package com.hucong.poly_.objectpoly;

public class PolyDetail {
    public static void main(String[] args) {
        //向上转型，父类的引用指向了子类的对象
        // 语法： 父类类型 引用名 = new 子类类型();
        Animal animal = new Cat();
        //可以调用父类中所有成员（但必须遵守访问权限）
        //但不能调用子类的特有成员，在编译阶段，能调用哪些成员，是由编译类型来决定
        //所以 animal.eatSome(); 是错误的
        animal.eat();
        animal.run();


        //向下转型
        //语法 子类类型 引用名 = (子类类型) 父类引用
        //cat的编译类型是 Cat 运行类型是 cat
        //要求父类的引用必须指向的是当前目标类型的对象 Animal animal = new Cat(); 指向的就是cat的目标对象
        //Dog dog =(Dog) animal 就是错的，animal此刻指向的是cat
        Cat cat = (Cat) animal;
        cat.eatSome();//向下转型目的就是可以调用cat类下面的方法


        //属性没有重新之说，属性的值看编译类型
        AA aa = new BB();
        System.out.println(aa.sun);//10
        BB bb = new BB();
        System.out.println(bb.sun);//20

        //instanceof 比较操作符，用于判断对象的运行类型是否为xx类型或xx类型的子类型
        System.out.println(aa instanceof AA);//true
        System.out.println(aa instanceof BB);//true
        Object o = new Object();
        System.out.println(o instanceof AA);//false
    }
}
class AA{
    int sun = 10;
}
class BB extends AA {
    int sun = 20;
}
