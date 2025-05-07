package com.hucong.interface_;

/**
 * 可以写属性，写方法
 * 在接口中抽象方法可以省略abstract关键字
 * 1 抽象方法 2 默认实现方法 3 静态方法
 */
public interface interface1 {
    public int n1 = 10;

    //省略abstract关键字
    public  void hi();

    //在jdk8后 可以有默认实现方法 需要使用default关键字来修饰
    default public void ok(){
        System.out.println("ok");
    }

    //在jdk8后 可以有静态方法
    public static void cry(){
        System.out.println("cry   ");
    }
}
