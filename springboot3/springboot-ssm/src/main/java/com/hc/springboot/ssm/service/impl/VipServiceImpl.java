package com.hc.springboot.ssm.service.impl;

import com.hc.springboot.ssm.bean.Vip;
import com.hc.springboot.ssm.repository.VipMapper;
import com.hc.springboot.ssm.service.VipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VipServiceImpl implements VipService {
    @Autowired
    private VipMapper vipMapper;
    @Override
    public Vip getVipByCarNumber(String carNumber) {
        return null;
    }

    @Override
    public Vip getVipByName(String name) {
        return vipMapper.findByName(name);
    }
}
