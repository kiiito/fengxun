package com.hc.user.service;

import com.hc.user.model.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hc.user.model.vo.UserVO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

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

    List<User> searchUsersByTags(List<String> tagNameList);


    /**
     * 更新用户信息
     */
    int updateUser(User user,User loginUser);

    /**
     * 获取当前登录用户
     * @return 返回当前登录用户
     */
    User getLoginUser(HttpServletRequest request);
    /**
     * 判断是否为管理员
     */
    boolean isAdmin(HttpServletRequest request);

    /**
     * 判断是否为管理员
     */
    boolean isAdmin(User loginUser);

    /**
     * 匹配最适合用户
     * @param num 匹配数量
     * @param loginUser 当前登录用户
     * @return 返回匹配用户
     */
    List<User> matchUsers(long num, User loginUser);
}

