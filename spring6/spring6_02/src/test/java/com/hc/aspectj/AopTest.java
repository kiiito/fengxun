package com.hc.aspectj;

import com.hc.aspectj.affairs.OderService;
import com.hc.aspectj.xml.PersonService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

import javax.swing.*;

public class AopTest {

    @Test
    public void testAffairs(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(com.hc.aspectj.affairs.SpringConfig.class);
        OderService oderService = applicationContext.getBean("oderService", OderService.class);
        oderService.transfer();
    }

    @Test
    public void testXml(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-aspectj.xml");
        PersonService personService = applicationContext.getBean("personService", PersonService.class);
        personService.info();
    }


    @Test
    public void testNoXml(){
        ApplicationContext applicationContext =  new AnnotationConfigApplicationContext(SpringConfig.class);
        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.info();
    }




    @Test
    public void testBefore(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-aspectj-aop.xml");
        UserService userBean = applicationContext.getBean("userService", UserService.class);
        userBean.info();
    }


}
