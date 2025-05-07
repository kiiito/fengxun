package com.hucong.interface_;

public class OracleDB implements DBInterface{
    @Override
    public void connect() {
        System.out.println("Oracle正在连接");
    }

    @Override
    public void close() {
        System.out.println("Oracle正在关闭");
    }
}
