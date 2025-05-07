package com.hucong.generic.custome;

public class CustomMethodGeneric {
    public static void main(String[] args) {
        /**
         *  泛型方法的使用
         * 1 泛型方法 可以定义在普通类中 也可以定义在泛型类当中
         * 2 当泛型方法被调用时 类型会确定
         * 3 public void hi(T t){} 修饰符后面没有<T,R> eat方法不是泛型方法而是使用了泛型
         */
        Car car = new Car();
        car.fly("ganYu",18);//当调用方法时 传入参数 编译器会确定类型
    }
}
//泛型方法 可以定义在普通类中 也可以定义在泛型类当中
class Car{//普通类
    public void run() {}//普通方法
    //<T,R>就是泛型标识符 提供给fly使用
    public <T,R> void fly(T t, R r) {
        System.out.println(t.getClass());//String
        //Integer 泛型必须是引用类型 所以int会自动装箱成Integer
        System.out.println(r.getClass());
    }//泛型方法
}
class Fish<T,R>{
    public void run() {}//普通方法
    public <U,M> void eat(U u, M m) {}//泛型方法

    //下面的Hi方法不是泛型方法 而是使用了类声明的泛型
    public void hi(T t){}

    //泛型方法 可以使用类声明的泛型 也可以使用自己声明的泛型
    public <S> void hello(T t ,S s){}
}
