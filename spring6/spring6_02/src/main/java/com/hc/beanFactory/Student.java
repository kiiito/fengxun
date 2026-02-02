package com.hc.beanFactory;

import java.util.Date;

public class Student {
    private Date date;

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Student{" +
                "date=" + date +
                '}';
    }
}
