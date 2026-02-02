package com.hc.controller;

import com.hc.pojo.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
public class UserController {
  //  @RequestMapping(value = "/")
    public String login(){
        return "register";
    }

//    @PostMapping("/user/reg")
//    public String testRequest(HttpServletRequest request, HttpServletResponse response, HttpSession session){
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        String sex = request.getParameter("sex");
//        String[] hobbies = request.getParameterValues("hobby");
//        String intro = request.getParameter("intro");
//        System.out.println(username);
//        System.out.println(password);
//        System.out.println(sex);
//        System.out.println(Arrays.toString(hobbies));
//        System.out.println(intro);
//        return "test";
//    }

    /**
     * RequestParam将请求映射在参数上
     * @param username
     * @param password
     * @param sex
     * @param hobbies
     * @param intro
     * @return
     */
//    @PostMapping("/user/reg")
//public String testRequest(
//        @RequestParam(name = "username")
//        String username,
//        @RequestParam(name = "password")
//        String password,
//        @RequestParam(name = "sex")
//        Integer sex,
//        @RequestParam(name = "hobby")
//        String[] hobbies,
//        @RequestParam(name = "intro")
//        String intro){
//        System.out.println(username);
//        System.out.println(password);
//        System.out.println(sex);
//        System.out.println(Arrays.toString(hobbies));
//        System.out.println(intro);
//    return "test";
//}

    /**
     * RequestParamS 省略的前提是参数的名称必须和前端传参的名称保持一致 且在spring6+版本中需要添加配置
     * @param username
     * @param password
     * @param sex
     * @param hobby
     * @param intro
     * @return
     */
//    @PostMapping("/user/reg")
//    public String testRequest(String username, String password, Integer sex, String[] hobby, String intro){
//        System.out.println(username);
//        System.out.println(password);
//        System.out.println(sex);
//        System.out.println(Arrays.toString(hobby));
//        System.out.println(intro);
//        return "test";
//    }

    /**
     * 直接通过pojo类向方法传参 要求pojo类中的属性名和前端传入参数的属性名相同（底层还是set方法名要正确）
     *  RequestHeader(value = "Referer",required = false,defaultValue = "") 获取请求头的一些信息 value
     * @param user
     * @return
     */
    @RequestMapping ("/user/reg")
    public String testRequest(User user,
                              @RequestHeader(value = "Referer",required = false,defaultValue = "")
                              String Referer,
                              @CookieValue(value = "id",required = false,defaultValue = "")
                              String id) {
        System.out.println(user);
        System.out.println(Referer);
        System.out.println("cookie的id =" + id );
        return "test";
    }
}
