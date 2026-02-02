package com.hc.springbootannotation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class UserController {
    @Autowired
    private Date date;
    @GetMapping("/hello")
    public String hello(){
        return date.toString();
    }
}
