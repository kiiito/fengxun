package com.hucong.interface_;

/**
 * 接口的多态
 */
public class InterfacePoly {
    public static void main(String[] args) {
        //接口类型的变量 if01 可以指向 实现 IF接口类的对象实例
        IF if01 = new AA();
        if01 = new BB();

        //继承体现的多态 父类类型的变量 C 可以指向 继承CC的子类的对象实例
        CC a = new DD();
        a = new EE();
    }
}
interface IF{}
class AA implements IF {}
class BB implements IF {}

class CC{}
class DD extends CC{}
class EE extends CC{}