package com.hc.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FistController {
    @RequestMapping(value = "/hello",method = RequestMethod.POST)
    public String fist(){
        System.out.println("helloController 执行");
        return "hello";
    }
}
