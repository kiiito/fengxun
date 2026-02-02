package com.hc.service.impl;

import com.hc.bean.UserBean;
import com.hc.dao.UserDao;
import com.hc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserBean getById(int id) {
        UserBean userBean = userDao.selectById(id);
        return userBean;
    }
}
