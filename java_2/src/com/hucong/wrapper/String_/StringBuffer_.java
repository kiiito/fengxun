package com.hucong.wrapper.String_;

public class StringBuffer_ {
    public static void main(String[] args) {
        /**
         * StringBuffer 的直接父类是 AbstractStringBuilder
         * StringBuffer 实现了Serializable 即StringBuffer的对象可以串行化
         * 在父类中 AbstractStringBuilder 有属性 char[] value 不是 final
         * 该 value 数组存放 字符串内容 引出存放在堆中的
         * StringBuffer 是一个final类 不能继承
         * 因为 StringBuffer字符串内容存放 char[] value 所有在变化(增加 删除)
         * 不用每次都更换地址 即不再是每次创建新的对象 所以效率低于 String
         */
        // 通过给一个String 创建StringBuffer char[] 大小就是 str.length() + 16
        StringBuilder hc = new StringBuilder("hc");
        //通过构造器指定 char[] 大小
        StringBuilder stringBuilder = new StringBuilder(100);
        //创建一个 大小为16的 char[] 用于存放字符串内容
        StringBuilder stringBuilder1 = new StringBuilder();

        //看 String -> StringBuffer
        String str = "hello hc";
        //方法1 使用构造器
        // 注意 返回的才是StringBuffer对象 对str 本身没有影响
        StringBuffer stringBuffer = new StringBuffer(str);
        // 方法2 使用的append 方法
        StringBuffer stringBuffer1 = new StringBuffer();
        stringBuffer1.append(str);

        //看看 StringBuffer -> String
        StringBuffer stringBuffer2 = new StringBuffer("你好");
        //方法1 使用StringBuffer提供的 toString 方法
        String s = stringBuffer2.toString();
        //方法2 使用构造器
        String s1 = new String(stringBuffer2);

        /**
         * StringBuffer 常用方法
         */
        StringBuffer s3 = new StringBuffer("你好");
        s3.append(",");
        s3.append("星空").append(100).append(true).append(10.9);
        System.out.println(s3);
        // 删除索引为>=start && <end 处的字符
        s3.delete(5,8);
        System.out.println(s3);
        // 使用替换
        s3.replace(3,5,"明月");
        System.out.println(s3);
        //查找指定子串在字符串第一次出现的索引 如果找不到就返回 -1
        int index = s3.indexOf("明月");
        System.out.println(index);
        //插入字符串
        s3.insert(5,"晚安");
        System.out.println(s3);
        //字符串长度
        System.out.println(s3.length());
    }
}
