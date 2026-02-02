package com.hc.jedis.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 创建jedis连接池工具类
 */
public class JedisConnectionFactory {
    public static final JedisPool jedisPool;
    static {
        //配置连接池
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        //1 设置最大连接数
        poolConfig.setMaxTotal(8);
        //2 设置最大空闲连接数
        poolConfig.setMaxIdle(8);
        //3 设置最小空闲连接数
        poolConfig.setMinIdle(0);
        //4 设置连接超时时间
        poolConfig.setMaxWaitMillis(1000);
        //创建连接池对象
        jedisPool = new JedisPool(poolConfig,"192.168.253.136",6379,1000,"123321");
    }
    public static Jedis getJedisPool(){
        return jedisPool.getResource();
    }
}
