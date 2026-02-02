package com.hc.springbootannotation;

import com.hc.springbootannotation.bean.User;
import com.hc.springbootannotation.config.AppConfig;
import com.hc.springbootannotation.service.UserService;
import com.hc.springbootannotation.service.impl.SystemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootAnnotationApplicationTests {

    @Autowired
    private UserService userService;
    @Autowired
    SystemService systemService;
    @Autowired
    AppConfig appConfig;
    @Test
    void contextLoads() {
        User info = userService.info();
        System.out.println(info);
    }
    @Test
    void test02(){
        systemService.info();
    }

    @Test
    void test03(){
        System.out.println(appConfig);
    }
}
