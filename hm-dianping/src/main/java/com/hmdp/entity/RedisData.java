package com.hmdp.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 创建存储逻辑过期的redis数据
 */
@Data
public class RedisData {
    private LocalDateTime expireTime;
    private Object data;
}
