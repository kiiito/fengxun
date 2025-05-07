package com.hucong.jdbc;

import com.mysql.jdbc.Statement;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 一个工具类，用于完成MySQL的连接和关闭资源
 */
public class JDBCUtils {
    private static String user;
    private static String password;
    private static String url;
    private static String driver;


    static {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/com/hucong/jdbc/mysql.properties"));
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            url = properties.getProperty("url");
            driver = properties.getProperty("driver");

        } catch (IOException e) {
            //在实际开发中 我们通常将编译异常转成运行异常 这样调用者既可以捕获该异常 也可以默认处理该异常
            throw new RuntimeException(e);
        }

    }

    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*
        1 ResultSet 返回结果集
        2 Statement 或 PreparedStatement
        3 Connection
        4 如果需要关闭资源 就传入对象 否则就传入null
     */
    public static void close(ResultSet resultSet, Statement statement,Connection connection){
        try {
            if (resultSet != null){
                resultSet.close();
            }
            if (statement != null){
                statement.close();
            }
            if (connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
