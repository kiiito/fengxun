package com.hc.jsp.bean;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;

public class User implements HttpSessionBindingListener {

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        //获取到ServletContext对象
        ServletContext application = event.getSession().getServletContext();
        //获取在线人数
        Object onlineCount = application.getAttribute("onlineCount");
        //第一个登入的人这个域还是为空所以要判断
        if (onlineCount == null) {
            application.setAttribute("onlineCount",1);
        }else {
            int count = (Integer)onlineCount;
            count++;
            application.setAttribute("onlineCount",count);
        }
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        //获取到ServletContext对象
        ServletContext application = event.getSession().getServletContext();
        //获取在线人数
        Integer onlineCount = (Integer) application.getAttribute("onlineCount");
        onlineCount--;
        application.setAttribute("onlineCount",onlineCount);
    }

    private String username;
    private String password;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
