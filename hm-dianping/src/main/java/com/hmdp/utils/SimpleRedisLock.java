package com.hmdp.utils;

import cn.hutool.core.lang.UUID;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class SimpleRedisLock implements ILock {

    private StringRedisTemplate stringRedisTemplate;
    private static final String KEY_PREFIX = "lock:";
    // 1 生成一个id
    private static final String ID_PREFIX = UUID.randomUUID().toString(true);
    private String name;

    /**
     *这里进行脚本的初始化
     */
    private static final DefaultRedisScript<Long> UNLOCK_SCRIPT;
    static{
        // 1 创建一个脚本对象
        UNLOCK_SCRIPT = new DefaultRedisScript<>();
        // 2 设置脚本的路径
        UNLOCK_SCRIPT.setLocation(new ClassPathResource("unlock.lua"));
        // 3 设置返回值类型
        UNLOCK_SCRIPT.setResultType(Long.class);
    }

    public SimpleRedisLock(StringRedisTemplate stringRedisTemplate, String name) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.name = name;
    }

    @Override
    public boolean tryLock(long timeoutSec) {
        //获取线程标识 线程id唯一 如果光是线程id 在多个jvm环境下可能会产生冲突所以需要加上前缀
        String threadId = ID_PREFIX + Thread.currentThread().getId();
        // 1 尝试去获取锁
        Boolean success = stringRedisTemplate.opsForValue().
                setIfAbsent(KEY_PREFIX + name, threadId, timeoutSec, TimeUnit.SECONDS);
        //注意这里可能有null出现 所以需要判断一下 Boolean是包装类 转化为基本类型有一个拆箱过程
        return Boolean.TRUE.equals(success);
    }

    /**
     * 这里为了保证判断获取到的线程id和自己线程id与释放锁这一动作的原子性（几乎同时进行）
     * 需要使用脚本来实现
     */
    @Override
    public void unlock() {
        stringRedisTemplate.execute(
                UNLOCK_SCRIPT,
                Collections.singletonList(KEY_PREFIX + name),
                ID_PREFIX + Thread.currentThread().getId()
        );
    }

//     @Override
//    public void unlock() {
//        /**
//         * 这里需要注意 如果业务时间过短 导致线程还没有执行完 就释放了锁 这样就会导致可能会误删其他线程的锁
//         * 所以这里需要做一个判断 如果获取到的锁的线程id与自己的线程id不一致 就无需释放锁
//         */
//        //获取线程标识
//        String threadId = ID_PREFIX + Thread.currentThread().getId();
//        //获取锁中的标识
//        String id = stringRedisTemplate.opsForValue().get(KEY_PREFIX + name);
//        if (threadId.equals(id)) {
//            //释放锁
//            stringRedisTemplate.delete(KEY_PREFIX + name);
//        }
//    }
}
