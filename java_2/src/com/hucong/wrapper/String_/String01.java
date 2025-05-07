package com.hucong.wrapper.String_;

/**
 *  1 String 类实现了接口Serializable [String 可以串行化 ： 可以在网络上传输] 接口comparable[String 对象可以比较大小]
 *  2 String 是final类 不能被其他类继承
 *  3 String 有属性 private final char value[] 用于存放字符串的内容
 *  value 是一个final类型 不可以修改 即value不能指向新的地址 但是单个字符内容是可以变化
 */
public class String01 {
    public static void main(String[] args) {
        final char[] value ={'a','b','c'};
        value[0] = 'w';
        char[] v2 = {'q'};
       // value = v2;//Cannot assign a value to final variable 'value' 不可以修改value的地址

        //String 类有很多构造器 构造器重载
        String s = new String("nh");
        //String s1 = new String(String original);
      //String s2 =   new String(char[] a);
//        System.out.println(s.intern());


        String a = "hc";
        String hc = new String("hc");
        System.out.println(a.equals(hc));//t
        System.out.println(a == hc);//f

        //t 如果常量池中已经包含一个一样的字符串 则返回池中字符串 否则将此对象添加到池中 并返回此对象的引用
        System.out.println(a == hc.intern());
        System.out.println(hc == hc.intern());//hc.intern()是指向的常量池 hc指向的是堆
    }
}
