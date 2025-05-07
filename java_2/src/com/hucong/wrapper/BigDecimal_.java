package com.hucong.wrapper;

import java.math.BigDecimal;

public class BigDecimal_ {
    public static void main(String[] args) {
        //保存一个精度很高的数 需要用BigDecimal
        BigDecimal bigDecimal = new BigDecimal("23.9999999999999999");
        BigDecimal bigDecimal2 = new BigDecimal("1.1");
        //在对 BigDecimal 进行加减乘除时 需要使用 对应的方法 不能直接进行加减乘除
        System.out.println(bigDecimal.add(bigDecimal2));//加法
        System.out.println(bigDecimal.subtract(bigDecimal2));//减法
        System.out.println(bigDecimal.multiply(bigDecimal2));//乘法
        //在调用divide 方法时指定精度
        // 除法  可能抛出异常 可能是一个无限循环小数
        System.out.println(bigDecimal.divide(bigDecimal2,BigDecimal.ROUND_CEILING));
    }
}
