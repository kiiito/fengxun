package com.hucong.Regexp;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEx {
    public static void main(String[] args) {
        String content = "3342@qq.com";
        String regStr = "[\\w-]+@([a-zA-Z]+\\.)+[a-zA-Z]+";
        //String  的matches是匹配整体的 可不用加^$
        boolean matches = content.matches(regStr);
        System.out.println(matches);

        //验证是不是整数或者小数 考虑整数和负数
        //123 -123 -12.3 0.3 -0.3
        content = "-12.3";
        /**
         * [-+]? 可能有-+
         * ([1-9]\\d*|0)只能以1到9开头后面接0到多的数字 或者以0开头
         * (\\.\\d+)?可以有小数出现后面接至少一位数字
         */
        regStr = "^[-+]?([1-9]\\d*|0)(\\.\\d+)?$";
        if (content.matches(regStr)){
            System.out.println("匹配成功");
        }else{
            System.out.println("匹配失败");
        }

        content = "http://www.sohu.com:8080/abc/index.html";
        regStr = "^([a-zA-Z]+)://([a-zA-Z.]+):(\\d+)[\\w-/]*/([\\w.]+)$";
        Pattern compile = Pattern.compile(regStr);
        Matcher matcher = compile.matcher(content);
        if (matcher.matches()){
            System.out.println("匹配成功1"+ matcher.group(0));
        }else{
            System.out.println("匹配失败1");
        }
    }
}
