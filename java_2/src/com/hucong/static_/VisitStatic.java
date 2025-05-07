package com.hucong.static_;

public class VisitStatic {
    public static void main(String[] args) {
        //类变量是随着类的家载而创建，所以即使没有创建对象也可以访问
        System.out.println(A.name);
        A a = new A();
        //也可以通过对象名。类变量名
        System.out.println(a.name);
    }
}
class A{
    //类变量依然要遵守相关的访问权限
    public static String name = "jack";
}
class B{
    private static  int age = 18;
    public static void hh(){

    }
    //类方法中不允许使用和对象有关的关键词如 this super
    //类方法(静态方法)中，只能访问 静态变量 或静态方法
    public static void xx(){
        System.out.println(age);
        System.out.println(B.age);
        hh();
        B.hh();
    }
    //普通成员方法，既可以访问 非静态成员，也可以访问静态成员
}