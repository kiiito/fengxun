package com.hc.mybatis.Controller;

import com.hc.mybatis.pojo.user;
import com.hc.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user",method = {RequestMethod.GET})
    public String list(Model model){
        List<user> users = userService.selectAll();
        model.addAttribute("users",users);
        return "user_list";
    }
}
