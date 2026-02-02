package com.example.springboot.service.impl;

import com.example.springboot.bean.Account;
import com.example.springboot.repository.AccountMapper;
import com.example.springboot.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;
    @Override
    public void transfer(String from, String to, Double money) {
        Account fromAction = accountMapper.findById(from);
        if(fromAction.getBalance() < money){
            throw new RuntimeException("余额不足");
        }
        Account toAction = accountMapper.findById(to);
        fromAction.setBalance(fromAction.getBalance() - money);
        toAction.setBalance(toAction.getBalance() + money);
        int count = accountMapper.update(fromAction);
        count += accountMapper.update(toAction);

        // 模拟异常
//        int i = 1/0;

        if(count != 2){
            throw new RuntimeException("转账失败");
        }

    }
}
