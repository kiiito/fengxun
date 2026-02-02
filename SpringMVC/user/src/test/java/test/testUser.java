package test;

import com.hc.mybatis.pojo.User;
import com.hc.mybatis.service.UserServiceImpl;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class testUser {
    @Test
    public void testSelectAll(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("springmvc.xml");
        UserServiceImpl userService = applicationContext.getBean("userService", UserServiceImpl.class);
        List<User> users = userService.selectAll();
        users.forEach(user -> {
            System.out.println(user);
        });
    }
}
