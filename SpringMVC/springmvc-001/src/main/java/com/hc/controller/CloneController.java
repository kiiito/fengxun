package com.hc.controller;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("clone")
public class CloneController {
    @RequestMapping(value = "/test")
    public String hello(){
        return "first";
    }

}
