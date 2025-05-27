package com.hc.spring6.service;

import com.hc.spring6.dao.UserDao;

public class UserService {
    //创建userDao对象
    private UserDao userDao;

    //构建set方法 注意这里可以写set方法 但最好是idea自动生成的
    //set注入是通过方法名去掉set将首字母小写进行的
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    //调用info方法
    public void testInfo(){
        userDao.info();
    }
}
