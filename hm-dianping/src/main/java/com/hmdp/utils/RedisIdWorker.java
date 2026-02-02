package com.hmdp.utils;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Component
public class RedisIdWorker {
    //开始时间戳
    public static final long BEGIN_TIMESTAMP = 1735689600L;
    //序列号位数
    public static final int COUNT_BITS = 32;
    private StringRedisTemplate stringRedisTemplate;

    public RedisIdWorker(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public long nextId(String keyPrefix) {
        // 1 生成时间戳
        LocalDateTime now = LocalDateTime.now();
        long nowSecond = now.toEpochSecond(ZoneOffset.UTC);
        long timestamp = nowSecond - BEGIN_TIMESTAMP;

        // 2 生成序列号
        /**
         *获取当前日期 精确到天
         *      第一避免超过32位的上限（因为如果这一个业务场景都用一个id自增涨 32位可能会溢出）
         *      第二根据日期去统计当天的销售量 当月 当年
         */
        String date = now.format(DateTimeFormatter.ofPattern("yyyy:MM:dd"));
        //自增长
        long count = stringRedisTemplate.opsForValue().increment("icr" + keyPrefix + ":" + date);

        //3 拼接并返回
        /**
         * 时间戳向前移位 32位 后32位全部补零 在和序列号进行或运算 由于后32位都是零 所以序列号进行或运算后就是本身
         */
        return timestamp << COUNT_BITS | count;
    }
}
