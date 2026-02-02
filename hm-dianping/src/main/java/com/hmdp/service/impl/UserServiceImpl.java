package com.hmdp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hmdp.dto.LoginFormDTO;
import com.hmdp.dto.Result;
import com.hmdp.dto.UserDTO;
import com.hmdp.entity.User;
import com.hmdp.mapper.UserMapper;
import com.hmdp.service.IUserService;
import com.hmdp.utils.RegexUtils;
import com.hmdp.utils.UserHolder;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.hmdp.utils.RedisConstants.*;
import static com.hmdp.utils.SystemConstants.USER_NICK_NAME_PREFIX;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 虎哥
 * @since 2021-12-22
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    // 注入redis
    @Resource
    private StringRedisTemplate  stringRedisTemplate;
    @Override
    public Result sendCode(String phone, HttpSession session) {
        // 1 校验手机号
        if (RegexUtils.isPhoneInvalid(phone)) {
            // 2 如果不符合 返回错误信息
            return Result.fail("手机号格式错误");
        }
        // 3 验证通过 生成验证码
        String code = RandomUtil.randomNumbers(6);
        // 4 将验证码存入session
        /**
         * 4 这里更改成用redis存储验证码 用手机号作为key 验证码作为value 方便在登入的时候获取
         * 注意这里手机号需要加一些业务逻辑前缀 因为不止一个地方需要手机号作为key 如果多个相同的key同时进入redis 会覆盖
         * 这里还需要设置过期时间 防止被恶意多次点击 生成大量验证码 占用redis空间
         * 相对于 set key value ex 120 这里使用 opsForValue().set(key,value,ttl,TimeUnit.MINUTES) 这个方法
         *   public static final String LOGIN_CODE_KEY = "login:code:";
         *     public static final Long LOGIN_CODE_TTL = 2L;
         */
        stringRedisTemplate.opsForValue().set(LOGIN_CODE_KEY + phone,code,LOGIN_CODE_TTL, TimeUnit.MINUTES);
//        session.setAttribute("code", code);
        // 5 发送验证码
        log.debug("发送短信验证码成功 验证码：{}", code);
        return Result.ok();
    }

    @Override
    public Result login(LoginFormDTO loginForm, HttpSession session) {
        // 1 校验手机号
        String phone = loginForm.getPhone();
        if (RegexUtils.isPhoneInvalid(phone)) {
            // 2 如果不符合 返回错误信息
            return Result.fail("手机号格式错误");
        }
        // 2 校验验证码
//        Object cacheCode = session.getAttribute("code");
        String cacheCode = stringRedisTemplate.opsForValue().get(LOGIN_CODE_KEY + phone);
        String code = loginForm.getCode();
        if (cacheCode == null || !(cacheCode.equals(code))) {
            // 3 不一致 报错
            return Result.fail("验证码错误");
        }
        // 4 一致 根据手机号查询用户
        //该语句等同于 select * from tb_user where phone = phone 是mybatis-plus的语法
        User user = query().eq("phone", phone).one();
        // 5 判断用户是否存在
        if (user == null) {
            // 6 不存在 创建用户并保存
            user = createUserWithPhone(phone);
        }
        // 7 保存用户信息到session
        // UserDTO 脱敏用户信息
        /**
         *  保存用户信息到redis
         *  1 随机生成token 作为登陆令牌 这里不用手机号作为key 主要原因是可能会导致隐私泄露
         *  2 将user对象转化为hashMap存储
         *  3 存储
         *  4 设置token的有效时间 这里有一个需要注意的点 就是不管用户是否操作 都会在设置有效时间过期后失效
         *  这里就需要在拦截器中添加更新token的有效时间的逻辑 如果拦截器拦截到用户的请求 就说明用户正在活跃中 就需要更新token的有效时间
         *  5 将token返回给前端 前端会将这个token保存到一个域当中
         */
        String token = UUID.randomUUID(true).toString(true);
//        session.setAttribute("user", BeanUtil.copyProperties(user, UserDTO.class));
        UserDTO userDTO = BeanUtil.copyProperties(user, UserDTO.class);
        Map<String, Object> userMap = BeanUtil.beanToMap(userDTO,new HashMap<>(),
                CopyOptions.create()
                        .setIgnoreNullValue(true)
                        .setFieldValueEditor((filedName,fieldValue) -> fieldValue.toString()));
        stringRedisTemplate.opsForHash().putAll(LOGIN_USER_KEY + token,userMap);
        stringRedisTemplate.expire(LOGIN_USER_KEY + token,LOGIN_USER_TTL, TimeUnit.SECONDS);
        return Result.ok(token);
    }

    @Override
    public Result sign() {
        // 1 获取登录用户
        Long userId = UserHolder.getUser().getId();
        // 2获取日期
        LocalDateTime now = LocalDateTime.now();
        // 3 拼接key
        String keySuffix = now.format(DateTimeFormatter.ofPattern(":yyyyMM"));
        String key = USER_SIGN_KEY + userId + keySuffix;
        // 4 获取今天是本月的第几天
        int dayOfMonth = now.getDayOfMonth();
        // 5 写入redis SETBIT Key offset 1
        stringRedisTemplate.opsForValue().setBit(key,dayOfMonth - 1,true);
        return Result.ok();
    }

    @Override
    public Result signCount() {
        // 1 获取登录用户
        Long userId = UserHolder.getUser().getId();
        // 2获取日期
        LocalDateTime now = LocalDateTime.now();
        // 3 拼接key
        String keySuffix = now.format(DateTimeFormatter.ofPattern(":yyyyMM"));
        String key = USER_SIGN_KEY + userId + keySuffix;
        // 4 获取今天是本月的第几天
        int dayOfMonth = now.getDayOfMonth();
        // 5 获取本月截止今天的所有签到记录 返回的是一个十进制的数字
        List<Long> result = stringRedisTemplate.opsForValue().bitField(
                key,
                BitFieldSubCommands.create()
                        .get(BitFieldSubCommands.BitFieldType.unsigned(dayOfMonth)).valueAt(0)
        );
        if (result == null || result.isEmpty()){
            // 如果没有签到记录 返回0
            return Result.ok(0);
        }
        Long num = result.get(0);
        if (num == null  || num == 0){
            // 如果没有签到记录 返回0
            return Result.ok(0);
        }
        int count = 0;
        // 6 循环遍历
        while (true){
            //  让这个数字与1做与运算 得到数字的最后一个bit位
            // 判断这个bit位是否为0
            if ((num & 1) == 0){
                // 如果为0 说明没有签到 结束
                break;
            }else {
                // 如果不为0 说明签到了 计数器加一
                count++;
            }
            // 把数字右移一位 抛弃最后一个bit位 继续下一个bit位
            num >>>= 1;
        }

        // 7 返回结果
        return Result.ok(count);
    }

    private User createUserWithPhone(String phone) {
        //创建用户
        User user = new User();
        user.setPhone(phone);
        user.setNickName(USER_NICK_NAME_PREFIX + RandomUtil.randomString(10));
        save(user);
        return user;
    }
}
