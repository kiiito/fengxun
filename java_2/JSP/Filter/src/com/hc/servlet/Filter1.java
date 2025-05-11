package com.hc.servlet;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;
//@WebFilter("/abc")
@WebFilter({"*.do"})
//*.do模糊匹配
public class Filter1 implements Filter {

    public Filter1() {
        System.out.println("无参构造器执行");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init执行");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
       //在请求的时候添加过滤规则
        System.out.println("doFilter执行");
        //执行下一个过滤器 如果下一个不是过滤器 则执行目标程序servlet
        filterChain.doFilter(servletRequest,servletResponse);
        //在响应的时候添加过滤规则
        System.out.println("doFilter执行结束");
    }

    @Override
    public void destroy() {
        System.out.println("destroy执行");
    }
}
