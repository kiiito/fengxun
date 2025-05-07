package com.hucong.file.outputstream;

import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStream_ {
    public static void main(String[] args) {

    }
    @Test
    public void write() throws IOException {
        /**
         * 1 如果输入的文件没有找到 则会自动创建
         * 2 new FileOutputStream("D:\\a.txt",true) 后面添加true是文件追加 如果不写则是文件覆盖
         * 3 "I love eula".getBytes("UTF-8") 可以把字符串改为你想要的编码 默认是字节数组
         * write(byte[] b,int off,int len) 将len字节从位于偏移量 off的指定字节数组写入此文件输出流
         */
        String str = "I love ganYu too";
        try(FileOutputStream fileOutputStream = new FileOutputStream("D:\\a.txt",true)){
            fileOutputStream.write("I love eula".getBytes("UTF-8"));
            fileOutputStream.write("我喜欢优菈".getBytes("UTF-8"));
            fileOutputStream.write(str.getBytes(),0,16);
        }
    }
}
