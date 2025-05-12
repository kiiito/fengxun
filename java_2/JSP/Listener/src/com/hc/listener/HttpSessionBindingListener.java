package com.hc.listener;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet({"/test"})
public class HttpSessionBindingListener extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        user1 user1 = new user1("1", "tom", "123");
        user2 user2 = new user2("2", "jack", "123");

        //将数据放入session域当中
        session.setAttribute("user1",user1);
        session.setAttribute("user2",user2);

    }
}
