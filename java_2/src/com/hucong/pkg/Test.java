package com.hucong.pkg;

import com.hucong.modifier.A;

public class Test {
    public static void main(String[] args) {
        A a = new A();
        //不同包下只可调用public
        System.out.println(a.n1);
        a.m1();
    }
}
