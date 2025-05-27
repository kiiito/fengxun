package com.xlz.web;

import com.xlz.utils.SqlSessionUtil;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class MyBatisListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 初始化操作
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // 确保所有SqlSession被关闭
        SqlSessionUtil.close(SqlSessionUtil.openSession());
    }
}
