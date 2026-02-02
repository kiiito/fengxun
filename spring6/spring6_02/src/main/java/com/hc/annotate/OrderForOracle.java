package com.hc.annotate;

import org.springframework.stereotype.Repository;

@Repository
public class OrderForOracle implements OrderDao{
    @Override
    public void info() {
        System.out.println("oracle正在保存数据");
    }
}
