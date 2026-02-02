//package com.hc.bank.service;
//
//import com.hc.bank.bean.Account;
//import com.hc.bank.dao.AccountDao;
//import jakarta.annotation.Resource;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service("accountService2")
//public class AccountServiceImpl2 implements AccountService{
//    @Resource(name = "accountDao")
//    private AccountDao accountDao;
//    @Override
//    public void transfer(String fromAction, String toAction, double money) {
//
//    }
//@Transactional(propagation = Propagation.REQUIRES_NEW)
//    @Override
//    public void save(Account account) {
//        int count = accountDao.insert(account);
//        //模拟异常
//        String str = null;
//        str.toString();
//
//        System.out.println(count);
//    }
//}
