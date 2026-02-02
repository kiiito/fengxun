package com.hc.mapper;

import com.hc.pojo.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface AccountMapper {
    int insert(Account account);
    int update(Account account);
    int delete(String name);
    Account select(String name);
    List<Account> selectAll();
}
