package com.hc.proxy;

import com.hc.proxy.utils.ProxyUtil;

import java.lang.reflect.Proxy;

public class client {
    public static void main(String[] args) {
        //创建OrderServiceImpl 目标对象
        OrderService orderService = new OrderServiceImpl();

        //创建代理对象
        /**
         * 需要传入目标对象的类加载器
         * 需要传入与代理对象和目标同一个实现接口类型
         * 需要一个调用处理器（增强代码就是写在这个类当中）
         */
//        OrderService proxy = (OrderService)Proxy.newProxyInstance(orderService.getClass().getClassLoader(),
//                                              orderService.getClass().getInterfaces(),
//                                              new InvocationHandler(orderService));

        OrderService proxy = (OrderService) ProxyUtil.newProxyInstance(orderService);
        //调用代理对象的代理方法
        proxy.info();

        //调用有返回值的代理方法
        System.out.println(proxy.getName());
    }
}
