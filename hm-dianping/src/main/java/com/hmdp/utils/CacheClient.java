package com.hmdp.utils;

import cn.hutool.core.util.BooleanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.hmdp.entity.RedisData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static com.hmdp.utils.RedisConstants.*;

@Slf4j
@Component
public class CacheClient {
    private final StringRedisTemplate  stringRedisTemplate;

    private static final ExecutorService CACHE_REBUILD_EXECUTOR = Executors.newFixedThreadPool(10);

    public CacheClient(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     *   set
     * @param key
     * @param value
     * @param time
     * @param unit
     */
    public void set(String key, Object value, Long time, TimeUnit unit){
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(value), time, unit);
    }
    public void setWithLogicalExpire(String key, Object value, Long time, TimeUnit unit){
        RedisData redisData = new RedisData();
        redisData.setData(value);
        redisData.setExpireTime(LocalDateTime.now().plusSeconds(unit.toSeconds(time)));
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(redisData));
    }
    public <R,ID> R queryWithPassThrough(String keyPrefix, ID id, Class<R> type, Function<ID, R> dbFallback,
                                         Long time, TimeUnit unit) {
        String key = keyPrefix + id;
        // 1 从redis查询商铺信息缓存
        String json = stringRedisTemplate.opsForValue().get(key);
        // 2 判断是否存在
        if (StrUtil.isNotBlank(json)) {
            //3 存在 将json转化为商铺对象 并直接返回
            return JSONUtil.toBean(json, type);
        }
        //判断命中的舒是否是空值 如果不判断就又会去查询数据库 导致缓存穿透
        if (json != null) {
//            return Result.fail("商铺信息不存在");
            return null;
        }

        // 4 不存在 根据id查询数据库
        R r = dbFallback.apply(id);
        // 5 判断商铺是否存在
        if (r == null) {
            // 6 不存在 返回商铺不存在
            // 6 不存在 将空值写入redis缓存 并返回信息 防止缓存穿透
            stringRedisTemplate.opsForValue().set(key, "", CACHE_NULL_TTL, TimeUnit.MINUTES);
//            return Result.fail("商铺不存在");
            return null;
        }
        // 7 存在 写入redis缓存 并返回信息
        this.set(key,r,time,unit);
        return r;
    }




    /**
     * 逻辑过期解决缓存击穿
     * @param id 商铺id
     * @return 商铺信息
     */
    public <R,ID> R queryWithLogicalExpire(String keyPrefix,ID id,Class<R> type, Function<ID, R> dbFallback,
                                           Long time, TimeUnit unit) {
        R r = checkRedisTime(keyPrefix,id,type);
        // 6 重建缓存
        // 获取互斥锁
        String lockKey = keyPrefix + id;
        // 判断互斥锁是否获取成功
        boolean isLock = tryLock(lockKey);
        if(isLock){
            //再次redis缓存是否过期
            r = checkRedisTime(keyPrefix,id,type);
            // 成功 开启独立线程 实现缓存重建 这个时候不会等待重建完成 而是返回旧信息
            CACHE_REBUILD_EXECUTOR.submit(() -> {
                try {
                    R r1 = dbFallback.apply(id);
                    this.setWithLogicalExpire(keyPrefix,r1,time,unit);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    // 释放锁
                    unlock(lockKey);
                }
            });
        }
        //返回旧信息
        return r;
    }
    private <R,ID> R checkRedisTime(String keyPrefix, ID id, Class<R> type){
        // 1 从redis查询商铺信息缓存
        String json = stringRedisTemplate.opsForValue().get(keyPrefix + id);
        // 2 判断是否存在
        if (StrUtil.isBlank(json)) {
            //3 不存在 直接返回null
            return null;
        }
        /**
         * 4 命中 需要把json反序列化为对象
         * 5 判断是否逻辑过期
         *      逻辑未过期 直接返回商铺信息
         *      逻辑过期 需要重建缓存
         * 6 重建缓存
         *      获取互斥锁
         *      判断互斥锁是否获取成功
         *      成功 开启独立线程 实现缓存重建 这个时候不会等待重建完成 而是返回旧信息
         *      失败 返回旧信息
         */
        // 4 命中 需要把json反序列化为对象
        RedisData redisData = JSONUtil.toBean(json, RedisData.class);
        // 这里进行了类型强转JSONObject 在进行类型转化
        R r = JSONUtil.toBean((JSONObject) redisData.getData(), type);
        LocalDateTime expireTime = redisData.getExpireTime();
        // 5 判断是否逻辑过期
        if (expireTime.isAfter(LocalDateTime.now())) {
            // 逻辑未过期 直接返回商铺信息
            return r;
        }
        return r;
    }

    /**
     * 缓存击穿
     *这里采用了redis的setnx 如果key没有被创建 则就是创建key 获取到锁
     * 如果key已经被创建 setnx就不会创建key 则就是获取不到锁
     * setnx 就是判断key是否存在 如果存在就不会创建key 如果不存在就创建key
     */

    /**
     * 给线程加上互传锁 防止缓存击穿
     *
     * @param key 锁的key
     * @return 是否获取到锁
     */
    private boolean tryLock(String key) {
        Boolean flag = stringRedisTemplate.opsForValue().setIfAbsent(key, "1", 10, TimeUnit.SECONDS);
        return BooleanUtil.isTrue(flag);
    }

    /**
     * 释放锁 执行完查询数据库并写入缓存 释放锁
     *
     * @param key 锁的key
     */
    private void unlock(String key) {
        stringRedisTemplate.delete(key);
    }

}
