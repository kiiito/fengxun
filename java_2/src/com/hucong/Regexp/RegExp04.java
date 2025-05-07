package com.hucong.Regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExp04 {
    public static void main(String[] args) {
        String content = "abc111111";
       // String regStr = "\\d+";//默认是贪婪匹配
        String regStr = "\\d+?";//非贪婪匹配
        //构建模式对象 正则表达式对象
        Pattern pattern = Pattern.compile(regStr);
        //创建匹配器 matcher 按照正则表达式的规则 去匹配content字符串
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()){
            System.out.println("找到" + matcher.group(0));
        }
    }

}
