package com.hc.annotate;

import org.springframework.stereotype.Service;

@Service("orderBean")
public class order {
    public order() {
        System.out.println("order");
    }
}
