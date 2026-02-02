package com.hc.service.impl;

import com.hc.mapper.AccountMapper;
import com.hc.pojo.Account;
import com.hc.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("accountService")
@Transactional
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;
    @Override
    public int save(Account account) {
        return accountMapper.insert(account);
    }

    @Override
    public int delByName(String name) {
        return accountMapper.delete(name);
    }

    @Override
    public int update(Account account) {
        return accountMapper.update(account);
    }

    @Override
    public Account selectByName(String name) {
        return accountMapper.select(name);
    }

    @Override
    public List selectAll() {
        return accountMapper.selectAll();
    }

    @Override
    public void transfer(String fromName, String toName, double money) {
        Account account1= accountMapper.select(fromName);
        if (account1.getBalance() < money) {
            throw new RuntimeException("余额不足");
        }

        Account account2 = accountMapper.select(toName);
        account1.setBalance(account1.getBalance() - money);
        account2.setBalance(account2.getBalance() + money);

        int count = accountMapper.update(account1);
        count += accountMapper.update(account2);

        if (count != 2) {
            throw  new RuntimeException("转账失败");
        }
    }
}
