package com.hucong.poly.dynamic;

public class DynamicBinding {
    public static void main(String[] args) {
        A a = new B();
        //当调用对象方法的时候；该方法会和该对象的内存地址/运行类型绑定
        //当调用对象属性时，没有动态绑定机制，哪里声明，哪里调用。
        System.out.println(a.sum());// 1 40 -> 30
        System.out.println(a.sum1());// 2 30 -> 20
    }
}

class A {
    public int i = 10;

    public int sum() { // 1 子类没有，找到父类
        return getI() + 10;//getI()由于动态绑定机制，应与其运行类型绑定，a的运行类型为 B 所有跳转到 B类运行getI()
        // 1 20 + 10 = 30
    }

    public int sum1() { // 2 子类没有找父类
        return i + 10; // 2 找声明属性 本类 属性 为10 10 + 10 = 20
    }

    public int getI() {
        return i;
    }
}

class B extends A {
    public int i = 20;
//    public int sum() {
//        return  i + 20;
//    }

    @Override
    public int getI() {// 1 由于调用对象方法时，属性没有动态绑定，所以只用本类的属性值 返回20
        return i;
    }
//    public int sum1() {
//        return i + 10;
//    }
}