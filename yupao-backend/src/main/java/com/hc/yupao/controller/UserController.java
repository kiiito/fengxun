package com.hc.yupao.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hc.yupao.common.BaseResponse;
import com.hc.yupao.common.ErrorCode;
import com.hc.yupao.common.ResultUtils;
import com.hc.yupao.exception.BusinessException;
import com.hc.yupao.model.User;
import com.hc.yupao.model.request.UserLoginRequest;
import com.hc.yupao.model.request.UserRegisterRequest;
import com.hc.yupao.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.hc.yupao.contant.UserConstant.ADMIN_ROLE;
import static com.hc.yupao.contant.UserConstant.USER_LOGIN_STATE;

/**
 * @author 风寻
 */
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {"http://localhost:3000","http://117.72.163.49"},
        methods = {RequestMethod.POST,RequestMethod.GET}, allowCredentials = "true")
public class UserController {
    @Resource
    private UserService userService;

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
        if (!isAdmin(request)) {
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
     * 用户删除
     *
     * @param id 用户ID
     * @return 返回删除结果
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUser(@RequestBody long id, HttpServletRequest request) {
        //仅管理员可以删除
        if (!isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH, "非管理员不能删除用户信息");
        }
        boolean result = userService.removeById(id);
        return ResultUtils.success(result);
    }

    /**
     * 是否是管理员
     * 重复代码往外提
     *
     * @param request 用于获取用户登录态
     * @return 返回是否是管理员
     */
    private boolean isAdmin(HttpServletRequest request) {
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

}
