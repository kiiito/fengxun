package com.hucong.jdbc;

import com.mysql.jdbc.Statement;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;

public class JDBCUtilsByDruid_use {
    @Test
    public void test() throws SQLException {
        //获取连接
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        //组织SQL
        String sql = "update actor set name = ? where id = ?";
        try {
            connection = JDBCUtilsByDruid.getConnection();
            System.out.println(connection.getClass());//class com.alibaba.druid.pool.DruidPooledConnection
            preparedStatement = connection.prepareStatement(sql);
            String name ="黎明";
            int id = 2;
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            int i = preparedStatement.executeUpdate();
            System.out.println(i > 0 ? "执行成功" : "执行失败");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtilsByDruid.close(null,null,connection);
        }


    }
    @Test
    public void testOld() throws SQLException {
        //获取连接
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //组织SQL
        String sql = "select * from actor where id >= ?";
        ArrayList<Actor> list = new ArrayList<>();//创建一个ArrayList数组 将actor对象放进去
        try {
            connection = JDBCUtilsByDruid.getConnection();
            System.out.println(connection.getClass());//class com.alibaba.druid.pool.DruidPooledConnection
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 1);
            resultSet = preparedStatement.executeQuery();
            //进行while循环取出数据
            while (resultSet.next()){//让光标向后移动 如果没有更多行 则返回 false
                int id = resultSet.getInt(1);//获取该行的第一列
                String name = resultSet.getString(2);
                String sex = resultSet.getString(3);
                Date date = resultSet.getDate(4);
                String phone = resultSet.getString(5);
                //将ResultSet返回的结果集用数组存储 这样就可以解决connection关闭 ResultSet不能使用的缺陷
                list.add(new Actor(id,name,sex,date,phone));
            }
            System.out.println(list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtilsByDruid.close(null,null,connection);
        }


    }
}
