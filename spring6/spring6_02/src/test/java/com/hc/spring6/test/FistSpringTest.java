package com.hc.spring6.test;

import com.hc.dataSource.MyData1;
import com.hc.dataSource.MyData2;
import com.hc.dataSource.MyData3;
import com.hc.spring6.bean.User;
import com.hc.spring6.service.CustomerService;
import com.hc.spring6.service.UserService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FistSpringTest {

    @Test
    public void testScope(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-scope.xml");
        User userBean1 = applicationContext.getBean("userBean", User.class);
        System.out.println(userBean1);
        User userBean2 = applicationContext.getBean("userBean", User.class);
        System.out.println(userBean2);
        User userBean3 = applicationContext.getBean("userBean", User.class);
        System.out.println(userBean3);
    }
    @Test
    public void testProperties(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-properties.xml");
        MyData3 ds = applicationContext.getBean("ds", MyData3.class);
        System.out.println(ds);
    }
    @Test
    public void testUtil(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-util.xml");
        MyData1 ds1 = applicationContext.getBean("ds1", MyData1.class);
        MyData2 ds2 = applicationContext.getBean("ds2", MyData2.class);
        System.out.println(ds1);
        System.out.println(ds2);
    }

    @Test
    public void testSpring(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        User userBean2 = applicationContext.getBean("userBean2", User.class);
        System.out.println(userBean2);
    }

    @Test
    public void testConstructorSpring(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        CustomerService csBean = applicationContext.getBean("csBean", CustomerService.class);
        csBean.testInfo();

        CustomerService csBean2 = applicationContext.getBean("csBean2", CustomerService.class);
        csBean2.testInfo();

        CustomerService csBean3 = applicationContext.getBean("csBean3", CustomerService.class);
        csBean3.testInfo();
    }

    @Test
    public void testSetSpring(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("spring.xml");
        UserService userServiceBean = classPathXmlApplicationContext.getBean("userServiceBean", UserService.class);
        userServiceBean.testInfo();
    }



    @Test
    public void testFistSpringCode(){
        //第一步 获取spring容器
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        //第二步 根据bean的id从spring容器中获取这个对象
        Object userBean = applicationContext.getBean("userBean");
        //记录日志
        Logger logger = LoggerFactory.getLogger(FistSpringTest.class);
        logger.info("我是一条信息");
        System.out.println(userBean);
    }
}
