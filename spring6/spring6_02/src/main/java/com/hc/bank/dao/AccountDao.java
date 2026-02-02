package com.hc.bank.dao;

import com.hc.bank.bean.Account;

public interface AccountDao {
    Account selectAccount(String act);
    int update(Account act);
    int insert(Account act);
}
