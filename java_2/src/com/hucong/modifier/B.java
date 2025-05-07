package com.hucong.modifier;

public class B {
    public void m2(){
        A a = new A();
        //在不同类里不能调用private
        System.out.println("n1=" + a.n1 + "n2=" + a.n2 + "n3=" + a.n3 );
        a.m1();
        a.m2();
        a.m3();
    }
}
