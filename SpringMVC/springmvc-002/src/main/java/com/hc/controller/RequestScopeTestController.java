package com.hc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class RequestScopeTestController {

//    @RequestMapping(value = "/")
//    public String index(){
//        return "index";
//    }
    @RequestMapping("/testModel")
    public String TestModel(Model model){
        model.addAttribute("testRequestScope","在spring mvc中使用model接口来完成request域的数据共享");
        return "ok";
    }
    @RequestMapping("/testMap")
    public String testMap(Map<String,Object>map){
        map.put("testRequestScope","在spring mvc中使用Map接口来完成request域的数据共享");
        return "ok";
    }
    @RequestMapping("/testModelMap")
    public String testModelMap(ModelMap modelMap){
        modelMap.addAttribute("testRequestScope","在spring mvc中使用ModelMap类来完成request域的数据共享");
        return "ok";
    }

    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView (){
        ModelAndView mav = new ModelAndView();
        //给模型视图对象绑定数据
        mav.addObject("testRequestScope","在spring mvc中使用ModelAndView类来完成request域的数据共享");
        //给模型视图绑定视图
        mav.setViewName("ok");
        return mav;
    }
}
