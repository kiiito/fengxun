package com.hucong.Regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExp09 {
    public static void main(String[] args) {
        String content = "123 12215 4554 11 55555";
       // String regStr = "(\\d)(\\d)\\2\\1";// \\2表示第二个\\d \\1表示第一个\\d

        //匹配两个相同的数字
       // String regStr = "(\\d)\\1";

        //匹配五个系统的数字
        String regStr = "(\\d)\\1{4}";
        Pattern compile = Pattern.compile(regStr);
        Matcher matcher = compile.matcher(content);
        while (matcher.find()){
            System.out.println(matcher.group(0));
        }
    }
}
