package com.hucong.innerclass;
/**
 * 实现匿名内部类的使用
 */
public class AnonymousInnerClass {
    public static void main(String[] args) {
        Outer02 outer02 = new Outer02();
        outer02.method();
    }
}

class Outer02{
    public void method(){
        // 1 tiger类 只用一次 后期不再使用
        // 2 匿名内部类来简化开发
        // 3 tiger 的编译类型是 IA
        // 4 tiger 的运行类型是 匿名内部类
        /*
        底层代码是 系统自定义一个类名 Outer02$1
        class Outer02$1 implements IA{
            @Override
                public void cry() {
                    System.out.println("老虎叫");
                }
        }
         */
        // 5 jdk底层在创建匿名内部类 Outer02$1 立即就创建了 Outer02$1实例并把地址返回tiger
        // 6 匿名内部类使用一次 就不能再使用 而tiger这个对象还可以反复调用
        IA tiger = new IA(){
            @Override
            public void cry() {
                System.out.println("老虎叫");
            }
        };
        System.out.println("tiger 的运行类型是 = " + tiger.getClass());//获取对象的运行类型
        tiger.cry();

        /*
        演示基于匿名内部类
        底层代码
        class Outer02$2 extends Father{}
         */
        //注意 jack 参数列表会传递给构造器
        Father father = new Father("jack"){
            @Override
            public void hi() {
                System.out.println("匿名内部类重写了Hi方法");
            }
        };
        System.out.println("father匿名内部类的运行类型是" + father.getClass());
        father.hi();

        //基于抽象类的匿名内部类
        Animal animal = new Animal(){
            //必须实现方法
            @Override
            void eat() {
                System.out.println("吃东西");
            }
        };
    }
    }

interface IA{
    void cry();
}
class Father {
    public Father(String name) {
        System.out.println("接收到的name = " + name);
    }
    public void hi(){

    }
}
abstract class Animal{
    abstract void eat();
}
