package com.hc.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hc.user.common.ErrorCode;
import com.hc.user.exception.BusinessException;
import com.hc.user.mapper.UserMapper;
import com.hc.user.model.User;
import com.hc.user.model.vo.UserVO;
import com.hc.user.service.UserService;
import com.hc.user.utils.AlgorithmUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.description.method.MethodDescription;
import org.apache.commons.lang3.StringUtils;


import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.hc.user.contant.UserConstant.ADMIN_ROLE;
import static com.hc.user.contant.UserConstant.USER_LOGIN_STATE;

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
    public long userRegister(String userAccount, String userPassword, String checkPassword, String plantCode) {
        // 1 校验
        /**
         * StringUtils.isAnyBlank() 这是由commons-lang3提供的一个方法 用于判断是否为空 需要引入依赖
         */
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword, plantCode)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        if (userAccount.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户账号长度不能小于4");
        }
        if (userPassword.length() < 8 || checkPassword.length() < 8) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码和确认密码长度不能小于8");
        }
        if (plantCode.length() > 5) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "星球编号长度不能大于5");
        }
        //校验账户不能包含特殊字符
        // 定义账户名的正则表达式规则：
        // 只允许包含字母(a-z,A-Z)、数字(0-9)、下划线(_)和中文汉字
        String validPattern = "^[a-zA-Z0-9_\\u4e00-\\u9fa5]+$";
        // 使用正则表达式编译模式并创建匹配器
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        // 检查用户账户名是否匹配正则规则
        if (!matcher.matches()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户账号不能包含特殊字符");
        }
        //校验密码和确认密码是否一致
        if (!userPassword.equals(checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码和确认密码不一致");
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
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户账号已存在");
        }
        //星星球编号不能重复
        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("plantCode", plantCode);
        count = userMapper.selectCount(queryWrapper);
        if (count > 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "星球编号已存在");
        }

        // 2 对密码进行加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        //3 插入数据到数据库
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        user.setPlantCode(plantCode);
        boolean saveResult = this.save(user);
        if (!saveResult) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "用户注册失败");
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
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        if (userAccount.length() < 4) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户账号长度不能小于4");
        }
        if (userPassword.length() < 8) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码长度不能小于8");
        }
        //校验账户不能包含特殊字符
        // 定义账户名的正则表达式规则：
        // 只允许包含字母(a-z,A-Z)、数字(0-9)、下划线(_)和中文汉字
        String validPattern = "^[a-zA-Z0-9_\\u4e00-\\u9fa5]+$";
        // 使用正则表达式编译模式并创建匹配器
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        // 检查用户账户名是否匹配正则规则
        if (!matcher.matches()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户账号不能包含特殊字符");
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
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户账号或密码错误");
        }
        // 3 用户脱敏
        User safetyUser = getSafetyUser(user);
        //4 记录用户的登录态
        request.getSession().setAttribute(USER_LOGIN_STATE, safetyUser);

        return safetyUser;
    }

    /**
     * 用户脱敏
     *
     * @param originUser 原始用户
     * @return 返回脱敏用户
     */
    @Override
    public User getSafetyUser(User originUser) {
        if (originUser == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户为空");
        }
        User safetyUser = new User();
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
        safetyUser.setTags(originUser.getTags());
        return safetyUser;
    }

    /**
     * 用户注销
     *
     * @param request 用于获取用户登录态
     */
    @Override
    public int userLogout(HttpServletRequest request) {
        // 清除用户登录态
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return 1;
    }

    /**
     * 根据标签列表搜索用户(内存查询)
     *
     * @param tagNameList 标签名称列表，要求非空
     * @return 脱敏后的用户列表
     * @throws BusinessException 如果标签列表为空，抛出参数错误异常
     */
    @Override
    public List<User> searchUsersByTags(List<String> tagNameList) {
        // 校验参数：如果标签列表为空，抛出业务异常
        if (CollectionUtils.isEmpty(tagNameList)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 1 先查询所有用户
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        List<User> userList = userMapper.selectList(queryWrapper);
        //创建一个Gson对象 将字符串转成json格式
        Gson gson = new Gson();
        // 2 在内存中判断是否包含要求的标签
        //userList.stream().filter 将返回值是false的过滤
        return userList.stream().filter(user -> {
            //获取到标签
            String tagStr = user.getTags();
            //如果标签为空 直接返回false
            if (StringUtils.isBlank(tagStr)) {
                return false;
            }
            //使用 Gson 库将 JSON 格式的字符串 tagStr 反序列化为一个 Set<String> 集合
            Set<String> tempTagNameSet = gson.fromJson(tagStr, new TypeToken<Set<String>>() {
            }.getType());
            // 使用 Optional 对 tempTagNameSet 进行空值安全处理
            // 如果 tempTagNameSet 为 null，则返回一个空的 HashSet 对象；否则返回原对象
            // 作用：避免后续操作中出现 NullPointerException，确保集合对象始终可用
            tempTagNameSet = Optional.ofNullable(tempTagNameSet).orElse(new HashSet<>());
            //遍历标签列表 判断是否包含
            for (String tagName : tagNameList) {
                if (!tempTagNameSet.contains(tagName)) {
                    return false;
                }
            }
            return true;
        }).map(this::getSafetyUser).collect(Collectors.toList());
    }

    @Override
    public int updateUser(User user, User loginUser) {
        long userId = user.getId();
        if (userId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //如果是管理员可以更新任意用户
        // 如果不是管理员 只允许更新自己的信息
        if (!isAdmin(loginUser) && userId != loginUser.getId()) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        User oldUser  = userMapper.selectById(userId);
        if (oldUser == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
       return userMapper.updateById(user);
    }


    /**
     * 获取当前登录用户信息
     * @param request 用于获取用户登录态
     * @return 返回当前登录用户
     */
    @Override
    public User getLoginUser(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        return (User) userObj;
    }

    /**
     * 根据标签列表搜索用户(SQL 查询)
     *
     * @param tagNameList 标签名称列表，要求非空
     * @return 脱敏后的用户列表
     * @throws BusinessException 如果标签列表为空，抛出参数错误异常
     */
    @Deprecated
    private List<User> searchUsersByTagsBySQL(List<String> tagNameList) {
        // 校验参数：如果标签列表为空，抛出业务异常
        if (CollectionUtils.isEmpty(tagNameList)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        // 创建MyBatis-Plus查询条件构造器
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        // 动态拼接AND查询条件：遍历标签列表，每个标签都作为模糊查询条件
        for (String tagName : tagNameList) {
            // like方法生成SQL：tag LIKE '%tagName%'
            queryWrapper = queryWrapper.like("tags", tagName);
        }

        // 执行数据库查询，获取原始用户列表
        List<User> userList = userMapper.selectList(queryWrapper);

        // 对结果进行流式处理：对每个用户对象进行脱敏处理，并收集为List返回
        return userList.stream()
                .map(this::getSafetyUser)  // 调用脱敏方法处理每个用户
                .collect(Collectors.toList());
    }
    /**
     * 是否是管理员
     * 重复代码往外提
     *
     * @param request 用于获取用户登录态
     * @return 返回是否是管理员
     */
    @Override
    public boolean isAdmin(HttpServletRequest request) {
        // 从session中获取当前登录用户信息
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        // 将获取的对象转换为User类型
        User user = (User) userObj;
        // 权限检查：如果用户未登录或不是管理员
        if (user == null || user.getUserRole() != ADMIN_ROLE) {
            return false;
        }
        return true;
    }
    /**
     * 是否是管理员
     * 重复代码往外提
     *
     * @param loginUser 用于获取用户登录态
     * @return 返回是否是管理员
     */
    @Override
    public boolean isAdmin(User loginUser) {

        // 权限检查：如果用户未登录或不是管理员
        if (loginUser == null || loginUser.getUserRole() != ADMIN_ROLE) {
            return false;
        }
        return true;
    }

    @Override
    public List<User> matchUsers(long num, User loginUser) {
        //创建查询条件 提高查询效率
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //排除标签为空的用户
        queryWrapper.isNotNull("tags");
        //只查需要的的数据
        queryWrapper.select("id","tags");
        //查询列表
        List<User> userList = this.list(queryWrapper);

        //获取登录用户的标签字段
        String tags = loginUser.getTags();
        Gson gson = new Gson();
        List<String> tagList = gson.fromJson(tags, new TypeToken<List<String>>() {
        }.getType());
        //用户列表的下表 =》相似度
        List<Pair<User,Long>> list = new ArrayList<>();
        for (int i = 0; i < userList.size(); i++) {
            User user = userList.get(i);
            String userTags = user.getTags();
            //无标签或者是当前用户则跳过
            if (StringUtils.isBlank(userTags) || user.getId() == loginUser.getId()){
                continue;
            }
            //获取其他用户的标签列表
            List<String> userTagList = gson.fromJson(userTags, new TypeToken<List<String>>() {
            }.getType());
            long distance = AlgorithmUtils.minDistance(tagList, userTagList);
            list.add(Pair.of(user, distance));
        }
        //按编辑距离有小到大排序
        List<Pair<User, Long>> topUserPairList = list.stream()
                .sorted((a, b) -> (int) (a.getValue() - b.getValue()))
                .limit(num)
                .collect(Collectors.toList());
        //有顺序的userID列表
        List<Long> userListVo = topUserPairList.stream().map(pari -> pari.getKey().getId()).collect(Collectors.toList());

        //根据id查询user完整信息
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.in("id",userListVo);
        Map<Long, List<User>> userIdUserListMap = this.list(userQueryWrapper).stream()
                .map(user -> getSafetyUser(user))
                .collect(Collectors.groupingBy(User::getId));

        // 因为上面查询打乱了顺序，这里根据上面有序的userID列表赋值
        List<User> finalUserList = new ArrayList<>();
        for (Long userId : userListVo){
            finalUserList.add(userIdUserListMap.get(userId).get(0));
        }
        return finalUserList;
    }


}




