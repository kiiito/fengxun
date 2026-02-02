package com.hc.springboot.service.impl;

import com.hc.springboot.bean.User;
import com.hc.springboot.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User getUserById() {
        return new User("jason", 18);
    }
}
