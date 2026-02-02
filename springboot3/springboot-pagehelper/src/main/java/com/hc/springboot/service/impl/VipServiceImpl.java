package com.hc.springboot.service.impl;

import com.hc.springboot.bean.Vip;
import com.hc.springboot.repository.VipMapper;
import com.hc.springboot.service.VipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VipServiceImpl implements VipService {
    @Autowired
    private VipMapper vipMapper;
    @Override
    public List<Vip> findAll() {
        return vipMapper.selectAll();
    }
}
