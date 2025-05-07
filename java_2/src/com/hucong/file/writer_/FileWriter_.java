package com.hucong.file.writer_;

import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriter_ {
    public static void main(String[] args) {

    }
    @Test
    public void write() throws IOException {
        char [] a = {'a','b','c'};
        //利用Java 7引入的新的try(resource)的语法，只需要编写try语句，让编译器自动为我们关闭资源。
        //一定要关闭流 或者flush才能真正把数据写入文件
      try(FileWriter fileWriter = new FileWriter("D:\\b.txt",true/** 默认是覆盖内容*/)){
          fileWriter.write("在细雨中呐喊");
          fileWriter.write('H');//写入单个字符
          fileWriter.write(a);//写入数组指定部分
          fileWriter.write("最后一头战象".toCharArray(),0,6);//写入指定数组的指定部分
          fileWriter.write("离开前请叫醒我",0,7);//写入字符串的指定部分
      }
    }
}
