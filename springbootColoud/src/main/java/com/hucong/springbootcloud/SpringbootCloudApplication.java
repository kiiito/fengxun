package com.hucong.springbootcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hucong.springbootcloud.mapper")
public class SpringbootCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootCloudApplication.class, args);
    }

}
