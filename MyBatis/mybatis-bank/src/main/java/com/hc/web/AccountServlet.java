package com.hc.web;

import com.hc.exceptions.MoneyNotEnoughException;
import com.hc.exceptions.TransferException;
import com.hc.service.AccountService;
import com.hc.service.impl.AccountServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;

@WebServlet({"/transfer"})
public class AccountServlet extends HttpServlet {

    private AccountService accountService = new AccountServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fromAction = req.getParameter("fromAction");
        String toAction = req.getParameter("toAction");
        double money = Double.parseDouble(req.getParameter("money"));
        try {
            accountService.transfer(fromAction,toAction,money);

            //调用view完成展示
            resp.sendRedirect(req.getContextPath() + "/success.jsp");
        } catch (MoneyNotEnoughException e) {
            resp.sendRedirect(req.getContextPath() + "/MoneyNotEnoughError.jsp");
        } catch (TransferException e) {
            resp.sendRedirect(req.getContextPath() + "/TransferError.jsp");
        }
    }
}
