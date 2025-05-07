package com.hucong.extend_;

public class ExtendDetail {
    public static void main(String[] args) {
        //调用父类公共方法得到私有属性
        Sub sub = new Sub();
        System.out.println(sub.getN4());
        sub.getTestN4();
        Sub sub1 = new Sub("jack",30);

    }
}
