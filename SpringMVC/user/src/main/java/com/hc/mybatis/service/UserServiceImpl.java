package com.hc.mybatis.service;

import com.hc.mybatis.mapper.UserMapper;
import com.hc.mybatis.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> selectAll() {
        List<User> users = userMapper.selectAll();
        return users;
    }

    @Override
    public int insert(User user) {
        int count = userMapper.insert(user);
        return count;
    }

    @Override
    public int delById(Integer id) {
        int count = userMapper.delete(id);
        return count;
    }

    @Override
    public User selectById(Integer id) {
        User user = userMapper.selectById(id);
        return user;
    }

    @Override
    public int update(User user) {
        int count = userMapper.update(user);
        return count;
    }
}
