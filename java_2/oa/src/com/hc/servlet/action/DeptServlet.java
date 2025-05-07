package com.hc.servlet.action;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet({"/dept/list", "/dept/save","/dept/edit","/dept/modify","/dept/detail","/dept/delete"})
//模糊匹配
//只要是"/dept/*"下都匹配这个servlet
//@WebServlet("/dept/*")
public class DeptServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getServletPath();
        if ("/dept/list".equals(servletPath)) {
            doList(req, resp);
        } else if ("/dept/save".equals(servletPath)) {
            doSave(req, resp);
        } else if ("/dept/edit".equals(servletPath)) {
            doEdit(req, resp);
        } else if ("/dept/modify".equals(servletPath)) {
            doModify(req, resp);
        } else if ("/dept/detail".equals(servletPath)) {
            doDetail(req, resp);
        } else if ("/dept/delete".equals(servletPath)) {
            doDel(req, resp);
        }
    }

    private void doList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        String contextPath = req.getContextPath();

        out.println("<!DOCTYPE html>");
        out.println("<html lang='en'>");
        out.println("<head>");
        out.println("    <meta charset='UTF-8'>");
        out.println("    <title>list</title>");
        out.println("</head>");
        out.println("<body>");
        out.print("<script type='text/javascript'>");
        out.print("    function del(dno){");
        out.print("        if(window.confirm('亲，删了不可恢复哦！')){");
        out.print("            document.location.href = '/oa/dept/del?deptno=' + dno");
        out.print("        }");
        out.print("    }");
        out.print("</script>");

        out.println("<h1 align='center'>部门列表</h1>");
        out.println("<hr>");
        out.println(" <table border='1px' align='center' width='50%'>");
        out.println("    <tr>");
        out.println("        <th>序号</th>");
        out.println("        <th>部门编号</th>");
        out.println("        <th>部门名称</th>");
        out.println("        <th>操作</th>");
        out.println("    </tr>");


        try {
            //获取连接
            Class.forName("com.mysql.jdbc.Driver");
//
            String url = "jdbc:mysql://localhost:3306/db02?serverTimezone=UTC&useSSL=false&characterEncoding=utf8&rewriteBatchedStatements=true";
            String user = "root";
            String password = "hc";
            connection = DriverManager.getConnection(url,user,password);
//            connection = JDBCUtilsByDruid.getConnection();
//            connection = JDBCUtils.getConnection();
//            connection = DBUtil.getConnection();
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
                out.println("            <a href='javascript:void(0)' onclick='del("+deptno+")'>删除</a>");
                out.println("            <a href='"+contextPath+"/dept/edit?deptno="+deptno+"'>修改</a>");
                out.println("            <a href='"+contextPath+"/dept/detail?deptno="+deptno+"'>详情</a>");
                out.println("        </td>");
                out.println("    </tr>");

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
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
        out.println("<a href='"+contextPath+"/add.html'>新增部门</a>");
        out.println("</body>");
        out.println("</html>");
    }

    private void doSave(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

    private void doEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

    private void doModify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
            String sql = "update dept02 set dname = ?,loc = ? where deptno = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,dname);
            preparedStatement.setString(2,loc);
            preparedStatement.setString(3,deptno);
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
                //req.getRequestDispatcher("/dept/list").forward(req,resp);

                //这里最好使用重定向
                resp.sendRedirect(req.getContextPath()+"/dept/list");
            }else {
                //req.getRequestDispatcher("/error.html").forward(req,resp);
                //这里最好使用重定向
                resp.sendRedirect(req.getContextPath()+"/error.html");
            }
        }
    }

    private void doDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
    }

    private void doDel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count =0;
        String deptno = req.getParameter("deptno");
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/db02?serverTimezone=UTC&useSSL=false&characterEncoding=utf8&rewriteBatchedStatements=true";
            String user = "root";
            String password = "hc";
            connection = DriverManager.getConnection(url,user,password);
            String sql = "delete from dept02 where deptno = ?";
            preparedStatement =  connection.prepareStatement(sql);
            preparedStatement.setString(1,deptno);
            count  = preparedStatement.executeUpdate();
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
                //req.getRequestDispatcher("/dept/list").forward(req,resp);
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
