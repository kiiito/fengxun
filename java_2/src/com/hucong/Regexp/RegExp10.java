package com.hucong.Regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExp10 {
    public static void main(String[] args) {
        //去掉重复案例
        String content = "我我.....我要...学学学...学java";
        Pattern compile = Pattern.compile("\\.");
        Matcher matcher = compile.matcher(content);
         content = matcher.replaceAll("");
        System.out.println(content);
//
//        //去掉重复的字
//        //(.)\\1+
//        compile = Pattern.compile("(.)\\1+");//匹配到 是 我 学
//        matcher = compile.matcher(content);
//        //使用 外部反向引用$1来匹配到的内容
//       content= matcher.replaceAll("$1");//用我去代替我我我 学代替学学学
//        System.out.println(content);
        //用一条语句
       content = Pattern.compile("(.)\\1+").matcher(content).replaceAll("$1");
        System.out.println(content);
    }
}
