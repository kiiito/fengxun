package com.action;

import com.exception.AppException;
import com.exception.MoneyNotEnoughException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

//@WebServlet({"/transfer"})
public class AccountTransferServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        //获取转账相关信息
        String fromAction = req.getParameter("fromAction");
        String toAction = req.getParameter("toAction");
        Double money = Double.parseDouble(req.getParameter("money"));

        //编写转账业务逻辑代码 连接数据库 进行转账操作
        //1 转账之前判断是否余额充足
        Connection conn = null;
        PreparedStatement ps = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;
        ResultSet rs = null;
        try {
            //注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接
            String url = "jdbc:mysql://localhost:3306/mvc?rewriteBatchedStatements=true&serverTimezone=UTC&useSSL=false&characterEncoding=utf8";
            String user = "root";
            String password = "hc";
            conn = DriverManager.getConnection(url, user, password);

            String sql1 = "select balance from users where actno = ?";
            ps = conn.prepareStatement(sql1);
            ps.setString(1, fromAction);
            rs = ps.executeQuery();
            //处理结果集
            if (rs.next()) {
                double balance = rs.getDouble("balance");
                if (balance < money) {
                    throw new MoneyNotEnoughException("对不起，余额不足");
                }

                //开启事务
                conn.setAutoCommit(false);

                //余额充足
                String sql2 = "update users set balance = balance - ? where actno = ?";
                ps2 = conn.prepareStatement(sql2);
                ps2.setDouble(1, money);
                ps2.setString(2, fromAction);
                int count = ps2.executeUpdate();

                String sql3 = "update users set balance = balance + ? where actno = ?";
                ps3 = conn.prepareStatement(sql3);
                ps3.setDouble(1, money);
                ps3.setString(2, toAction);
                //累加
                count += ps3.executeUpdate();

                if (count != 2) {
                    throw new AppException("APP异常，请联系管理员");
                }

                //手动提前事务
                conn.commit();
                //转账成功
                out.println("转账成功");
            }
        } catch (Exception e) {
            //异常处理
            //遇到异常进行回滚
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            out.println(e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (ps2 != null) {
                try {
                    ps2.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (ps3 != null) {
                try {
                    ps3.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
