package com.hc.aspectj.affairs;

import org.springframework.stereotype.Service;

@Service("oderService")
public class OderService {
    public  void transfer(){
        System.out.println("正在转账");
    }
}
