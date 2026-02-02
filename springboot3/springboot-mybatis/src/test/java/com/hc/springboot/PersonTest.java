package com.hc.springboot;

import com.hc.springboot.bean.Person;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PersonTest {
    public static void main(String[] args) {
        Person person = new Person();
        person.setName("hc");
        person.setAge(18);
        Person person1 = new Person("hc",18);
        System.out.println(person);
        System.out.println(person1);
        System.out.println(person.equals(person1));
    }
}
