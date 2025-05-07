package com.hucong.extend_.exercise;

public class ExtendsExercise01 {
    public static void main(String[] args) {
        B b = new B();//a  b,name  b
    }
}
class A{
    A(){
        System.out.println("a");
    }
    A(String name){
        System.out.println("a name");
    }
}
class B extends A {
    B(){
        //this指向B(String name)这个方法，而this和super()又必须是构造器的第一句，所以两者不能同在一个构造器
        this("abc");
        System.out.println("b");
    }
    B(String name){
        //默认有super();
        System.out.println("b,name");
    }
}
