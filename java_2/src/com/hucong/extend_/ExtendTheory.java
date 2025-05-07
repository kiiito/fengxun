package com.hucong.extend_;

public class ExtendTheory {
    public static void main(String[] args) {
        son son = new son();
        //如果子类有这个属性，并且可以访问，则返回信息
        //如果子类没有这个属性，就看父类有没有，如果有并且可以访问就返回，没有就继续寻找上级父类，直到object(object是所有类的父类)
        System.out.println(son.name);//返回大头儿子
        System.out.println(son.age);//39
        System.out.println(son.hobby);//旅游
        //如果要想访问的属性是私有的，可以利用父类公共方法来调用
        System.out.println(son.getNumber());
    }
}

class GrandPa{
    String name = "大头爷爷";
    String hobby = "旅游";
}
class Father extends GrandPa{
     String name = "大头爸爸";
     int age = 18;
     private int number = 1;

    public int getNumber() {
        return number;
    }
}
class son extends Father{
    String name = "大头儿子";
}