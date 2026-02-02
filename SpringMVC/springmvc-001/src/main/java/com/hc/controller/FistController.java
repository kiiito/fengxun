package com.hc.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
//@RequestMapping("/first")//在控制类前面加上RequestMapping 可以防止RequestMapping出现重复的错误
public class FistController {
//    @RequestMapping("/")
//    public String index(){
//        return "index";
//    }

    @RequestMapping(value = "/test")
    public String hello(){
        return "first";
    }

    @RequestMapping("/two")
    public String two(){
        return "two";
    }

    /**
     *
     * ● ?，代表任意一个字符  (除/?之外的其它字符）,注意。一定是一个字符哦。不能空着。
     * ● *，代表0到N个任意字符 (除/?之外的其它字符）
     * ● **，代表0到N个任意字符，并且路径中可以出现路径分隔符 /
     * 注意：** 通配符在使用时，左右不能出现字符，只能是 /
     * @return
     */
   // @RequestMapping("/x?z/test")
    //@RequestMapping("/x*z/test")
    @RequestMapping("/x?z/test/**")
    public String testValueAnt(){
        return "test";
    }

    /**
     * 占位符的使用
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/login/{username}/{password}")
    public String testUrl(@PathVariable("username") String username,@PathVariable("password") String password){
        System.out.println("用户名 " + username + "密码" + password);
        return "test";
    }

    /**
     * method的使用 限制前端发送的请求方式
     * @return
     */
    //@RequestMapping(value = "/user/login" ,method = {RequestMethod.POST})
    @PostMapping("/user/login") //可以简写 代替@RequestMapping(value = "/user/login" ,method = {RequestMethod.POST})
    public String testMethod(){
        System.out.println("处理登陆业务");
        return "test";
    }

    /**
     * 测试params
     * @return
     */
    @RequestMapping(value = "/testParams",params = {"username=admin","password=123"})
    public String testParams(){
        return "test";
    }

    /**
     * 测试headers属性
     * @return
     */
    @RequestMapping(value="/testHeaders", headers = {"Referer=http://localhost:8080/springmvc/"})
    public String testHeaders(){
        return "test";
    }
}
