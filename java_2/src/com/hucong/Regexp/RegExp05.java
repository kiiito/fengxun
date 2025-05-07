package com.hucong.Regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExp05 {
    public static void main(String[] args) {
        //判断是否为汉字
//        String content = "陈皮糖";
//        String regStr = "^[\u0391-\uffe5]+$";

        //验证邮政编码 要求是1-9 开头的一个六位数
//        String content = "123456";
//        String regStr = "^[1-9]\\d{5}$";

        //验证QQ号 是1-9 开头的一个5位数-10位数
//        String content = "123456";
//        String regStr = "^[1-9]\\d{4,9}$";

        //手机号码 必须是13 14 15 18 开头的11位数
        String content = "13177969672";
         String regStr = "1[3|4|5|8]\\d{9}$";
       // String regStr = "^1(?:3|4|5|8)\\d{9}$";
        Pattern compile = Pattern.compile(regStr);
        Matcher matcher = compile.matcher(content);

        if (matcher.find()){
            System.out.println("满足条件");
        }else {
            System.out.println("不满足条件");

        }
//        while (matcher.find()){
//            System.out.println("找到"+matcher.group(0));
//        }
    }
}
