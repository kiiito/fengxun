package com.hc.spring6.service;

import com.hc.spring6.dao.UserDao;

public class CustomerService {
    private UserDao userDao;
    //采用构造注入的方法

    public CustomerService(UserDao userDao) {
        this.userDao = userDao;
    }
    public void testInfo(){
        userDao.info();
    }
}
