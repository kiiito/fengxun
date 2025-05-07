package com.hc.jsp.action;

import com.hc.jsp.bean.Dept;
import com.hc.jsp.utils.JDBCUtilsByDruid;
import java.sql.Statement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.out;

@WebServlet({"/dept/list", "/dept/edit", "/dept/add", "/dept/detail","/dept/del","/dept/modify"})
public class DeptServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取session（不需要新建的） 获取不到就返回null
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("username") != null){
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        String servletPath = req.getServletPath();
        if ("/dept/list".equals(servletPath)) {
            doList(req, resp);
        } else if ("/dept/detail".equals(servletPath)) {
            doDetail(req,resp);
        }else if ("/dept/del".equals(servletPath)){
            doDel(req,resp);
        } else if ("/dept/edit".equals(servletPath)) {
           doEdit(req,resp);
        } else if ("/dept/modify".equals(servletPath)) {
            doModify(req,resp);
        } else if ("/dept/add".equals(servletPath)) {
            doAdd(req,resp);
        }
        }else {
            resp.sendRedirect(req.getContextPath());//访问项目根节点 本就是欢迎页面
        }
    }

    private void doAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String deptno = req.getParameter("deptno");
        String dname = req.getParameter("dname");
        String loc = req.getParameter("loc");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            String sql = "insert into dept02(deptno, dname, loc) values(?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,deptno);
            preparedStatement.setString(2,dname);
            preparedStatement.setString(3,loc);
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtilsByDruid.close(resultSet,preparedStatement,connection);
        }
        if (count == 1){
            try {
                resp.sendRedirect(req.getContextPath()+"/dept/list");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            try {
                req.getRequestDispatcher("/error.jsp").forward(req,resp);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }

    private void doModify(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String deptno = req.getParameter("deptno");
        String dname = req.getParameter("dname");
        String loc = req.getParameter("loc");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            String sql = "update dept02 set dname = ?,loc = ? where deptno = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,dname);
            preparedStatement.setString(2,loc);
            preparedStatement.setString(3,deptno);
            count = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtilsByDruid.close(resultSet,preparedStatement,connection);
        }
        if (count == 1){
            try {
                //没有存储数据到请求域当中 需要用重定向
                resp.sendRedirect(req.getContextPath()+"/dept/list");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            try {
                req.getRequestDispatcher("/error.jsp").forward(req,resp);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private void doEdit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Dept dept = new Dept();
        String deptno = req.getParameter("deptno");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection =JDBCUtilsByDruid.getConnection();
            String sql = "select deptno,dname,loc from dept02 where deptno = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,deptno);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                deptno = resultSet.getString("deptno");
                String dname = resultSet.getString("dname");
                String loc = resultSet.getString("loc");
                dept = new Dept(deptno, dname, loc);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtilsByDruid.close(resultSet,preparedStatement,connection);
        }
        try {
            req.setAttribute("dept",dept);
            req.getRequestDispatcher("/edit.jsp").forward(req,resp);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void doDel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String deptno = req.getParameter("deptno");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;

        try {
            connection = JDBCUtilsByDruid.getConnection();
            String sql = "delete from dept02 where deptno = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,deptno);
            count = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtilsByDruid.close(resultSet,preparedStatement,connection);
        }
        if (count == 1){
            try {
                resp.sendRedirect(req.getContextPath()+"/dept/list");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else {
            try {
                req.getRequestDispatcher("/error.jsp").forward(req,resp);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void doDetail(HttpServletRequest req, HttpServletResponse resp) {
        Dept dept = new Dept();
        String deptno = req.getParameter("deptno");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = JDBCUtilsByDruid.getConnection();
            String sql = "select deptno,dname,loc from dept02 where deptno = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,deptno);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                deptno = resultSet.getString("deptno");
                String dname = resultSet.getString("dname");
                String loc = resultSet.getString("loc");
                 dept = new Dept(deptno, dname, loc);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBCUtilsByDruid.close(resultSet,preparedStatement,connection);
        }
        req.setAttribute("dept",dept);
        try {
            req.getRequestDispatcher("/detail.jsp").forward(req,resp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void doList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //创建容器来存储部门
        List<Dept> depts = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        //out.println("连接成功" );
        try {
            connection= JDBCUtilsByDruid.getConnection();
            String sql = "select deptno,dname,loc from dept02";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            int i = 0;
            while (resultSet.next()) {
                String deptno = resultSet.getString("deptno");
                String dname = resultSet.getString("dname");
                String loc = resultSet.getString("loc");

                //将以上对象封装成对象
                Dept dept = new Dept();
                dept.setDeptno(deptno);
                dept.setDname(dname);
                dept.setLoc(loc);

                //将部门对象放入部门集合当中
                depts.add(dept);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtilsByDruid.close(resultSet, preparedStatement, connection);
//
        }
        //将一个集合放入请求域
        req.setAttribute("deptList",depts);

        //转发
        req.getRequestDispatcher("/list.jsp").forward(req,resp);
    }
}
