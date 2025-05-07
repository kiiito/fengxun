package com.hucong.jdbc;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class PreparedStatement_ {
    public static void main(String[] args)throws Exception {
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
        //使用占位符？代替
        String sql = "select name,pwd from admin where name = ? and pwd= ?";
        Connection connection = DriverManager.getConnection(url, user, password);
        //preparedStatement 对象实现了 PreparedStatement 接口的实现类的对象
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //给？赋值 从1 开始 1 代表name 2 代表了pwd
        preparedStatement.setString(1,name);
        preparedStatement.setString(2,pwd);
        //executeQuery() 是查询语句 executeUpdate() 是执行dml(update insert delete)
        //这里执行 executeQuery 就不需要在填入sql
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            System.out.println("登入成功");
        }else {
            System.out.println("登入失败");
        }
        //关闭连接
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
