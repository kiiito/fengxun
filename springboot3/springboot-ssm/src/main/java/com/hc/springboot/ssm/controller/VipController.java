package com.hc.springboot.ssm.controller;

import com.hc.springboot.ssm.bean.Vip;
import com.hc.springboot.ssm.service.VipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VipController {
    @Autowired
    private VipService vipService;
    @GetMapping(value = "/vip", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vip> getVip(@RequestParam("name") String name) {
        Vip vip = vipService.getVipByName(name);
        return vip != null ? ResponseEntity.ok(vip) : ResponseEntity.notFound().build();
    }
}
