package com.hucong.Regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExp06 {
    public static void main(String[] args) {
        String content = "https://www.bilibili.com/video/BV1fh411y7R8?from=search&useid=1831060912083761326";

        /**
         * 1 先确定URL的来时部分
         * 2 通过([\w-]+\.)+[\w-]+匹配 www.bilibili.com
         * 3 (\/[\w-?=&/%.#]*)?
         */
        String regStr = "^((http|https)://)?([\\w-]+\\.)+[\\w-]+(\\/[\\w-?=&/%.#]*)?$";//[.]表示匹配就是.本身并不是匹配所有
        Pattern compile = Pattern.compile(regStr);
        Matcher matcher = compile.matcher(content);

        if (matcher.find()){
            System.out.println("满足条件");
        }else {
            System.out.println("不满足条件");

        }
    }
}
