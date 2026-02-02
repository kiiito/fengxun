package com.hc.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
public class UserController {
    @RequestMapping(value = "/")
    public String login(){
        return "register";
    }

    @PostMapping("/user/reg")
    public String testRequest(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String radio = request.getParameter("radio");
        String[] hobbies = request.getParameterValues("hobby");
        String intro = request.getParameter("intro");
        System.out.println(username);
        System.out.println(password);
        System.out.println(radio);
        System.out.println(hobbies.toString());
        System.out.println(intro);
        return "test";
    }
}
