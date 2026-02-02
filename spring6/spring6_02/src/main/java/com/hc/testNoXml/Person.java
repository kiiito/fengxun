package com.hc.testNoXml;

import org.springframework.stereotype.Component;

@Component
public class Person {
    public Person() {
        System.out.println("person构造器执行");
    }
    public void info(){
        System.out.println("person info执行");
    }
}
