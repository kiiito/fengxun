package com.hucong.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionAccessMethod {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
    //  1 得到Boss类对应的class对象
        Class<?> aClass = Class.forName("com.hucong.reflection.Boss");
        // 2 创建对象
        Object o = aClass.newInstance();
        // 3 调用public的Hi方法
        Method hi = aClass.getMethod("hi",String.class);
        hi.invoke(o,"hu");
        // 4 调用private的say方法
        Method say = aClass.getDeclaredMethod("say", int.class, String.class,char.class);
        say.setAccessible(true);
        System.out.println(say.invoke(o,10,"co",'a'));

        // 5 在反射中 如果方法有返回值 统一返回object 但是他运行类型和方法定义的返回类型一致
        Object invoke = say.invoke(null, 300, "王五", '男');
        System.out.println("invoke 的运行类型 = "  + invoke.getClass());

    }
}
class Boss{
    public int age;
    private static String nane;

    public Boss() {
    }
    private static String say(int n,String s,char c) {
        return n + " " + s + " " + c;
    }
    public void hi(String s){
        System.out.println("hi " +s);
    }
}
