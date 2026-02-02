package com.hc.annotate;


import org.springframework.stereotype.Component;


/**
 * 如果只是单个的话value可以省略
 * 如果全部省略不写 系统默认就会将你类名首字母小写后的类名当做你的id
 */
//@Component(value = "userBean")
//    @Component("userBean")
    @Component
public class User {
    public User() {
        System.out.println("user");
    }
}
