package com.hucong.file.Properties_;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Properties01 {
    public static void main(String[] args) throws IOException {
        //读取文件并得到IP user pwd
        BufferedReader bf = new BufferedReader(new FileReader("src/com/hucong/file/Properties_/myaql.properties"));
        String str = "";
        while ((str = bf.readLine()) != null) {
            String[] split = str.split("=");
            System.out.println(split[0] + " 的值：" + split[1]);
        }
        bf.close();
    }
}
