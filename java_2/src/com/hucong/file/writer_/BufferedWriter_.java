package com.hucong.file.writer_;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class BufferedWriter_ {
    public static void main(String[] args) throws Exception {
        //需要以追加形式写入 应该在FileWriter 里面加true BufferedWriter 没有这个选项
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("D:\\a.txt",true));
        bufferedWriter.write("我同样喜欢甘雨");
        bufferedWriter.newLine();//插入一个和系统相关的换行
        bufferedWriter.write("我同样喜欢甘雨");
        bufferedWriter.close();
    }
}
