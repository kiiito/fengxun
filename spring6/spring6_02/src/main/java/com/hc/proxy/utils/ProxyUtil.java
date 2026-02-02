package com.hc.proxy.utils;

import com.hc.proxy.InvocationHandler;
import com.hc.proxy.OrderService;


import java.lang.reflect.Proxy;

public class ProxyUtil {
    public static Object newProxyInstance(Object target){
        /**
         * 需要传入目标对象的类加载器
         * 需要传入与代理对象和目标同一个实现接口类型
         * 需要一个调用处理器（增强代码就是写在这个类当中）
         */
        Object proxy =  Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new InvocationHandler((OrderService) target));
        return proxy;
    }
}
