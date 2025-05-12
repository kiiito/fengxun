package com.hc.listener;

import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class MyServletRequestListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println("request对象被创建");
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("request对象被销毁");
    }
}
