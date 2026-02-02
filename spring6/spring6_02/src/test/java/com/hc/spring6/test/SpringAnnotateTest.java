package com.hc.spring6.test;

import com.hc.annotate.*;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringAnnotateTest {
    @Test
    public void testDIByAnnotation(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-annotate.xml");
        MyDateSource myDateSource = applicationContext.getBean("myDateSource", MyDateSource.class);
        System.out.println(myDateSource);
    }
    @Test
    public void testBeanComponent2(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-annotate.xml");

    }
    @Test
    public void testBeanComponent(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-annotate.xml");
//        User userBean = applicationContext.getBean("userBean", User.class);
        //只写注解 spring会默认将你类名首字母小写的类名当做id
        User userBean = applicationContext.getBean("user", User.class);
        order orderBean = applicationContext.getBean("orderBean", order.class);
        Student studentBean = applicationContext.getBean("studentBean", Student.class);
        Vip vipBean = applicationContext.getBean("vipBean", Vip.class);
        System.out.println(userBean);
        System.out.println(orderBean);
        System.out.println(studentBean);
        System.out.println(vipBean);
    }
}
