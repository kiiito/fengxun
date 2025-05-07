package com.hucong.jdbc;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class jdbc01 {
    public static void main(String[] args) throws SQLException {

        //1 注册驱动
        Driver driver = new Driver();//创建driver对象
        //2 得到连接
        String url = "jdbc:mysql://localhost:3306/db02?serverTimezone=UTC&useSSL=false&characterEncoding=utf8";
        //将用户名和密码放入properties 对象
        Properties properties = new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password","hc");
        Connection connect = driver.connect(url, properties);
        //3 执行SQL
        String sql = "insert into actor values(2,'梁朝伟','男','1980-12-11','120')";
        //String sql = "delete from actor where id= 4";
        //String sql = "update actor set id = 1 where name ='刘德华'";
        //用于执行静态SQL语句并返回其生成的结果对象
        Statement statement = connect.createStatement();
        int l = statement.executeUpdate(sql);
        System.out.println(l > 0 ? "成功":"失败");
        //4 关闭连接资源
        statement.close();
        connect.close();
    }
}
