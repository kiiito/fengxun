package com.hc.spring6.test;
import com.hc.bank.SpringConfig;
import com.hc.bank.bean.Account;
import com.hc.bank.service.AccountServiceImpl;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.awt.*;

public class SpringBankTest {

    @Test
    public void testNoAnnotation(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-bank-all.xml");
        AccountServiceImpl accountService = applicationContext.getBean("accountService", AccountServiceImpl.class);
        try{
            accountService.transfer("act001","act002",1000);
            System.out.println("转账成功");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void testNoXml(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        AccountServiceImpl accountService = applicationContext.getBean("accountService", AccountServiceImpl.class);
        try{
            accountService.transfer("act001","act002",1000);
            System.out.println("转账成功");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void testSave(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-bank.xml");
        AccountServiceImpl accountService = applicationContext.getBean("accountService", AccountServiceImpl.class);
        try{
            Account act003 = new Account("act003", 20000);
            accountService.save(act003);

        }catch (Exception e){
            e.printStackTrace();
        }
    }            

    @Test
    public void testTransfer(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-bank.xml");
        AccountServiceImpl accountService = applicationContext.getBean("accountService", AccountServiceImpl.class);
        try{
            accountService.transfer("act001","act002",1000);
            System.out.println("转账成功");
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
