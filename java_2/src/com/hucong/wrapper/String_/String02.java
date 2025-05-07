package com.hucong.wrapper.String_;

/**
 *  String e = "hello" + "hc"; 常量相加 看的是常量池 而String c = a + b; 变量相加 是在堆中
 */
public class String02 {
    public static void main(String[] args) {
        String a = "hello";
        String b = "hc";

        // 底层先创建一个 StringBuilder s = new StringBuilder();
        //在执行 s.append("hello"); s.append("hc");
        // String c = s.toString();
        // 最后c指向的是堆中的对象 (String) value[] -> 池中 "hellohc"
        String c = a + b;
        String d = "hellohc";
        System.out.println(c == d);//f c指向的堆中对象 而d指向的常量池
        String e = "hello" + "hc";
        System.out.println(e == d);//t e 指向的也是常量池

    }
}
