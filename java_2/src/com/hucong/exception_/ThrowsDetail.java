package com.hucong.exception_;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * 1 对于编译异常 程序必须处理 比如 try catch 或者 throws
 * 2 对于运行异常 程序中如果没有处理 默认就是throws的方式处理
 * 3 子类重写父类的方法时 对抛出异常的规定 子类重写的方法 所抛出的异常类型要么与父类抛出的异常一致，要么是父类抛出异常类型的子类
 * 4 在throws过程中 如果有方法try catch 就相当于处理异常 就可以不必throws 两者选一
 */
public class ThrowsDetail {
    public static void main(String[] args) {

    }
    public static void f1() throws FileNotFoundException{
        //f3方法是一个编译异常 f1就必须处理这个编译异常 try catch 和 throws 二者选一
        f3();
    }
    public static void f3() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("D://aa.txt");
    }
    public static void f4(){
        //f5方法是抛出的运行异常 有默认处理
        f5();
    }
    public static void f5() throws ArithmeticException{

    }
}

