package com.hc.bank.dao;

import com.hc.bank.bean.Account;
import jakarta.annotation.Resource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("accountDao")
public class AccountDaoImpl implements AccountDao{
    @Resource(name = "jdbcTemplate")
   private JdbcTemplate jdbcTemplate;

    @Override
    public Account selectAccount(String act) {
        String sql = "select actno,balance from users where actno = ?";
        Account account = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Account.class),act);
        return account;
    }

    @Override
    public int update(Account act) {
        String sql = "update users set balance = ? where actno = ?";
        int count = jdbcTemplate.update(sql, act.getBalance(), act.getActno());
        return count;
    }

    @Override
    public int insert(Account act) {

        String sql = "insert into users values(?,?,?)";
        int count = jdbcTemplate.update(sql,null,act.getActno(),act.getBalance());
        return count;
    }
}
