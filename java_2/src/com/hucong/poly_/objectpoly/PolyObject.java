package com.hucong.poly_.objectpoly;

public class PolyObject {
    public static void main(String[] args) {
        //对象的多态的特点
        //(1)一个对象的编译类型和运行类型可以不一致
        //(2)编译类型在定义对象时，就确定了,不能改变
        // (3)运行类型是可以变化的.
        //(4)编译类型看定义时=号的左边，运行类型看=号的右边
        Animal animal = new Cat();//编译类型还是Animal 但运行类型则是Cat类型
        animal.say();// Cat say
        animal = new Dog();//编译类型还是Animal 但运行类型则是Dog类型
        animal.say();//Dog say

    }
}
