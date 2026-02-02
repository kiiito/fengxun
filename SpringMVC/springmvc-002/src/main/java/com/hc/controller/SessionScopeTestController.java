package com.hc.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * SessionAttributes(value = "testSessionScope") value 可以省略 也可以是多个值
 */
@SessionAttributes(value = "testSessionScope")
@Controller
public class SessionScopeTestController {
    @RequestMapping("/testSessionAttribute")
    public String testSessionAttribute(Model model){
        //这个需要标识是session域 不然就会返回request域
        model.addAttribute("testSessionScope","在spring mvc中使用model接口来完成session域的数据共享");
        return "ok";
    }
}
