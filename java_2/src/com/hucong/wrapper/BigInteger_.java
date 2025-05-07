package com.hucong.wrapper;

import java.math.BigInteger;

public class BigInteger_ {
    public static void main(String[] args) {
        //当我们编程中需要处理很大的整数龙不够用 可以用BigInteger的类来解决
        BigInteger bigInteger = new BigInteger("9999999999999999999999999999");
        System.out.println(bigInteger);
        BigInteger bigInteger1 = new BigInteger("1");
        //在对 BigInteger 进行加减乘除时 需要使用 对应的方法 不能直接进行加减乘除
        System.out.println(bigInteger.add(bigInteger1));//加法
        System.out.println(bigInteger.subtract(bigInteger1));//减法
        System.out.println(bigInteger.multiply(bigInteger1));//乘法
        System.out.println(bigInteger.divide(bigInteger1));//除法
    }
}
