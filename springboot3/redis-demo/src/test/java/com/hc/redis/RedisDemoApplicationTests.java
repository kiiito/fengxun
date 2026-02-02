package com.hc.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class RedisDemoApplicationTests {

    @Autowired
    private RedisTemplate  redisTemplate;
    @Test
    void testString() {
        redisTemplate.opsForValue().set("age",22);
        System.out.println(redisTemplate.opsForValue().get("age"));
    }

}
