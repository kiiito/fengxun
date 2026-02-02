package com.hc.bank.service;

import com.hc.bank.bean.Account;

public interface AccountService {
    void transfer(String fromAction,String toAction,double money);
    void save(Account account);
}
