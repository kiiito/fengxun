package com.hc.servlet.web.action;

import com.hc.servlet.utils.DBUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class add extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        String deptno = req.getParameter("deptno");
        String dname = req.getParameter("dname");
        String loc = req.getParameter("loc");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/db02?serverTimezone=UTC&useSSL=false&characterEncoding=utf8&rewriteBatchedStatements=true";
            String user = "root";
            String password = "hc";
            connection = DriverManager.getConnection(url,user,password);
            out.println(connection);
//            System.out.println(connection);
//            String sql = "insert into dept02(deptno,dname,loc) values (?,?,?)";
//            preparedStatement =  connection.prepareStatement(sql);
//            preparedStatement.setString(1,deptno);
//            preparedStatement.setString(2,dname);
//            preparedStatement.setString(3,loc);
//            count = preparedStatement.executeUpdate();
            String sql = "insert into dept02(deptno, dname, loc) values(?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, deptno);
            preparedStatement.setString(2, dname);
            preparedStatement.setString(3, loc);
            count = preparedStatement.executeUpdate();

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

           if (count == 1){
                //将该请求转发给DeptListServlet再次显示
                //底层已经删除了一条数据 再次调用DeptListServlet将会是删除完数据的页面
               // req.getRequestDispatcher("/dept/list").forward(req,resp);

               //这里最好使用重定向
               resp.sendRedirect(req.getContextPath()+"/dept/list");
            }else {
               // req.getRequestDispatcher("/error.html").forward(req,resp);
               //这里最好使用重定向
               resp.sendRedirect(req.getContextPath()+"/error.html");
            }
        }


    }



}