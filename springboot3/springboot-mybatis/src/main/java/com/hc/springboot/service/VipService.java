package com.hc.springboot.service;

import com.hc.springboot.bean.Vip;

import java.util.List;

public interface VipService {
    boolean save(Vip vip);
    List<Vip> findAll();
}
