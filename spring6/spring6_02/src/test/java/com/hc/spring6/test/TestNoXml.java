package com.hc.spring6.test;

import com.hc.testNoXml.Person;
import com.hc.testNoXml.SpringConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestNoXml {
    @Test
    public void NoXmlTest(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        Person person = context.getBean("person", Person.class);
        person.info();
    }
}
