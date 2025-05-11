package com.hc.jsp.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String servletPath = request.getServletPath();
        HttpSession session = request.getSession(false);

        /**
         * 目前写的路径是/* 表示所有请求均拦截
         *
         * 用户访问 index.jsp的时候不能拦截
         * 用户已经登陆了 不能拦截
         * 用户要求登陆 不能拦截
         * WelcomeServlet也不能拦截
         *
         */

        if ("/index.jsp".equals(servletPath) || "/welcome".equals(servletPath) ||
                "/user/login".equals(servletPath) || "/user/exit".equals(servletPath) ||
        session != null && session.getAttribute("username") != null){
            //继续往下走
            filterChain.doFilter(request,response);
        }else {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }
}
