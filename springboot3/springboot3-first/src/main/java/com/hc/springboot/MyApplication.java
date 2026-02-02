package com.hc.springboot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//所有的springboot应用的主入口程序必须使用@SpringBootApplication注解进行标志
@SpringBootApplication
public class MyApplication {
    //主入口 也得启动服务器
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class,args);
    }
}
