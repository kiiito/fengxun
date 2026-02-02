package com.hc.spring6.test;

import com.hc.beanCycle.User;
import com.hc.beanCycle.vip;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanCycleTest {
    @Test
    public void testCycleSeven(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-cycle.xml");
        vip vip = applicationContext.getBean("vip", vip.class);
        System.out.println("第四步 使用bean " + vip);

        //需要手动关闭spring容器 这样spring容器才会销毁bean
        applicationContext.close();
    }
    @Test
    public void testCycleFive(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-cycle.xml");
        User user = applicationContext.getBean("user", User.class);
        System.out.println("第四步 使用bean " + user);

        //需要手动关闭spring容器 这样spring容器才会销毁bean
        applicationContext.close();
    }
}
