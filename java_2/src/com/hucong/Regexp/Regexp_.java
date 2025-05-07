package com.hucong.Regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regexp_ {
    public static void main(String[] args) {
        String content = "abc$(a.bc(123( )";
        String regStr = "\\d{3}";//等价于\\d\\d\\d
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()){
            System.out.println("找到:" + matcher.group(0));
        }

        String content1 = "a11c8abcABC";
       // String regStr1 = "[a-z]";//匹配a-z之间的任意一个字符
        //String regStr1 = "[A-Z]";//匹配A-Z之间的任意一个字符
       // String regStr1 = "abc";//匹配abc 默认区分大小写
       // String regStr1 = "(?i)abc";//匹配abc 不区分大小写
       // String regStr1 = "[0-9]";//匹配0-9之间的任意一个字符
       // String regStr1 = "[^a-z]";//匹配不是a-z之间的任意一个字符 [^0-9] [^A-Z]以此类推
        String regStr1 = "[abcd]";//匹配abcd之间的任意一个字符
        /**
         * \\d 匹配0-9之间的任意一个字符
         * \\D匹配不是0-9之间的任意一个字符
         * \\w 匹配任意英文字符 数字和下划线 相当于[a-zA-Z0-9]
         * \\W 匹配不是任意英文字符 数字和下划线 相当于[^a-zA-Z0-9]
         * \\s 匹配任何空白字符(空格 制表符等)
         * \\S 匹配任何非空白字符
         * . 匹配出\n之外的所有字符 如果要匹配 本身需要转义符\\.
         *
         */

        //当创建Pattern对象时 指定 Pattern.CASE_INSENSITIVE 就不区分大小写
        Pattern pattern1 = Pattern.compile(regStr1,Pattern.CASE_INSENSITIVE);
        Matcher matcher1 = pattern1.matcher(content1);
        while (matcher1.find()){
            System.out.println("找到:" + matcher1.group(0));
        }
    }
}
