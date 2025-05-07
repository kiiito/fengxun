package com.hu.JavaWeb.servlet;

import jakarta.servlet.*;

import java.io.IOException;

/**
 * 适配器模式改进servlet接口
 * 将需要使用方法改成抽象方法
 * 让实现类去继承该类实现该抽象方法即可
 * 简化了的代码繁杂
 */
public abstract class GenericServlet implements Servlet {
    //将ServletConfig定义成成员变量
    private ServletConfig servletConfig;

    //防止子类重写init方法 造成servletConfig=null 需要添加final
    @Override
    public final void init(ServletConfig servletConfig) throws ServletException {
        //将Tomcat传进来的ServletConfig赋值给创建的ServletConfig
        this.servletConfig = servletConfig;
        //预防子类有需求更改init方法 可以将这个方法进行重载
        //在让子类重写该重载后的类 最后在原有的init类调用重载方法
        this.init();
    }

   public void init() {

    }
    @Override
    public ServletConfig getServletConfig() {
        //将赋值后的ServletConfig返回
        return servletConfig;
    }

    @Override
    public abstract void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException ;

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
