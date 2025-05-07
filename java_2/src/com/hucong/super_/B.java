package com.hucong.super_;

public class B extends A{
    //super完全符合修饰符的访问要求
    public void su(){
        System.out.println(super.name + " " + super.age + " " + super.sum);
    }
    //super不可直接访问私有属性
    public void su02(){
        super.test01();
        super.test02();
        super.test03();
    }
    public void sum(){
        //有三种找到cal的方法
        //如果子类有这个属性，并且可以访问，则返回信息
        //如果子类没有这个属性，就看父类有没有，如果有并且可以访问就返回，没有就继续寻找上级父类，直到object(object是所有类的父类)
        //直接调用
//        cal();
//        this.cal();//等价与cal()
        super.cal();//找cal()的方法是先找父类，其他规则一样
    }
}
