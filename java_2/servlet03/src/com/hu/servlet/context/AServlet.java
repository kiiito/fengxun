package com.hu.servlet.context;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class AServlet extends GenericServlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        servletResponse.setContentType("text/html");
        PrintWriter writer = servletResponse.getWriter();
        ServletContext application = this.getServletContext();
        writer.println(application);

        //获取上下文初始化参数
        Enumeration<String> parameterNames = application.getInitParameterNames();
        while (parameterNames.hasMoreElements()){
            String nextElement = parameterNames.nextElement();
            String initParameter = application.getInitParameter(nextElement);
            writer.println("<br>" + nextElement + "=" + initParameter);
        }

        //获取上下文的根
        String contextPath = application.getContextPath();
        System.out.println("<br>" + contextPath);

        //获取文件的绝对路径
        //默认是从根下找(web)
        String realPath = application.getRealPath("index.html");
        writer.println(realPath);

        //log日志
        application.log("你好 日志");


        //准备数据
        User jack = new User("jack", "123");
        //向ServletContext应用域存储数据
        application.setAttribute("user",jack);
        //获取数据
        //ServletContext应用域是整个webAPP共用的数据源 所以可以在其他类获取该数据 但是要在当前类创建之后才会有该数据
        Object user = application.getAttribute("user");
        writer.println(user);
        //移除数据
        //application.removeAttribute();
    }
}
