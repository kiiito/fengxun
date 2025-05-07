package com.hucong.jdbc;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Scanner;

public class PreparedStatement_DML {
    public static void main(String[] args)throws Exception {
        Scanner scanner = new Scanner(System.in);
        //SQL 注入 1' or     or '1'= '1

        System.out.print("请输入用户名: ");
        String name = scanner.nextLine();//必须用nextLine next当接收到空格或者`就表示结束
//        System.out.print("请输入 密码: ");
//        String pwd = scanner.nextLine();
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
        //String sql = "insert into admin values(?,?)";
        //String sql = "update admin set  pwd=? where name=?";
        String sql = "delete from admin where name=?";

        Connection connection = DriverManager.getConnection(url, user, password);
        //preparedStatement 对象实现了 PreparedStatement 接口的实现类的对象
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

       // preparedStatement.setString(1,pwd);
        preparedStatement.setString(1,name);
        int i = preparedStatement.executeUpdate();
        System.out.println(i>0?"执行成功":"执行失败");

        //关闭连接
        preparedStatement.close();
        connection.close();
    }

}
