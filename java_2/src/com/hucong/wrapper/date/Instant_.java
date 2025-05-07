package com.hucong.wrapper.date;

import java.time.Instant;
import java.util.Date;

public class Instant_ {
    public static void main(String[] args) {
        //通过 静态方法 now() 获取表示时间戳的对象
        Instant now = Instant.now();
        System.out.println(now);
        //通过 from 可以把 Instant转成 Date
        Date date = Date.from(now);
        // 通过 date.toInstant() 可以把 date 转换成 Instant对象
        Instant instant = date.toInstant();
    }
}
