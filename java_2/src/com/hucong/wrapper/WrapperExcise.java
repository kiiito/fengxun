package com.hucong.wrapper;

public class WrapperExcise {
    public static void main(String[] args) {
        Integer i = new Integer(1);
        Integer j = new Integer(1);
        System.out.println(i == j); //false i 和 j 是两个对象 指向的地址不同所以不同

        //底层还是 Integer.valueOf(1) 所以这里主要是看范围 -128 到 127 就是直接返回的是1
        Integer m = 1;
        Integer n = 1;
        System.out.println(n == m);//true

        //超过了-128 到 127 的范围 就创建新对象
        Integer x = 128;
        Integer y = 128;
        System.out.println(x==y);//false
    }
}
