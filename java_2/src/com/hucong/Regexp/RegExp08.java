package com.hucong.Regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExp08 {
    public static void main(String[] args) {
        String content = "123 456 123";
        String regStr = "123";
        Pattern compile = Pattern.compile(regStr);
        Matcher matcher = compile.matcher(content);
        while (matcher.find()){
            System.out.println("=============");
            //返回该regStr第一个和最后位置的索引
            System.out.println(matcher.start());
            System.out.println(matcher.end());
            System.out.println("找到:" + content.substring(matcher.start(), matcher.end()));


            //整体匹配方法 常用于去校验某个字符串是否满足某个规则
            System.out.println("整体匹配"+matcher.matches());//返回false 改成123.*则返回true

            //完成替换
            regStr = "456";
            compile = Pattern.compile(regStr);
            matcher = compile.matcher(content);
            //注意 返回的字符串才是替换后的字符串 原来的content不变化
            String newContent = matcher.replaceAll("789");
            System.out.println("newContent" + newContent);
        }
    }
}
