package com.hc.service.impl;

import com.hc.bean.Account;

import com.hc.dao.AccountDao;
import com.hc.exceptions.MoneyNotEnoughException;
import com.hc.exceptions.TransferException;
import com.hc.service.AccountService;
import com.hc.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

public class AccountServiceImpl implements AccountService {
    //    private AccountDao accountDaoImpl = new AccountDaoImpl();
    //动态生成我们生成的dao接口的实现类（代理类 dao接口的代理）
    private AccountDao accountDao = SqlSessionUtil.openSession().getMapper(AccountDao.class);
    int count = 0;

    @Override
    public void transfer(String fromAction, String toAction, double money) throws MoneyNotEnoughException, TransferException {
        //添加事务控制代码
        SqlSession sqlSession = SqlSessionUtil.openSession();

        //判断转账的账户余额是否充足
        Account formAction = accountDao.selectByAction(fromAction);
        if (formAction.getBalance() < money) {
            throw new MoneyNotEnoughException("余额不足");
        }
        //获取被转账的账户
        Account toAct = accountDao.selectByAction(toAction);
        //更新对象余额
        formAction.setBalance(formAction.getBalance() - money);
        toAct.setBalance(toAct.getBalance() + money);

        //更新数据库余额信息
        count = accountDao.update(formAction);
        count += accountDao.update(toAct);
        if (count != 2) {
            throw new TransferException("转账异常");
        }
        //提交事务
        sqlSession.commit();
        //关闭事务
        SqlSessionUtil.close(sqlSession);
    }
}
