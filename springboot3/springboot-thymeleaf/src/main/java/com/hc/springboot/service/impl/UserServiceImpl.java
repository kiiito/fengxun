package com.hc.springboot.service.impl;

import com.hc.springboot.bean.User;
import com.hc.springboot.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        users.add(new User("eula",20,false,"my name is eula","beijing"));
        users.add(new User("king",28,true,"my name is king","beijing"));
        users.add(new User("tom",20,true,"my name is tom","JiangXi"));
        return users;
    }
}
