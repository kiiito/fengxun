package com.hc.springboot.ssm.service;

import com.hc.springboot.ssm.bean.Vip;

public interface VipService {
    Vip getVipByCarNumber(String carNumber);
    Vip getVipByName(String name);
}
