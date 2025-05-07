package com.hucong.reflection;

import java.lang.reflect.Constructor;

public class ReflectionCreateInstance {
    public static void main(String[] args) throws Exception {
        // 1 先获取user类的class对象
        Class<?> aClass = Class.forName("com.hucong.reflection.User");
        // 2 通过public的无参构造器创建实例
        Object o = aClass.newInstance();
        System.out.println(o);
        // 3 通过public的有参构造器创建实例
        /*
        constructor 就是
        public User(String name) {
                this.name = name;
            }
         */
        //得到构造器对象
        Constructor<?> constructor = aClass.getConstructor(String.class);
        //创建实例 并传入实参
        Object o1 = constructor.newInstance("刻晴");//修改name值
        System.out.println("o1 = " + o1);
        // 4 通过非public的有参构造器创建实例
        Constructor<?> declaredConstructor = aClass.getDeclaredConstructor(int.class, String.class);
        //反射爆破 可以访问private构造器
        declaredConstructor.setAccessible(true);
        Object hu = declaredConstructor.newInstance(20, "hu");
        System.out.println("hu=" + hu);
    }
}
class User {
    private int age = 10;
    private String name = "甘雨";

    public User(){}

    private User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public User(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
