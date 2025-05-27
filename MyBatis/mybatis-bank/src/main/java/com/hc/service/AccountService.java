package com.hc.service;

import com.hc.exceptions.MoneyNotEnoughException;
import com.hc.exceptions.TransferException;

public interface AccountService {
     void transfer(String fromAction,String toAction,double money) throws MoneyNotEnoughException, TransferException;
}
