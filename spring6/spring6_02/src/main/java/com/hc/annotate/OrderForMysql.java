package com.hc.annotate;


import org.springframework.stereotype.Repository;

@Repository(value = "orderForMysql")
public class OrderForMysql implements OrderDao {
    @Override
    public void info() {
        System.out.println("mysql数据库正在保存信息");
    }
}
