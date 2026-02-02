package com.example.springboot.repository;

import com.example.springboot.bean.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper {
    Account findById(String actNo);
    int update(Account account);

}
