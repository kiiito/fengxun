package com.hc.yupao.service;

import com.hc.yupao.model.User;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;

/**
* @author 风寻
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2025-07-05 13:57:00
*/
public interface UserService extends IService<User> {

    /**
     * 用户注册
     * @param userAccount 账号
     * @param userPassword 密码
     * @param checkPassword 确认密码
     * @param plantCode 星球编号
     * @return 返回用户ID
     */
    long userRegister(String userAccount,String userPassword,String checkPassword,String plantCode);

    /**
     * 用户登录
     * @param userAccount 账号
     * @param userPassword 密码
     * @return 返回用户信息
     */
    User userLogin(String userAccount,String userPassword,HttpServletRequest request);

    /**
     * 用户脱敏
     * @param originUser 原始用户
     * @return 返回脱敏用户
     */
    User getSafetyUser(User originUser);

    /**
     * 用户注销
     * @return 返回注销结果
     */
    int userLogout(HttpServletRequest request);
}
