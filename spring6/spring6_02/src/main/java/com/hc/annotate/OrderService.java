package com.hc.annotate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * 注入非简单类型
 *如果该接口有多个实现类 @Autowired就不能自动匹配类型
 * 就要用@Qualifier去指定是哪一个实现类
 * 只有一个实现类的情况下@Autowired可以在属性上 构造器上（构造器只能有一个） set方法上 还可以省略
 */
@Service("orderService")
public class OrderService {
    @Autowired
    @Qualifier("orderForMysql")  // 改为依赖接口
    private OrderDao orderDao;  // 不是 OrderForMysql


}
