package com.hucong.interface_;

public class MysqlDB implements DBInterface{
    //必须按规定的方法名来写方法

    @Override
    public void connect() {
        System.out.println("MySQL正在连接");
    }

    @Override
    public void close() {
        System.out.println("MySQL正在关闭");
    }
}
