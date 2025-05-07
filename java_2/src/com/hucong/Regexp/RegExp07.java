package com.hucong.Regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExp07 {
    public static void main(String[] args) {
        String content = "13177969672";
        String regStr = "1[3|4|5|8]\\d{9}$";
//        // String regStr = "^1(?:3|4|5|8)\\d{9}$";
//        Pattern compile = Pattern.compile(regStr);
//        Matcher matcher = compile.matcher(content);


        //演示matches方法 用于整体匹配 在验证输入的字符串是否满足条件使用 直接返回布尔值
        boolean matches = Pattern.matches(regStr, content);
        System.out.println("整体匹配=" + matches);
    }
}
