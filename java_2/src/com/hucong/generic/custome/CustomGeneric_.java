package com.hucong.generic.custome;

public class CustomGeneric_ {
    public static void main(String[] args) {
        /**
         * 1 普通成员可以用泛型 (属性 方法)
         * 2 使用泛型的数组不可以初始化
         * 3 静态方法中不能使用类的泛型
         * 4 泛型类的类 啥在创建对象时确定的 因为创建对象时 需要指定确定类型
         * 5 如果在创建对象时 没有指定类型 默认为Object
         */

        // T = Double R = String M = Integer
        Tiger<Double, String, Integer> g = new Tiger<>("ganYu");
        g.setT(10.9);//OK
        //g.setT("yy");//类型不对报错
        System.out.println(g);
        Tiger tiger = new Tiger("eula");//默认3个都是Object
        tiger.setT("yy");//ok
        System.out.println(tiger);
    }

}

/**
 * 1 T R M 就被称为自定义泛型的标识 Tiger就是自定义泛型类
 * 2 泛型的标识可以有很多个
 * 3 普通成员可以用泛型 (属性 方法)
 */
class Tiger<T, R, M> {
    String name;
    T t;//普通成员可以用泛型 (属性 方法)
    //Type parameter 'T' cannot be instantiated directly 使用泛型的数组不可以初始化
    //T[] ts = new T();
    R r;
    M m;

    public Tiger(String name, T t, R r, M m) {
        this.name = name;
        this.t = t;
        this.r = r;
        this.m = m;
    }

    public Tiger(String name) {
        this.name = name;
    }

    //静态与类相关 类加载时 对象并没有被创建
    // 如果静态方法和静态属性使用了泛型 jvm就无法完成初始化(因为根本还未确定泛型是什么类型)
    //static R r2;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public R getR() {
        return r;
    }

    public void setR(R r) {
        this.r = r;
    }

    public M getM() {
        return m;
    }

    public void setM(M m) {
        this.m = m;
    }

    @Override
    public String toString() {
        return "Tiger{" +
                "name='" + name + '\'' +
                ", t=" + t +
                ", r=" + r +
                ", m=" + m +
                '}';
    }
}