package com.hc.springbootannotation.service.impl;

import com.hc.springbootannotation.bean.User;
import com.hc.springbootannotation.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User info() {
        return new User("eula","123");
    }
}
