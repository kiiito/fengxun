package com.hucong.exception_;

/**
 * 捕获异常的细节
 * 1 如果异常发生了 则发生异常后面的代码不会执行 自己进入catch块
 * 2 如果异常没有发生 则顺序执行try的代码块 不会进入到catch中
 * 3 如果希望不管是否发生异常 都要执行某段代码 比如关闭连接 释放资源 则用finally
 * 4 可以有多个catch语句 捕获不同的异常 要求异常的子类在前 父类在后 如果发生异常 只会匹配一个catch
 * 5 可以进行 try finally 这种没有捕获异常因此程序会直接退出
 */
public class TryCatchDetail {
    public static void main(String[] args) {
        try {
            String str = "你好";
            int a = Integer.parseInt(str);
            System.out.println("数字" + a);
        } catch (NumberFormatException e) {
//            throw new RuntimeException(e);
            System.out.println("异常信息" + e.getMessage());
        } finally {
            System.out.println("finally 代码块被执行");
        }
        System.out.println("程序继续执行");
        System.out.println("==========  =============");
        try {
            Person person = new Person();
            System.out.println(person.getName());
            int n1 = 10;
            int n2 = 0;
            int res = n1 / n2;
        } catch (NumberFormatException e) {
            System.out.println("算术异常" + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("空指针异常" + e.getMessage());
        } catch (Exception e) {
//            throw new RuntimeException(e);
        } finally {

        }

    }
}

class Person {
    private String name = "jack";

    public String getName() {
        return name;
    }
}
