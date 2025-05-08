package com.hc.jsp.action;

import com.hc.jsp.utils.JDBCUtilsByDruid;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.security.Provider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet({"/user/login", "/user/exit"})
public class userServlet extends HttpServlet {
    boolean key = false;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getServletPath();
        if ("/user/login".equals(servletPath)) {
            doLogin(req, resp);
        } else if ("/user/exit".equals(servletPath)) {
            doExit(req, resp);
        }
    }

    private void doExit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            //手动销毁session对象
            session.invalidate();
            //跳转到登陆页面
            resp.sendRedirect(req.getContextPath());
        }
    }

    private void doLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = JDBCUtilsByDruid.getConnection();
            String sql = "select * from users where username = ?and password = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                key = true;
//                String password1 = resultSet.getString("password");
//                if (password.equals(password1)){
//                    resp.sendRedirect(req.getContextPath()+"/dept/list");
//                }else {
//                    req.getRequestDispatcher("/error.jsp").forward(req,resp);
//                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtilsByDruid.close(resultSet, preparedStatement, connection);
        }
        if (key) {
            //获取session对象
            HttpSession session = req.getSession();
            session.setAttribute("username", username);

            //确定是否选择十天免登入
            String f = req.getParameter("f");
            if ("1".equals(f)) {
                //创建cookie对象
                Cookie cookie1 = new Cookie("username", username);
                Cookie cookie2 = new Cookie("password", password);

                //设置cookie的有效时间
                cookie1.setMaxAge(60 * 60 * 24 * 10);
                cookie2.setMaxAge(60 * 60 * 24 * 10);

                //设置cookie的path（只要访问这个应用 浏览器就一定会携带这两个cookie）
                cookie1.setPath(req.getContextPath());
                cookie2.setPath(req.getContextPath());

                //响应cookie给浏览器
                resp.addCookie(cookie1);
                resp.addCookie(cookie2);
            }

            resp.sendRedirect(req.getContextPath() + "/dept/list");
        } else {
            req.getRequestDispatcher("/false.jsp").forward(req, resp);
        }
    }
}

