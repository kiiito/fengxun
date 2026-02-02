package com.hc.springboot.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {
    @GetMapping("/hello")
    public String hello(@RequestParam("name") String name, Model model){
        //保存数据到域当中
        model.addAttribute("name",name);
        model.addAttribute("url","http://localhost:8080/1.mp4");
        model.addAttribute("love","我的喜欢");
        model.addAttribute("style", "color:red");
        return "hello";
    }
}
