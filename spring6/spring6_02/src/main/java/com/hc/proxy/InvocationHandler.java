package com.hc.proxy;

import java.lang.reflect.Method;

/**
 * 动态生成代理类
 */

public class InvocationHandler implements java.lang.reflect.InvocationHandler {

    private OrderService orderService;

    public InvocationHandler(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 代理类
     * @param proxy the proxy instance that the method was invoked on
     *
     * 目标类的方法
     * @param method the {@code Method} instance corresponding to
     * the interface method invoked on the proxy instance.  The declaring
     * class of the {@code Method} object will be the interface that
     * the method was declared in, which may be a superinterface of the
     * proxy interface that the proxy class inherits the method through.
     *
     * 目标类的参数
     * @param args an array of objects containing the values of the
     * arguments passed in the method invocation on the proxy instance,
     * or {@code null} if interface method takes no arguments.
     * Arguments of primitive types are wrapped in instances of the
     * appropriate primitive wrapper class, such as
     * {@code java.lang.Integer} or {@code java.lang.Boolean}.
     *
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //增强代码写在这里
        System.out.println("点击查看详情");
        //回调目标类的目标方法
        //需要传入一个目标类的对象
        Object invoke = method.invoke(orderService, args);
        System.out.println("撤销查看详情");

        //如果目标方法有返回值的话 必须将这个目标对象的目标方法的执行结果返回
        return invoke;
    }
}
