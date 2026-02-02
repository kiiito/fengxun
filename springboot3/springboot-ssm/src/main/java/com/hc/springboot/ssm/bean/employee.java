package com.hc.springboot.ssm.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class employee {
    private int id;
    private String name;
    private int age;
    private String position;

    public  employee( String name, int age, String position) {
        this.name = name;
        this.age = age;
        this.position = position;
    }
}


