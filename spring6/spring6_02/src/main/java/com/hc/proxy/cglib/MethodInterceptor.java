package com.hc.proxy.cglib;

import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MethodInterceptor implements net.sf.cglib.proxy.MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("增强1");
        Object retValue = methodProxy.invokeSuper(o, objects);
        System.out.println("增强2");
        return retValue;
    }
}
