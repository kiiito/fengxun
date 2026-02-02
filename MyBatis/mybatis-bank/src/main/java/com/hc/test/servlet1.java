package com.hc.test;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.sql.DataSource;
import java.util.ResourceBundle;

// 引入数据库连接池相关类
import javax.naming.InitialContext;
import javax.naming.NamingException;

@WebServlet("/aa")
public class servlet1 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain;charset=utf-8");
        PrintWriter out = resp.getWriter();

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // 使用数据库连接池获取连接
            DataSource dataSource = getDataSource();
            conn = dataSource.getConnection();

            // 准备 SQL 查询
            String sql = "SELECT * FROM tb_user WHERE userName = ? AND userPassword = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            // 执行查询
            rs = pstmt.executeQuery();

            if (rs.next()) {
                // 登录成功，返回用户名用于后续页面显示
                out.print("success:" + rs.getString("userName"));
            } else {
                // 登录失败
                out.print("error:用户名或密码错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.print("error:系统错误 - " + e.getMessage());
        } finally {
            // 关闭资源
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 获取数据库连接池的 DataSource
    private DataSource getDataSource() throws NamingException {
        // 从配置文件中获取 JNDI 名称
        ResourceBundle rb = ResourceBundle.getBundle("dbconfig");
        String jndiName = rb.getString("jndi.name");

        // 使用 JNDI 查找 DataSource
        InitialContext ctx = new InitialContext();
        DataSource dataSource = (DataSource) ctx.lookup(jndiName);
        return dataSource;
    }
}
