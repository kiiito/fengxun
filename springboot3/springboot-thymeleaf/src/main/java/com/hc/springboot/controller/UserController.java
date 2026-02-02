package com.hc.springboot.controller;

import com.hc.springboot.bean.User;
import com.hc.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/list")
    public String list(Model model){
       List<User> users = userService.getAll();
       model.addAttribute("users",users);
       return "list";
    }
}
