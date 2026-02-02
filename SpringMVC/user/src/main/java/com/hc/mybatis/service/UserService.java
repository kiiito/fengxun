package com.hc.mybatis.service;

import com.hc.mybatis.pojo.User;


import java.util.List;

public interface UserService {
    List<User> selectAll();
    int insert(User user);
    int delById(Integer id);
    User selectById(Integer id);
    int update(User user);
}
