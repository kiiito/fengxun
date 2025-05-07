package com.hucong.final_;

/**
 * final的细节使用
 */
public class FinalDetail {
    public static void main(String[] args) {
        CC cc = new CC();
        new EE().xx();
        System.out.println(FF.a);
    }
}

/**
 * final修饰的属性在定义时必须赋初值，并且以后不能修改，赋值可以在 1 定义时 2 在构造器里 3 在代码块里
 */
class AA {
    final double MAX_VALUE = 0.1;
    final double MAX_VALUE01;
    final double MAX_VALUE02;

    //在构造器中
    public AA() {
        MAX_VALUE01 = 1;
    }

    //在代码块中
    {
        MAX_VALUE02 = 2;
    }
}

/**
 * 如果final修饰的的属性是静态的，则初始化的位置只能是 1定义时 2 在静态代码块里 (不能在构造器里)
 */
class BB {
    final static double MIN_VALUE = 0.1;
    final static double MIN_VALUE01;

    static {
        MIN_VALUE01 = 1.0;
    }
}

/**
 * final类不能被继承，但可以实例化对象
 */
final class CC {
}

/**
 * 如果不是final类，但含有final方法，则该方法虽然不能重写，但可以被继承
 */
class DD {
    final void xx() {
        System.out.println("xx方法被调用");
    }
}

class EE extends DD {

}

/**
 * 如果一个类已经是final类，就没有必要再将方法修饰成final方法
 * final不能修饰构造器
 * 包装类 integer double float boolean string 都是final类
 */

/**
 * final和static往往搭配使用，不会导致类加载，底层编译器做了优化处理
 */
class FF{
    public static final int a = 100;
    static {
        System.out.println("FF静态方法被加载");
    }
}