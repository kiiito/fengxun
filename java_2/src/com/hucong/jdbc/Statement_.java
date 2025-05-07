package com.hucong.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Statement_ {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        //SQL 注入 1' or     or '1'= '1

        System.out.print("请输入用户名: ");
        String name = scanner.nextLine();//必须用nextLine next当接收到空格或者`就表示结束
        System.out.print("请输入 密码: ");
        String pwd = scanner.nextLine();
        Properties properties = new Properties();
        properties.load(new FileInputStream("src//com/hucong/jdbc/mysql.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        //加载驱动
        Class.forName(driver);//建议写上

        //得到连接
        String sql = "select name,pwd from admin where name = '" + name + "' and pwd= '" + pwd + "'";
        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        //执行给定的SQL语句 该语句返回单个ResultSet对象
        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()) {
            System.out.println("登入成功");
        }else {
            System.out.println("登入失败");
        }
        //关闭连接
        resultSet.close();
        statement.close();
        connection.close();
    }
}
