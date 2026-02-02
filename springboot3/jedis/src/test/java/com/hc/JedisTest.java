package com.hc;

import com.hc.jedis.utils.JedisConnectionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

public class JedisTest {

    private Jedis jedis;

    @org.junit.jupiter.api.Test
    @BeforeEach
    void setUp() {
        //1 建立连接
//        jedis = new Jedis("192.168.253.136",6379);
        jedis = JedisConnectionFactory.getJedisPool();
        //2 设置密码
        jedis.auth("123321");
        //3 选择数据库
        jedis.select(0);
    }
    @Test
    void testString(){
        String result = jedis.set("name", "hc");
        System.out.println(result);
        String name = jedis.get("name");
        System.out.println(name);
    }

    @Test
    void testHash(){
        Long hset = jedis.hset("user", "k4", "v4");
        System.out.println(hset);
        String hget = jedis.hget("user", "k4");
        System.out.println(hget);
    }
    @AfterEach
    void tearDown() {
        if (jedis != null){
            jedis.close();
        }
    }
}
