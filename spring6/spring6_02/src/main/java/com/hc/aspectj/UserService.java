package com.hc.aspectj;

import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {
    public void info(){
        System.out.println("userService info 方法执行");
    }
}
