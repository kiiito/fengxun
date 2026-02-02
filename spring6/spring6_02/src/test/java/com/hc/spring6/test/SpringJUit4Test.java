package com.hc.spring6.test;


import com.hc.JUti.User;
import com.hc.aspectj.affairs.OderService;
import jakarta.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-testJUit.xml")
public class SpringJUit4Test {
    @Resource(name = "user")
    private User user;

    @Test
    public void testUser(){
        System.out.println(user.getName());
    }

}
