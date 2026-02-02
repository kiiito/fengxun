package com.hc.proxy;

public class OrderServiceImpl implements OrderService{
    @Override
    public void info() {
        System.out.println("该产品....");
    }

    @Override
    public String getName() {
        System.out.println("获取到了返回值");
        return "甘雨";
    }
}
