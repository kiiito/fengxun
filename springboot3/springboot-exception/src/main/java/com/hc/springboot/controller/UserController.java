package com.hc.springboot.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/get/{id}")
    public String get(@PathVariable("id") Long id) {
        if(id == 1){
            throw new IllegalArgumentException("无效ID" + id);
        }
        return "id:" + id;
    }

    /**
     * 处理异常 springmvc的局部异常处理
     * 只有在当前controller中发生了IllegalArgumentException这个异常才能生效 才会执行方法
     * @param e
     * @return
     */
//    @ExceptionHandler(IllegalArgumentException.class)
//    public String handlerIllegalArgumentException(IllegalArgumentException e) {
//        return "错误信息：" + e.getMessage();
//    }
}
