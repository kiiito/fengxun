package com.hucong.jdbc;

import com.mysql.jdbc.Statement;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Transaction_ {
    @Test
    public void testNot() throws SQLException {
        String sql1 = "update many set balance = balance - 100 where id = 1";
        String sql2 = "update many set balance = balance + 100 where id = 2";
        PreparedStatement preparedStatement = null;
        Connection connection = JDBCUtils.getConnection();
        preparedStatement = connection.prepareStatement(sql1);
        int i = preparedStatement.executeUpdate();

        int k = 1 / 0;//抛出异常 下面代码不执行

        System.out.println(i > 0 ? "执行成功" : "执行失败");
        preparedStatement = connection.prepareStatement(sql2);
        int j = preparedStatement.executeUpdate();
        System.out.println(j > 0 ? "执行成功" : "执行失败");
        JDBCUtils.close((ResultSet) null, (Statement) preparedStatement, connection);
    }

    @Test
    public void testTrue() throws SQLException {
        String sql1 = "update many set balance = balance - 100 where id = 1";
        String sql2 = "update many set balance = balance + 100 where id = 2"; //获取连接
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        //组织SQL
        try {
            connection = JDBCUtils.getConnection();//默认情况下 是默认自动提交
            connection.setAutoCommit(false);//将默认提交设置为false
            preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.executeUpdate();
           // int i = 1 / 0;//抛出异常 下面代码不执行 进入到catch当中
            preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            //这里我们可以进行回滚 即撤销执行的SQL
            //若没有设置保存点 默认回滚到事务开始状态
            System.out.println("执行错误，进行回滚");
            connection.rollback();
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.close(null, (Statement) preparedStatement, connection);
        }
    }
}
