package com.hucong.exception_;

/**
 * 异常的引入
 */
public class Exception01 {
    public static void main(String[] args) {
        int num = 10;
        int num02 = 0;
        //抛出异常 程序退出 崩溃了 下面的代码就不在运行了
        // 将代码块选中 快捷键 Ctrl + Alt + t 选中 6 或者 try catch
        // 如果进行异常处理 那么即使出现了异常 程序可以继续运行
        try {
            int res = num / num02;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println("程序继续运行");
    }
}
