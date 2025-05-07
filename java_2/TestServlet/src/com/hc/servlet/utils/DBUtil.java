package com.hc.servlet.utils;

import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBUtil {
  private static ResourceBundle bundle = ResourceBundle.getBundle("src/jdbc.properties");
  private  static  String driver = bundle.getString("driver");
  private  static  String url = bundle.getString("url");
  private  static  String user = bundle.getString("username");
  private  static  String password = bundle.getString("password");
  static {
     try {
        Class.forName(driver);
     } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
     }
  }

  public static Connection getConnection() throws SQLException {
     Connection connection = DriverManager.getConnection(url, user, password);
     return  connection;
  }
   public static void close(ResultSet resultSet, Statement statement, Connection connection){
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
