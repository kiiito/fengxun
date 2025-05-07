package com.hucong.reflection;

import java.lang.reflect.Method;

public class Reflection02 {
    public static void main(String[] args) throws Exception {
        m1();
        m2();
        m3();
    }
    public static void m1(){
        Cat cat = new Cat();
        long start = System.currentTimeMillis();
        for(int i = 0; i <1000000;i++){
            cat.hi();
        }
        long end = System.currentTimeMillis();
        System.out.println("m1() 耗时 = " + (end - start));
    }
    public static void m2() throws Exception {
        Class cls = Class.forName("com.hucong.reflection.Cat");
        Object o = cls.newInstance();
        Method hi = cls.getMethod("hi");
        long start = System.currentTimeMillis();
        for(int i = 0; i <1000000;i++){
            hi.invoke(o);
        }
        long end = System.currentTimeMillis();
        System.out.println("m2() 耗时 = " + (end - start));
    }
    public static void m3() throws Exception {
        Class cls = Class.forName("com.hucong.reflection.Cat");
        Object o = cls.newInstance();
        Method hi = cls.getMethod("hi");
        hi.setAccessible(true);//在反射调用方法 取消访问检查
        long start = System.currentTimeMillis();
        for(int i = 0; i <1000000;i++){
            hi.invoke(o);
        }
        long end = System.currentTimeMillis();
        System.out.println("m3() 耗时 = " + (end - start));
    }
}
