package com.hucong.wrapper;

public class WrapperVSString {
    public static void main(String[] args) {
        //包装类 Integer -> String
        Integer i = 100;
        //方法1
        String str1 = i + "";
        //方法2
        String str2 = i.toString();
        //方法3
        String str3 = String.valueOf(i);

        //String -> 包装类 Integer
        String str4 = "12345";
        Integer i2 = Integer.parseInt(str4);//使用到自动装箱
        Integer i3 = new Integer(str4);//构造器
    }
}
