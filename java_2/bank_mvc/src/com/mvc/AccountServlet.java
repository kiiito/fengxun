package com.mvc;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * 该类负责各种功能的调度
 * mvc当中的controller
 */
@WebServlet({"/transfer"})
public class AccountServlet extends HttpServlet {
    private AccountService accountService = new AccountService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接受数据
        String fromAction = req.getParameter("fromAction");
        String toAction = req.getParameter("toAction");
        double money = Double.parseDouble(req.getParameter("money"));

        //调用业务方法处理业务

        try {
            accountService.transfer(fromAction, toAction, money);
            resp.sendRedirect(req.getContextPath() + "/success.jsp");
        } catch (Exception e) {
            resp.sendRedirect(req.getContextPath() + "/error.jsp");
        }
    }
}
