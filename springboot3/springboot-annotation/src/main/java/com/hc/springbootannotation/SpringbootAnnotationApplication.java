package com.hc.springbootannotation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Date;

/**
 * 本质上是一个注解类
 */
@SpringBootApplication
public class SpringbootAnnotationApplication {
    @Bean
    public Date getDate(){
        return new Date();
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringbootAnnotationApplication.class, args);
        Object sqlSessionFactory = applicationContext.getBean("sqlSessionFactory");
        System.out.println(sqlSessionFactory);

        Object transactionManager = applicationContext.getBean("transactionManager");
        System.out.println(transactionManager);
        applicationContext.close();

    }

}
