package com.example.springboot.contorller;

import com.example.springboot.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
@Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;
    @PostMapping("/transfer")
    public String transfer(String outAccount,String inAccount,Double money){
        accountService.transfer(outAccount,inAccount,money);
        return "success";
    }
}
