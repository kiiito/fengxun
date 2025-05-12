package com.hc.listener;

import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;

public class user1 implements HttpSessionBindingListener {
    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        System.out.println("绑定数据");
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        System.out.println("解绑数据");
    }
    private String id;
    private String name;
    private String pwd;

    public user1() {
    }

    public user1(String id, String name, String pwd) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
