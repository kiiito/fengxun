package com.hc.spring6.dao;

import com.hc.spring6.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserDao {
    //创建日志对象
    private Logger logger = LoggerFactory.getLogger(User.class);

    public UserDao() {
    }

    public void info(){
        logger.info("info正在被调用");
    }
}
