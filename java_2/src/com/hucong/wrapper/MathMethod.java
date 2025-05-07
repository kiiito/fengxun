package com.hucong.wrapper;

public class MathMethod {
    //Math的常用静态方法
    public static void main(String[] args) {
        System.out.println(Math.abs(-9));//abs绝对值
        System.out.println(Math.pow(2,4));//pow 求幂 2的四次方
        System.out.println(Math.ceil(-3.001));// ceil 向上取整 返回>=该参数的最大整数 转成double
        System.out.println(Math.floor(-4.99));// floor 向下取整 返回<=该参数的最大整数 转成double
        System.out.println(Math.round(-5.001));// round 四舍五入
        System.out.println(Math.sqrt(9.0)); //sqrt 求开方
        //Math.random 随机取[0,1)的数
        //要求返回一个数值范围 a ~ b 之间的一个随机整数
        // int num = (int)(a + Math.random() * (b - a + 1))  b-a+1 是防止有取 6.99999 在 int下直接取 6
        //例如 在2到7中随机取一个整数
        for (int i = 0; i < 10; i++) {
            System.out.print((int)(2 + Math.random() * (7 - 2 + 1) ) + " ");
        }
        //max main
        System.out.println(Math.max(2,5));
        System.out.println(Math.min(3,5));
    }
}
