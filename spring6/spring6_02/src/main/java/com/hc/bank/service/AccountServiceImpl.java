package com.hc.bank.service;

import com.hc.bank.bean.Account;
import com.hc.bank.dao.AccountDao;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("accountService")
//@Transactional //开启事务
public class AccountServiceImpl implements AccountService{
    @Resource(name = "accountDao")
    private AccountDao accountDao;
    @Resource(name = "accountService2")
    private AccountService accountService;
    @Transactional
    @Override
    public void transfer(String fromAction,String toAction,double money) {
        Account account1 = accountDao.selectAccount(fromAction);
        if (account1.getBalance() < money) {
            throw new RuntimeException("余额不足");
        }

        Account account2 = accountDao.selectAccount(toAction);
        account1.setBalance(account1.getBalance() - money);

        //模拟异常
//        String str = null;
//        str.toString();

        account2.setBalance(account2.getBalance() + money);


        int count = accountDao.update(account1);
        count += accountDao.update(account2);
        if (count != 2) {
            throw  new RuntimeException("转账失败");
        }
    }

    //propagation = Propagation.REQUIRED 采用的同一个事务 如果出异常 两个添加都不会执行
    //@Transactional(propagation = Propagation.REQUIRED)
    //开启一个新事务 两个事务局部干扰
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void save(Account account) {
        accountDao.insert(account);
        Account act004 = new Account("act004", 50000);
        //只要扑捉到异常就不会影响方法的执行
        try{
            accountService.save(act004);
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    /**
     * ● REQUIRED：支持当前事务，如果不存在就新建一个(默认)【没有就新建，有就加入】
     * ● SUPPORTS：支持当前事务，如果当前没有事务，就以非事务方式执行**【有就加入，没有就不管了】**
     * ● MANDATORY：必须运行在一个事务中，如果当前没有事务正在发生，将抛出一个异常**【有就加入，没有就抛异常】**
     * ● REQUIRES_NEW：开启一个新的事务，如果一个事务已经存在，则将这个存在的事务挂起**
     * 【不管有没有，直接开启一个新事务，开启的新事务和之前的事务不存在嵌套关系，之前事务被挂起】**
     * ● NOT_SUPPORTED：以非事务方式运行，如果有事务存在，挂起当前事务**【不支持事务，存在就挂起】**
     * ● NEVER：以非事务方式运行，如果有事务存在，抛出异常**【不支持事务，存在就抛异常】**
     * ● NESTED：如果当前正有一个事务在进行中，则该方法应当运行在一个嵌套式事务中。被嵌套的事务可以独立于外层事务进行提交或回滚。如果外层事务不存在，行为就像REQUIRED一样。
     * 【有事务的话，就在这个事务里再嵌套一个完全独立的事务，嵌套的事务可以独立的提交和回滚。没有事务就和REQUIRED一样。】
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void with(){

    }
}
