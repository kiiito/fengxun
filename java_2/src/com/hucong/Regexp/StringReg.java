package com.hucong.Regexp;

public class StringReg {
    public static void main(String[] args) {
        String content = "JDK1.3 JDK1.4";
       //使用正则表达式 将JDK1.3 JDK1.4 替换JDK
        content = content.replaceAll("JDK1\\.3|JDK1\\.4","JDK");
        System.out.println(content);
        content = "13879624721";
        boolean matches = content.matches("1(38|39)\\d{8}");
        System.out.println(matches);
        //要求按照# - ~来分割
        content = "123-456#789~123";
        String[] split = content.split("#|-|~");
        for (String s : split) {
            System.out.println(s);
        }
    }
}
