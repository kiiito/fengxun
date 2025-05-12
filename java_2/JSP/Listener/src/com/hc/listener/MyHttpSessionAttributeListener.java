package com.hc.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
@WebListener
public class MyHttpSessionAttributeListener implements HttpSessionAttributeListener {
   //向session域当中存储数据时 web服务器调用以下方法
    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        System.out.println("session存储数据");
    }
    //向session域当中删除数据时 web服务器调用以下方法
    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {
        System.out.println("session删除数据");
    }
    //向session域当中替换数据时 web服务器调用以下方法
    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {
        System.out.println("session替换数据");
    }
}
