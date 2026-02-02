package com.hc.springboot;

import com.hc.springboot.builderpattern.User;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserBuilderTest {
    public static void main(String[] args) {
        User user = User.builder()
                .name("hc")
                .age(18)
                .addPhone("1234")
                .addPhone("1234455")
                .build();
        user.print();
        System.out.println(user);
    }
    }

