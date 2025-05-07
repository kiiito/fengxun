package com.hucong.jdbc;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class ResultSet_ {
    public static void main(String[] args) throws Exception{
        Properties properties = new Properties();
        properties.load(new FileInputStream("src//com/hucong/jdbc/mysql.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        //加载驱动
        Class.forName(driver);//建议写上

        //得到连接
        String sql = "select * from actor";
        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        //执行给定的SQL语句 该语句返回单个ResultSet对象
        ResultSet resultSet = statement.executeQuery(sql);
        //进行while循环取出数据
        while (resultSet.next()){//让光标向后移动 如果没有更多行 则返回 false
            int id = resultSet.getInt(1);//获取该行的第一列
            String name = resultSet.getString(2);
            String sex = resultSet.getString(3);
            Date date = resultSet.getDate(4);
            System.out.println(id + "\t" + name + "\t" + sex + "\t" + date);
        }

        //关闭连接
        resultSet.close();
        statement.close();
        connection.close();
    }

}
