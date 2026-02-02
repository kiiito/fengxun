package com.hc.test;

import com.hc.service.impl.AccountServiceImpl;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SMTest {
    @Test
    public void testSM(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        AccountServiceImpl accountService = applicationContext.getBean("accountService", AccountServiceImpl.class);
        try{
            accountService.transfer("act001","act002",1000);
            System.out.println("转账成功");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
