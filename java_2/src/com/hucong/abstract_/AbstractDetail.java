package com.hucong.abstract_;

public class AbstractDetail {
}

/**
 * 1 抽象类不能够实例化
 * 2 抽象类可以没有抽象方法， 但一旦有抽象abstract方法，它所在的类就必须是 抽象类
 * 3 抽象方法不能有实体
 * 4 abstract 只能修饰类和方法 ，不能修饰属性和其他的
 * abstract 抽象类还是类，可以有任意的成员 比如非抽象方法 静态属性，构造器
 */
abstract class AA{
    abstract void hi();
    // abstract void hi(){} 是错误的不能有 {}
    public static void xx(){
    }

    public AA() {
    }
}
/**
 * 如果一个类继承了抽象类，那就必须实现他的所有抽象方法，除非他自己也声明自己是 抽象类
 * 抽象方法不能用 private static final 修饰
 */
abstract class BB extends AA{
//    abstract final void jj(); 是错误的
}
class CC extends AA{
    @Override
    void hi() {

    }
}