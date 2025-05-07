package com.hucong.interface_;

/**
 * 接口不可以被实例化
 * 接口所有的方法都是public方法 可省略不写 接口中抽象方法 可省略不写abstract
 * 一个普通类实现接口 就必须将该接口的所有方法都实现 可以利用Alt + enter一键生成
 * 抽象类实现接口，可以不用实现接口的方法
 * 一个类同时可以实现多个接口
 * 接口中的属性 只能是final 而且是 public static final 修饰符
 * 接口中的属性的访问形式 接口名.属性名
 * 接口不能继承其他的类 但可以继承多个别的接口
 * 接口的修饰符 只能是public 和默认，这一点和类的修饰符是一样的
 */
public class InterfaceDetail {
    public static void main(String[] args) {
        //接口中的属性的访问形式 接口名.属性名
        System.out.println(IQ.n1); //证明属性是static
       // iq.n1 = 100; 不可修改 证明属性是final
    }
}
//接口所有的方法都是public方法 可省略不写 接口中抽象方法 可省略不写abstract
interface IA{
    void hi();
    //public 和abstract 都可以省略不写
    public void say();

}
//一个普通类实现接口 就必须将该接口的所有方法都实现 可以利用Alt + enter一键生成
class Cat implements IA {

    @Override
    public void hi() {

    }

    @Override
    public void say() {

    }
}
//抽象类实现接口，可以不用实现接口的方法
abstract class Big implements IA {}

//一个类同时可以实现多个接口
interface IC{}
abstract class Q implements IA,IC{}

//接口中的属性 只能是final 而且是 public static final 修饰符
interface IQ{
    int n1 = 10;
}

//接口不能继承其他的类 但可以继承多个别的接口
interface IU extends IQ,IA{}