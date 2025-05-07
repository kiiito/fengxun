package com.hucong.Regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 非捕获分组
 */
public class RegExp03 {
    public static void main(String[] args) {
        String content = "西瓜奶茶 和 西瓜冰淇淋 和 西瓜果汁";
        //找出后缀一致的 返回西瓜奶茶西瓜冰淇淋西瓜果汁
        //String regStr = "西瓜(?:奶茶|冰淇淋|果汁)";
        //找出与后缀一致的但返回西瓜
        //String regStr = "西瓜(?=奶茶|冰淇淋)";
        //找出后缀不一致的 返回的还是西瓜 只不过是西瓜果汁
        String regStr = "西瓜(?!奶茶|冰淇淋)";
        //构建模式对象 正则表达式对象
        Pattern pattern = Pattern.compile(regStr);
        //创建匹配器 matcher 按照正则表达式的规则 去匹配content字符串
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()){
            //非捕获分组 不可用matcher.group(1)等
            System.out.println("找到" + matcher.group(0));
        }
    }
}
