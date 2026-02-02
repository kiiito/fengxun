package com.hc.yupao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hc.yupao.common.ErrorCode;
import com.hc.yupao.exception.BusinessException;
import com.hc.yupao.model.User;
import com.hc.yupao.service.UserService;
import com.hc.yupao.mapper.UserMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.hc.yupao.contant.UserConstant.USER_LOGIN_STATE;

/**
 * @author 风寻
 * @description 针对表【user(用户)】的数据库操作Service实现
 * @createDate 2025-07-05 13:57:00
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    @Resource
    private UserMapper userMapper;
    /**
     * 盐值 混淆密码
     */
    private static final String SALT = "hc";

    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword,String plantCode) {
        // 1 校验
        /**
         * StringUtils.isAnyBlank() 这是由commons-lang3提供的一个方法 用于判断是否为空 需要引入依赖
         */
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword,plantCode)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数为空");
        }
        if (userAccount.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户账号长度不能小于4");        }
        if (userPassword.length() < 8 || checkPassword.length() < 8) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"密码和确认密码长度不能小于8");        }
        if (plantCode.length()>5){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"星球编号长度不能大于5");        }
        //校验账户不能包含特殊字符
        // 定义账户名的正则表达式规则：
        // 只允许包含字母(a-z,A-Z)、数字(0-9)、下划线(_)和中文汉字
        String validPattern = "^[a-zA-Z0-9_\\u4e00-\\u9fa5]+$";
        // 使用正则表达式编译模式并创建匹配器
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        // 检查用户账户名是否匹配正则规则
        if (!matcher.matches()) {
throw  new BusinessException(ErrorCode.PARAMS_ERROR,"用户账号不能包含特殊字符");        }
        //校验密码和确认密码是否一致
        if (!userPassword.equals(checkPassword)) {
            throw  new BusinessException(ErrorCode.PARAMS_ERROR,"密码和确认密码不一致");
        }
        //账户不能重复
        /**
         * 这里使用了mybatis-plus提供的QueryWrapper 用于构建查询条件
         */
        // 创建查询条件构造器
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 设置查询条件：用户账号等于传入的userAccount
        queryWrapper.eq("userAccount", userAccount);
        // 查询数据库中满足条件的用户数量
        long count = userMapper.selectCount(queryWrapper);
        // 如果数量大于0，说明该用户账号已存在
        if (count > 0) {
            throw  new BusinessException(ErrorCode.PARAMS_ERROR,"用户账号已存在");
        }
        //星星球编号不能重复
        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("plantCode", plantCode);
        count = userMapper.selectCount(queryWrapper);
        if (count > 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"星球编号已存在");
        }

        // 2 对密码进行加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        //3 插入数据到数据库
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        user.setPlantCode(plantCode);
        boolean saveResult = this.save(user);
        if (!saveResult){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"用户注册失败");
        }
        return user.getId();
    }

    @Override
    public User userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        // 1 校验
        /**
         * StringUtils.isAnyBlank() 这是由commons-lang3提供的一个方法 用于判断是否为空 需要引入依赖
         */
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw  new BusinessException(ErrorCode.PARAMS_ERROR,"参数为空");
        }
        if (userAccount.length() < 4) {
            throw  new BusinessException(ErrorCode.PARAMS_ERROR,"用户账号长度不能小于4");
        }
        if (userPassword.length() < 8) {
            throw  new BusinessException(ErrorCode.PARAMS_ERROR,"密码长度不能小于8");
        }
        //校验账户不能包含特殊字符
        // 定义账户名的正则表达式规则：
        // 只允许包含字母(a-z,A-Z)、数字(0-9)、下划线(_)和中文汉字
        String validPattern = "^[a-zA-Z0-9_\\u4e00-\\u9fa5]+$";
        // 使用正则表达式编译模式并创建匹配器
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        // 检查用户账户名是否匹配正则规则
        if (!matcher.matches()) {
            throw  new BusinessException(ErrorCode.PARAMS_ERROR,"用户账号不能包含特殊字符");
        }
        // 2 对密码进行加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        //查询用户是否存在
        /**
         * 这里使用了mybatis-plus提供的QueryWrapper 用于构建查询条件
         */
        // 创建查询条件构造器
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 设置查询条件：用户账号等于传入的userAccount
        queryWrapper.eq("userAccount", userAccount);
        // 设置查询条件：用户密码等于传入的userPassword
        queryWrapper.eq("userPassword", encryptPassword);
        // 查询数据库中满足条件的用户数量
        User user = userMapper.selectOne(queryWrapper);
        // 用户不存在
        if (user == null) {
            log.info("user login failed, userAccount cannot match userPassword");
            throw  new BusinessException(ErrorCode.PARAMS_ERROR,"用户账号或密码错误");
        }
        // 3 用户脱敏
        User safetyUser = getSafetyUser(user);
        //4 记录用户的登录态
        request.getSession().setAttribute(USER_LOGIN_STATE, safetyUser);

        return safetyUser;
    }

    /**
     * 用户脱敏
     * @param originUser 原始用户
     * @return 返回脱敏用户
     */
    @Override
    public User getSafetyUser(User originUser){
        if (originUser == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"用户为空");
        }
        User safetyUser  = new User();
        safetyUser.setId(originUser.getId());
        safetyUser.setUsername(originUser.getUsername());
        safetyUser.setUserAccount(originUser.getUserAccount());
        safetyUser.setAvatarUrl(originUser.getAvatarUrl());
        safetyUser.setGender(originUser.getGender());
        safetyUser.setPhone(originUser.getPhone());
        safetyUser.setEmail(originUser.getEmail());
        safetyUser.setUserRole(originUser.getUserRole());
        safetyUser.setUserStatus(originUser.getUserStatus());
        safetyUser.setCreateTime(originUser.getCreateTime());
        safetyUser.setPlantCode(originUser.getPlantCode());
        return safetyUser;
    }

    /**
     * 用户注销
     * @param request 用于获取用户登录态
     */
    @Override
    public int userLogout(HttpServletRequest request) {
        // 清除用户登录态
        request.getSession().removeAttribute(USER_LOGIN_STATE);
       return 1;
    }

}




