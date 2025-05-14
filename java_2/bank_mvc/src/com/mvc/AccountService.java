package com.mvc;

import com.bean.Account;
import com.exception.AppException;
import com.exception.MoneyNotEnoughException;
import com.utils.JDBCUtilsByDruid;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * 该类为业务类 用于处理转账业务
 */

public class AccountService {
    //定义一个AccountDao对象用于连接数据库处理数据
    private AccountDao accountDao = new AccountDao();

    //完成转账的业务逻辑
    public void transfer(String fromActno,String toActno,double money) throws AppException {

        try(Connection connection = JDBCUtilsByDruid.getConnection()) {
            System.out.println(connection);
            //开启事务
            connection.setAutoCommit(false);

            //查询余额是否充足
            Account fromAct = accountDao.selectByAct(fromActno);
            if (fromAct.getBalance() < money){
                try {
                    throw new MoneyNotEnoughException("余额不足");
                } catch (MoneyNotEnoughException e) {
                    throw new RuntimeException(e);
                }
            }
            Account toAct = accountDao.selectByAct(toActno);
            //修改余额（只是修改了内存Java对象的余额）
            fromAct.setBalance(fromAct.getBalance() - money);
            toAct.setBalance(toAct.getBalance() + money);
            //更新数据库余额
            int count = accountDao.update(fromAct);
            count += accountDao.update(toAct);

            if (count != 2) {
                throw new AppException("转账异常");
            }

            //提交事物
            connection.commit();

        } catch (SQLException e) {
            //这个可以添加回滚业务
            throw new AppException("转账异常");
        }



    }
}
