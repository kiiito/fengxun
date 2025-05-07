package com.hucong.Regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExp02 {
    public static void main(String[] args) {
        String content = "abc1234 5678";
        //下面是非命名分组
       // String regStr = "(\\d\\d)(\\d\\d)";
       //命名分组
        String regStr = "(?<g1>\\d\\d)(?<g2>\\d\\d)";
        //构建模式对象 正则表达式对象
        Pattern pattern = Pattern.compile(regStr);
        //创建匹配器 matcher 按照正则表达式的规则 去匹配content字符串
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()){
            //groups[0] 表示匹配到子字符串
            //groups[1] 表示匹配到的子字符串第一组字符串
            //groups[2] 表示匹配到的子字符串第二组字符串
            //但是分组不能越界
            System.out.println("找到:" + matcher.group(0));
            System.out.println("找到:" + matcher.group(1));
            System.out.println("找到(通过命名分组):" + matcher.group("g1"));
            System.out.println("找到:" + matcher.group(2));
            System.out.println("找到(通过命名分组):" + matcher.group("g2"));


        }
    }
}
