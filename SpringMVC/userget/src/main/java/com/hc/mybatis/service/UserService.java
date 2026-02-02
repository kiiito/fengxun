package com.hc.mybatis.service;

import com.hc.mybatis.pojo.user;

import java.util.List;

public interface UserService {
    List<user> selectAll();
    int insert(user user);
    int delById(Integer id);
    user selectById(Integer id);
    int update(user user);
}
