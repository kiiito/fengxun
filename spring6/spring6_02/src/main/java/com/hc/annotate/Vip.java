package com.hc.annotate;

import org.springframework.stereotype.Controller;

@Controller("vipBean")
public class Vip {
    public Vip() {
        System.out.println("vip");
    }
}
