package com.hc.user.service;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
public class RedisTest {

    @Resource
    private RedisTemplate redisTemplate;
    @Test
    void test(){
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set("黄腾辉","03");
        Object name = valueOperations.get("黄腾辉");
        System.out.println(name);
    }
}
