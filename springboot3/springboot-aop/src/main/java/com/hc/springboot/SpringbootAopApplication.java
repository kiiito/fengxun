package com.hc.springboot;

import com.hc.springboot.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringbootAopApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringbootAopApplication.class, args);
		UserService userService = context.getBean(UserService.class);
		userService.save(111L, "hc");
		userService.delete(111L);
		context.close();
	}

}
