package com.hc.handler;

import com.hc.bean.UserBean;
import com.hc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserHandler {

    @Autowired
    private UserService userService;
    @GetMapping("/{id}")
    public UserBean info(@PathVariable("id") int id){
        UserBean userBean = userService.getById(id);
        return userBean;
    }
}
