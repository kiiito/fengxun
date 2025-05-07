package com.hc.servlet.web.action;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class edit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String deptno = req.getParameter("deptno");
        String contextPath = req.getContextPath();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("  <meta charset='utf-8'>");
        out.println("  <title>修改部门</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>修改部门</h1>");
        out.println("<hr>");
        out.println("<form action='"+contextPath+"/dept/modify' method='post'>");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/db02?serverTimezone=UTC&useSSL=false&characterEncoding=utf8&rewriteBatchedStatements=true";
            String user = "root";
            String password = "hc";
            connection = DriverManager.getConnection(url,user,password);
            String sql = "select *from dept02 where deptno = ?";
            preparedStatement =  connection.prepareStatement(sql);
            preparedStatement.setString(1,deptno);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                deptno = resultSet.getString("deptno");
                String dname = resultSet.getString("dname");
                String loc = resultSet.getString("loc");

                out.println("                部门编号<input type='text' name='deptno' value=" + deptno + " readonly/><br>");
                out.println("                部门名称<input type='text' name='dname' value=" + dname + "><br>");
                out.println("                部门位置<input type='text' name='loc' value=" + loc + "><br>");

            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if (preparedStatement != null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        out.println("  <input type='submit' value='修改'/><br>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");

    }
}
