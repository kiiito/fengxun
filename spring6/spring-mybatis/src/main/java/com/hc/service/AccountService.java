package com.hc.service;

import com.hc.pojo.Account;

import java.util.List;

public interface AccountService {
    int save(Account account);
    int delByName(String name);
    int update(Account account);
    Account selectByName(String name);
    List selectAll();
    void transfer(String fromName,String toName,double money );
}
