package com.hc.servlet.web.action;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class DetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String deptno = req.getParameter("deptno");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/db02?serverTimezone=UTC&useSSL=false&characterEncoding=utf8&rewriteBatchedStatements=true";
            String user = "root";
            String password = "hc";
            connection = DriverManager.getConnection(url, user, password);
            String sql = "select *from dept02 where deptno = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, deptno);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                deptno = resultSet.getString("deptno");
                String dname = resultSet.getString("dname");
                String loc = resultSet.getString("loc");
                // out.println(deptno + " " + dname + " " + loc);

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("    <meta charset='utf-8'>");
                out.println("    <title>部门详情</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>部门详情</h1>");
                out.println("<hr>");
                out.println("<p>部门编号：" + deptno + " <br> 部门名称：" + dname + "<br> 部门位置：" + loc + "</p>");
                out.println("<input type='button' value='后退' onclick='window.history.back()'/>");
                out.println("</body>");
                out.println("</html>");
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
//

    }
}
