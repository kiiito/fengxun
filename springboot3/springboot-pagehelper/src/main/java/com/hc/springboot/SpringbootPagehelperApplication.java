package com.hc.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.hc.springboot.repository")
public class SpringbootPagehelperApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootPagehelperApplication.class, args);
    }

}
