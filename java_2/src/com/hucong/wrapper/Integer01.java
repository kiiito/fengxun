package com.hucong.wrapper;

public class Integer01 {
    public static void main(String[] args) {
        //演示int <-> Integer 的装箱与拆箱
        //jdk5前说手动装箱和拆箱
        //手动装箱 int -> Integer
        int n = 100;
        Integer integer = new Integer(n);
        Integer integer1 = Integer.valueOf(n);

        //手动拆箱
        //Integer -> int
        int i = integer.intValue();

        //jdk5后就可以自动装箱和自动拆箱
        int n2 = 200;
        //自动装箱 int -> Integer
        Integer integer2 = n2;//底层使用的还是Integer.valueOf(n2)
        //自动拆箱 Integer -> int
        int n3 = integer2;//底层还是使用intValue()方法

    }
}
