package com.hucong.interface_;

public class interface02 {
    public static void main(String[] args) {
        MysqlDB mysqlDB = new MysqlDB();
        t(mysqlDB);
        OracleDB oracleDB = new OracleDB();
        t(oracleDB);
    }
    //实现接口调用方法
    public static void t(DBInterface db){
        db.connect();
        db.close();
    }
}