package com.hucong.exception_;

import java.io.FileInputStream;

public class Throws01 {
    public static void main(String[] args) {

    }

    /**
     *
     * @throws Exception
     * 使用throws 抛出异常 让调用f2方法的调用者 方法 处理
     * throws 后面的异常类型可以是方法中产生的异常类型 也可以是它的父类
     * throws 关键字后也可以说异常列表 即可以抛出多个异常
     */

    public void f2() throws Exception {
        FileInputStream fileInputStream = new FileInputStream("d://a.txt");
    }
}
