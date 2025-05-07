package com.hucong.wrapper.String_;

public class StringMethod {
    public static void main(String[] args) {
        String str1 = "hc";
        String  str2 = "HC";
        String  str3 = "HC@shhj@iyu";
        //equalsIgnoreCase()忽略大小写判断内容是否相等
        System.out.println(str1.equalsIgnoreCase(str2));
        System.out.println(str1.length());//获取字符串的长度
        int index = str1.indexOf("@");//获取字符(也可以是字符串 如 shh) 在字符串对象第一次出现的索引 从0开始 如果没找到返回-1
        int end = str1.lastIndexOf("h");//获取字符在字符串对象最后一次出现的索引 从0开始 如果没有返回-1
        String str4 = "涂家骏1234";
        System.out.println(str4.substring(5));//截取指定范围内的子串  从索引5开始截取后面所有的字符
        System.out.println(str4.substring(0,3));//从索引0开始 截取到3-1 [0 3)
        String str5 = "qwerty";
        String str6 = "QWERTY";
        System.out.println(str5.toUpperCase());//将字符串全部转换成大写
        System.out.println(str5.toLowerCase());//将字符串全部转换成小写
        str6 = str6.concat(str5).concat("你好");//拼接字符串
        System.out.println(str6);
        //替换字符串中的字符 返回结果才是替换过的 对本身str6没有任何影响
        System.out.println(str6.replace("你好","我好"));
        System.out.println(str6);
        String str7 = "123,456,789";
        String [] split01 = str7.split(",");//分割字符串 对于某些分割字符 我们需要转义 比如 | \\
       for (int i = 0; i < split01.length; i++){
           System.out.print(split01[i] + " ");
       }
       String str8 = "E:\\aa\\bb";
       //在对字符串分割时 如果有特殊字符串 需要加入转义符 \ 如 \ 需要转义的字符 \
       String [] split02 = str8.split("\\\\");
        for (int i = 0; i < split02.length; i++){
            System.out.print(split01[i] + " ");
        }
        System.out.println();
        String str9 = "happy";
        char [] s = str9.toCharArray();//将字符串转换成字符数组
        for (int i = 0; i < s.length; i++) {
            System.out.print(s[i] + " ");
        }
        String str10 = "jac";
        String str11 = "jack";
        //比较两个字符串的大小 如果前者大 返回正数 反之复数 如果相等返回0 例如此结果 返回-1
        // 如果长度相等 字符不相等 就用前者的字符的值相减
        System.out.println(str10.compareTo(str11));
        // format 格式占位符 有 %s 字符串 %c 字符 %d 整型 %.2f 浮点型
        String name = "jack";
        int age = 18;
        double score = 98.3;
        char gender = '男';
        String info1 = "我的姓名是%s 年龄是%d 成绩是%.2f 性别是%c 希望大家多多关照";
        String info2 = String.format(info1,name, age, score, gender);
        System.out.println(info2);
    }
}
