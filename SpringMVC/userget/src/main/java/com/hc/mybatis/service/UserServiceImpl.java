package com.hc.mybatis.service;

import com.hc.mybatis.mapper.userMapper;
import com.hc.mybatis.pojo.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private userMapper userMapper;
    @Override
    public List<user> selectAll() {
        List<user> users = userMapper.selectByExample(null);
        return users;
    }

    @Override
    public int insert(user user) {
        return 0;
    }

    @Override
    public int delById(Integer id) {
        return 0;
    }

    @Override
    public user selectById(Integer id) {
        return null;
    }

    @Override
    public int update(user user) {
        return 0;
    }
}
