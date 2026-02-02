package com.hc.springboot.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @ControllerAdvice + @ExceptionHandler 可以用来定义全局异常处理 针对所有的控制器都有效
 */
//@ControllerAdvice
//public class GlobalExceptionHandler {
//    @ExceptionHandler(IllegalArgumentException.class)
//    @ResponseBody
//    public String handlerIllegalArgumentException(IllegalArgumentException e) {
//        return "错误信息：" + e.getMessage();
//    }
//}
