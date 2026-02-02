package com.hc.springboot.ssm.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Students {
    private int id;
    private String name;
    private String number;
    private String smajor;
    private int age;
}

