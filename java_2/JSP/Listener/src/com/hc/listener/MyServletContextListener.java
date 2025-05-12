package com.hc.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        //在服务器启动时调用自动调用该方法（ServletContext被创建时调用）
        System.out.println("ServletContext被创建");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        //在服务器关闭时调用自动调用该方法（ServletContext被销毁时调用）
        System.out.println("ServletContext被销毁");
    }
}
