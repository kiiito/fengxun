package com.hucong.exception_;

/**
 * NullPointerException 空指针异常
 * ArrayIndexOutOfBoundsException 数组下标越界异常
 * ClassCastException 类型转换异常
 * NumberFormatException 数字格式不正确异常
 * ArithmeticException 算术异常
 */
public class NullPointerException_ {
    public static void main(String[] args) {
        String name = null;
//        //NullPointerException 空指针异常
        System.out.println(name.length());
        int []arr = {1,2,4};
//        // ArrayIndexOutOfBoundsException 数组下标越界异常
        for (int i = 0; i <= arr.length; i++){
            System.out.println(arr[i]);
        }
        //ClassCastException 类型转换异常
        A b = new B();
        C c = (C)b;
        //NumberFormatException 数字格式不正确异常
        String name1 = "你好";
        int num =Integer.parseInt(name1);

        //ArithmeticException 算术异常
        int num01 = 10;
        int num02 = 0;
        int res = num01 / num02;
    }
}
class A{}
class B extends A{}
class C extends A{}