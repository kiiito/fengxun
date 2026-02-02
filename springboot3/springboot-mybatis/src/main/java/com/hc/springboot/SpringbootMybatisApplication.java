package com.hc.springboot;

import com.hc.springboot.bean.Vip;
import com.hc.springboot.service.VipService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@MapperScan(basePackages = {"com.hc.springboot.repository"})//扫描mapper
@SpringBootApplication
public class SpringbootMybatisApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringbootMybatisApplication.class, args);
        VipService vipService = context.getBean(VipService.class);
//        vipService.save(new Vip("eula","12","2008-08-08"));
//        vipService.save(new Vip("ganyu","18","2008-08-08"));
        vipService.findAll().forEach(
                vip -> System.out.println(vip.toString())
        );

        context.close();
    }

}
