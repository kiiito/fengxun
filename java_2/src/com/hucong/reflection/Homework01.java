package com.hucong.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Homework01 {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        Class<?> aClass = Class.forName("com.hucong.reflection.PrivateTest");
        Object o = aClass.newInstance();
        Method getName = aClass.getMethod("getName");
        System.out.println(getName.invoke(o));
        Field name = aClass.getDeclaredField("name");
        name.setAccessible(true);
        name.set(o,"ki");
        System.out.println(name.get(o));


    }
}
class PrivateTest {
    private String name = "helloKitty";
    public String getName() {
        return name;
    }
}
