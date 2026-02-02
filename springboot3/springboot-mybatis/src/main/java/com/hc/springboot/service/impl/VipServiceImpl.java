package com.hc.springboot.service.impl;

import com.hc.springboot.bean.Vip;
import com.hc.springboot.repository.VipMapper;
import com.hc.springboot.service.VipService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VipServiceImpl implements VipService {
    @Autowired
    private VipMapper vipMapper;
    @Override
    public boolean save(Vip vip) {
        return vipMapper.insert(vip) == 1;
    }

    @Override
    public List<Vip> findAll() {
        return vipMapper.selectAll();
    }


}
