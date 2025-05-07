package com.hu.JavaWeb.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class TestServlet02 extends GenericServlet{
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        //设置响应内容类型
        servletResponse.setContentType("text/html");
        PrintWriter writer = servletResponse.getWriter();
        writer.println("执行了servlet方法");

        //获取ServletConfig对象
        ServletConfig servletConfig = this.getServletConfig();
        System.out.println("ServletConfig = " + servletConfig);
        System.out.println("执行了servlet方法");
    }
    //重写 重载后的init
@Override
    public void init(){
    System.out.println("init执行了");
    }
}
