package com.hucong.modifier;
//只有public和默认才能修饰类
public class A {
    public int n1 = 100;
    protected int n2 = 200;
    int n3 = 300;
    private int n4 = 400;
    //在同一类中四个修饰符都可调用
    public void m1(){
        System.out.println("n1=" + n1 + "n2=" + n2 + "n3=" + n3 + "n4" + n4);
    }
    //成员方法的访问规则和属性一致
    protected void m2(){
    }
    void m3(){}
    private void m4(){}
}
