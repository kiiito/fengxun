package com.hc.controller;

import com.hc.controller.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller
@RestController //给该类的每一个方法都加上ResponseBody  相当于Controller + ResponseBody
public class AjaxController {
    @RequestMapping(value = "/hello" ,method = RequestMethod.GET)
//    @ResponseBody
    public User ajax(){
        User user = new User(8, "甘雨", "0", "1232222@qq.com");
        return user;
    }
    @RequestMapping(value = "/a" ,method = RequestMethod.GET)
//    @ResponseBody
    public String ajax1(){
        return "ok";
    }
}
