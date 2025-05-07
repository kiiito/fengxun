package com.hc.jsp.action;

import com.hc.jsp.utils.JDBCUtilsByDruid;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet({"/welcome"})
public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取cookie
        Cookie[] cookies = req.getCookies();
        String username = null;
        String password = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if ("username".equals(name)){
                    username = cookie.getValue();
                } else if ("password".equals(name)) {
                    password = cookie.getValue();
                }
            }
        }

        //要在这里使用username和password变量
        if (username != null && password != null){

            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            boolean key = false;
            try {
                connection = JDBCUtilsByDruid.getConnection();
                String sql = "select * from users where username = ?and password = ?";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);
                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()){
                    key = true;

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }finally {
                JDBCUtilsByDruid.close(resultSet,preparedStatement,connection);
            }
            if (key){
                //获取session对象
                HttpSession session = req.getSession();
                session.setAttribute("username", username);
                resp.sendRedirect(req.getContextPath() + "/dept/list");
            }else {
                resp.sendRedirect(req.getContextPath() + "/index.jsp");
            }

        }else {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        }
    }
}
