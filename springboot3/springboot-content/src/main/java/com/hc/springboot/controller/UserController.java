package com.hc.springboot.controller;

import com.hc.springboot.bean.User;
import com.hc.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService  userService;
    @GetMapping("/detail")
    public User detail(){
        return userService.getUserById();
    }
}
