package com.hc.servlet;

import com.hc.servlet.utils.DBUtil;
import com.hc.servlet.utils.JDBCUtils;
import com.mysql.jdbc.Statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class test {
    public static void main(String[] args) throws SQLException {
        Connection connection = JDBCUtils.getConnection();
//        }
        String sql = "select deptno,dname,loc from dept02";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet  resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String deptno = resultSet.getString("deptno");
            String dname = resultSet.getString("dname");
            String loc = resultSet.getString("loc");
            System.out.println(deptno +" " + dname);
        }
        JDBCUtils.close(resultSet, (Statement) preparedStatement,connection);
//        DBUtil.close(resultSet, (Statement) preparedStatement,connection);
    }

}
