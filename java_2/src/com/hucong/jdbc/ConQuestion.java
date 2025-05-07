package com.hucong.jdbc;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class ConQuestion {
    @Test
    public void testCo(){
        //用传统方法连接5000次数据库
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++) {
            Connection connection = JDBCUtils.getConnection();
            // 下面进行 SQL一系列操作....

            //关闭 如果没有关闭连接 将会报错 Too many connections
           JDBCUtils.close(null,null,connection);
        }
        long end = System.currentTimeMillis();
        System.out.println("执行所需时间" + (end - start));
    }
}
