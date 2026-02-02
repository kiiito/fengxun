package com.hc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ForwardController {
@RequestMapping("/a")
    public String testA(){
    //这是转发的格式
    return "forward:/b";

    //这是重定向的格式
//    return "redirect:/b";
}
@RequestMapping("/b")
    public String testB(){
    return "b";
}
}
