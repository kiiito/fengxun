package com.hc.spring6.test;

import com.hc.CircularDependency.Husband;
import com.hc.CircularDependency.Wife;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CircularDependencyTest {
    @Test
    public void test(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-cd.xml");
        Husband h = applicationContext.getBean("h", Husband.class);
        Wife w = applicationContext.getBean("w", Wife.class);
        System.out.println(h);
        System.out.println(w);
    }
}
