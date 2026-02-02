package com.hc.user.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hc.user.common.BaseResponse;
import com.hc.user.common.ErrorCode;
import com.hc.user.common.ResultUtils;
import com.hc.user.config.UploadConfig;
import com.hc.user.exception.BusinessException;
import com.hc.user.model.User;
import com.hc.user.model.request.UserLoginRequest;
import com.hc.user.model.request.UserRegisterRequest;
import com.hc.user.service.UserService;
import com.hc.user.utils.FileUploadUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.hc.user.contant.UserConstant.USER_LOGIN_STATE;

/**
 * @author 风寻
 */
@RestController
@Slf4j
@RequestMapping("/user")
//@CrossOrigin(origins = {"http://localhost:3000","http://117.72.163.49"},
//        methods = {RequestMethod.POST,RequestMethod.GET}, allowCredentials = "true")
@CrossOrigin(origins = {"http://localhost:3000","http://localhost:5173", "http://127.0.0.1:5173", "http://127.0.0.1:8082", "http://127.0.0.1:8083"})
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private RedisTemplate<String ,Object> redisTemplate;

    @Resource
    private UploadConfig uploadConfig;
    /**
     * 用户注册
     *
     * @param userRegisterRequest 用户注册请求体
     * @return 返回用户ID
     * @RequestBody 用于接收前端传递的json数据
     */
    @PostMapping("register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空");
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        String planetCode = userRegisterRequest.getPlantCode();
        /**
         * 这里进行参数非空校验
         */
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword, planetCode)) {
            return ResultUtils.error(ErrorCode.PARAMS_ERROR);
        }
        long result = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
//        return new BaseResponse<>(0,result,"ok");
        //封装一个返回结果工具类
        return ResultUtils.success(result);
    }

    /**
     * 用户登录
     *
     * @param userLoginRequest 用户登录请求体
     * @param request          用于记录用户的登录态
     * @return 返回已经脱敏的用户信息
     */
    @PostMapping("login")
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            //抛出自定义的异常
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        /**
         * 这里进行参数非空校验
         */
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空");
        }
        User user = userService.userLogin(userAccount, userPassword, request);
