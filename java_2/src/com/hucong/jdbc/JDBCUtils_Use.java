package com.hucong.jdbc;

import com.mysql.jdbc.Statement;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtils_Use {

    @Test
    public void testDML() {
        //获取连接
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        //组织SQL
        String sql = "update actor set name = ? where id = ?";
        try {
            connection =JDBCUtils.getConnection();
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
            JDBCUtils.close( null, (Statement) preparedStatement,connection);
        }


    }
    @Test
    public void testSelect() throws SQLException {
        Connection connection = JDBCUtils.getConnection();
        String sql = "select id from actor where name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        String name = "黎明";
        preparedStatement.setString(1,name);
        boolean execute = preparedStatement.execute();
        if(execute){
            System.out.println("查询成功");
        }else {
            System.out.println("查询失败");
        }
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            String string = resultSet.getString(1);
            System.out.println(string);
        }
        JDBCUtils.close(resultSet, (Statement) preparedStatement,connection);
    }
}
