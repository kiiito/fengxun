package com.hc.annotate;

import org.springframework.stereotype.Repository;

@Repository("studentBean")
public class Student {
    public Student() {
        System.out.println("student");
    }
}
