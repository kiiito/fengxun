package com.hc.springboot;

import com.hc.springboot.builderpattern.Person;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PersonBuilderTest {
    public static void main(String[] args) {
        Person build = Person.builder()
                .name("eula")
                .build();
        System.out.println(build);
    }
}
