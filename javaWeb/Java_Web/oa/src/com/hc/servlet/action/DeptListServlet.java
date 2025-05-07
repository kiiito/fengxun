package com.hc.servlet.action;

import com.hc.servlet.utils.DBUtil;
import com.hc.servlet.utils.JDBCUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class DeptListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <title>list</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1 align='center'>部门列表</h1>");
        out.println("<hr>");
        out.println(" <table border='1px' align='center' width='50%'>");
        out.println("    <tr>");
        out.println("        <th>序号</th>");
        out.println("        <th>部门编号</th>");
        out.println("        <th>部门名称</th>");
        out.println("        <th>操作</th>");
        out.println("    </tr>");

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //获取连接
             connection = JDBCUtils.getConnection();
            System.out.println("连接成功！");
            String sql = "select deptno,dname,loc from dept02";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            int i = 0;
            while (resultSet.next()) {
                String deptno = resultSet.getString("deptno");
                String dname = resultSet.getString("dname");
                String loc = resultSet.getString("loc");
                out.println("    <tr>");
                out.println("        <td>" + (++i) + "</td>");
                out.println("        <td>" + deptno + "</td>");
                out.println("        <td>" + dname + "</td>");
                out.println("        <td>");
                out.println("            <a href=''>删除</a>");
                out.println("            <a href='edit.html'>修改</a>");
                out.println("            <a href='detail.html'>详情</a>");
                out.println("        </td>");
                out.println("    </tr>");

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
        } finally {

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
        out.println(" ");
        out.println("</table>");

        out.println("<hr>");
        out.println("<a href='add.html'>新增部门</a>");
        out.println("</body>");
        out.println("</html>");

    }
}
