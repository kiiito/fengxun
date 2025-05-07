package com.hucong.file.inputstream;

import java.io.*;

public class InputStreamReader_ {
    public static void main(String[] args) throws IOException {
        //把FileInputStream 转成 InputStreamReader 指定编码
        BufferedReader br = new BufferedReader(
                            new InputStreamReader(new FileInputStream("D:\\a.txt"),"gbk"));
        //读取
        String s = br.readLine();
        System.out.println("读取的内容 = " + s);
        //关闭外层流
        br.close();
    }
}
