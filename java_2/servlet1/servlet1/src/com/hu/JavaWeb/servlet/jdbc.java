package com.hu.JavaWeb.servlet;

import java.sql.*;

public class jdbc {
    public static void main(String[] args) {
        try (Connection con = getConnection()) {
            System.out.println("连接成功: " + con);

             //插入
//            insertRecord(con);
            // 修改
//            updateRecord(con);
            // 删除
//            deleteRecord(con);
            // 查询
            queryRecords(con);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws Exception {
        // 1. 使用正确的驱动类名
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/mvc?rewriteBatchedStatements=true&serverTimezone=UTC&useSSL=false&characterEncoding=utf8";
        String user = "root";
        String password = "hc";
        return DriverManager.getConnection(url, user, password);
    }

    // 插入记录
    private static void insertRecord(Connection con) throws SQLException {
        String sql = "INSERT INTO EMPLOYEE (EMP_ID, EMP_NAME, JOB, SALARY, DEPT) "
                + "VALUES ('5', '邓紫棋', '歌手', 8300, '30')";

        try (Statement sta = con.createStatement()) {
            int rows = sta.executeUpdate(sql);  // 使用executeUpdate
            System.out.println("插入成功，影响行数: " + rows);
        }
    }

    // 修改记录
    private static void updateRecord(Connection con) throws SQLException {
        String sql = "UPDATE EMPLOYEE SET EMP_NAME='颜人中', "
                + "JOB='歌手', SALARY=5000, DEPT='20' "
                + "WHERE EMP_ID=5";

        try (Statement sta = con.createStatement()) {
            int rows = sta.executeUpdate(sql);
            System.out.println("修改成功，影响行数: " + rows);
        }
    }

    // 删除记录
    private static void deleteRecord(Connection con) throws SQLException {
        String sql = "DELETE FROM EMPLOYEE WHERE EMP_ID=5";

        try (Statement sta = con.createStatement()) {
            int rows = sta.executeUpdate(sql);
            System.out.println("删除成功，影响行数: " + rows);
        }
    }

    // 查询记录
    private static void queryRecords(Connection con) throws SQLException {
        String sql = "SELECT * FROM EMPLOYEE";

        try (Statement sta = con.createStatement();
             ResultSet rs = sta.executeQuery(sql)) {

            System.out.println("\n当前员工数据:");
            while (rs.next()) {
                System.out.printf(
                        "ID: %s, 姓名: %s, 职位: %s, 薪资: %d, 部门: %s%n",
                        rs.getString("EMP_ID"),
                        rs.getString("EMP_NAME"),
                        rs.getString("JOB"),
                        rs.getInt("SALARY"),
                        rs.getString("DEPT")
                );
            }
        }
    }
    }