//        return new BaseResponse<>(0,user,"ok");
        return ResultUtils.success(user);

    }

    /**
     * 用户注销
     *
     * @param request 用于获取用户登录态
     * @return 返回注销结果
     */
    @PostMapping("logout")
    public BaseResponse<Integer> userLogout(HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空");
        }
        int logout = userService.userLogout(request);
        return ResultUtils.success(logout);
    }

    /**
     * 用户查询 支持模糊查询
     *
     * @param username 用户名称
     * @return 返回用户列表
     */
    @GetMapping("/search")
    public BaseResponse<List<User>> searchUsers(String username, HttpServletRequest request) {
        //仅管理员可以查询
        if (!userService.isAdmin(request)) {
            // 返回空列表
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "列表为空");
        }
        // 构建查询条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //StringUtils.isNotBlank(username) 这是由commons-lang3提供的一个方法 用于判断是否为空 需要引入依赖
        if (StringUtils.isNotBlank(username)) {
            // 设置查询条件：用户名称模糊匹配传入的username 即用户名称包含username 参数有三个值 最后一个有默认值 即%username%
            queryWrapper.like("username", username);
        }
        List<User> userList = userService.list(queryWrapper);
        // 将用户列表(userList)中的每个用户对象转换为安全用户对象(脱敏处理)
        // 1. 使用stream()将List转为流以便进行链式处理
        // 2. 通过map()对每个用户调用getSafetyUser()方法进行数据脱敏
        // 3. 最后使用collect()将处理后的流重新收集为List集合返回
        List<User> list = userList.stream().map(user -> userService.getSafetyUser(user)).collect(Collectors.toList());
        return ResultUtils.success(list);
    }

    /**
     * 用户查询 支持模糊查询
     *
     * @return 返回用户列表
     */
    @GetMapping("/recommend")
    public BaseResponse<Page<User>> recommendsUsers(long pageSize, long pageNum, HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        // 添加空值检查
        if (loginUser == null) {
            return ResultUtils.error(ErrorCode.NOT_LOGIN, "用户未登录");
        }
            String redisKey = String.format("yupao:user:recommend:%s", loginUser.getId());
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        //如果有缓存 直接读缓存
        Page<User> userPage = (Page<User>) valueOperations.get(redisKey);
        if (userPage != null) {
            return ResultUtils.success(userPage);
        }
        // 无缓存 构建查询条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        userPage = userService.page(new Page<>(pageNum,pageSize),queryWrapper);
       //写缓存
        try {
            valueOperations.set(redisKey,userPage,10, TimeUnit.MINUTES);
        } catch (Exception e) {
            log.error("redis set key error", e);
        }

        return ResultUtils.success(userPage);
    }


    /**
     * 用户删除
     *
     * @param id 用户ID
     * @return 返回删除结果
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUser(@RequestBody long id, HttpServletRequest request) {
        //仅管理员可以删除
        if (!userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH, "非管理员不能删除用户信息");
        }
        boolean result = userService.removeById(id);
        return ResultUtils.success(result);
    }


    /**
     * 获取当前登录用户
     *
     * @param request 用于获取用户登录态
     * @return 返回当前登录用户
     */
    @GetMapping("/current")
    public BaseResponse<User> getCurrentUser(HttpServletRequest request) {
        // 从session中获取当前登录用户信息
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        // 将获取的对象转换为User类型
        User currentUser = (User) userObj;
        if (currentUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN, "未登录");
        }
        Long userId = currentUser.getId();
        /**
         * TODO 校验用户是否合法
         */
        User user = userService.getById(userId);
        User safetyUser = userService.getSafetyUser(user);
        return ResultUtils.success(safetyUser);
    }

    @GetMapping("/search/tags")
    public BaseResponse<List<User>> searchUsersByTags( @RequestParam(required = false) List<String> tagNameList){
        if (CollectionUtils.isEmpty(tagNameList)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        List<User> userList = userService.searchUsersByTags(tagNameList);
        return  ResultUtils.success(userList);
    }

    @PostMapping("/update")
    public  BaseResponse<Integer> updateUser( @RequestBody User user,HttpServletRequest request){
        //1 校验参数是否为空
        if (user == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //2 校验权限
        User loginUser = userService.getLoginUser(request);
        if (loginUser == null) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        //3 触发更新
        int result = userService.updateUser(user,loginUser);
        return ResultUtils.success(result);
    }

    /**
     * 获取最匹配的用户
     * @param num 匹配数量
     * @param request 用于获取用户登录态
     * @return 返回最匹配的用户列表
     */
    @GetMapping("/match")
    public BaseResponse <List<User>> matchUsers(long num, HttpServletRequest request) {
        if (num <= 0 || num > 20){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "匹配数量不合法");
        }
        User loginUser = userService.getLoginUser(request);
        return ResultUtils.success(userService.matchUsers(num,loginUser));
    }



    @PostMapping("/avatar/upload")
    public BaseResponse<String> uploadAvatar(
            @RequestParam("file") MultipartFile file,
            @RequestParam("userId") Long userId,
            HttpServletRequest request) {

        try {
            // 上传文件
            String fileName = FileUploadUtil.uploadAvatar(file, uploadConfig.getAvatarPath());

            // 构建访问URL（使用相对路径，避免硬编码域名）
            String avatarUrl = uploadConfig.getAvatarAccessUrl() + fileName;

            // 更新数据库
            User user = new User();
            user.setId(userId);
            user.setAvatarUrl(avatarUrl);
            userService.updateById(user);

            return ResultUtils.success(avatarUrl);

        } catch (IOException e) {
            log.error("头像上传失败", e);
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "头像上传失败");
        }
    }
}
