package com.hucong.file.inputstream;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 演示FileInputStream的使用 字节输入流 文件 -> 程序
 */
public class FileInputStream_ {
    public static void main(String[] args) {

    }
    @Test
    public void readFile(){
        FileInputStream fileInputStream = null;
        int readData = 0;
        try {
            fileInputStream = new FileInputStream("D:\\test.txt");
            //从该输入流读取一个字节的数据 如果没有输入可用 此方法将阻止
            //如果返回-1 表示读取完毕
            while ((readData = fileInputStream.read()) != -1){
                System.out.print((char) readData);//转成char显示
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                //关闭文件流 释放资源
                fileInputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
    @Test
    /**
     * 用try ... finally来编写上述代码会感觉比较复杂，
     * 更好的写法是利用Java 7引入的新的try(resource)的语法，
     * 只需要编写try语句，让编译器自动为我们关闭资源
     */
    public void readFile01() throws IOException {
        int readDate = 0;
        try( InputStream fileInputStream = new FileInputStream("D:\\test.txt")){
            while ((readDate = fileInputStream.read()) != -1){
                System.out.print((char)readDate);
            }
        }
    }
    @Test
    public void readFile02() throws IOException {
        byte[] bytes = new byte[8];
        int readDate = 0;
        try( InputStream fileInputStream = new FileInputStream("D:\\test.txt")){
            //如果读取正常 返回的是读取字节数
            while ((readDate = fileInputStream.read(bytes)) != -1){
                System.out.print(new String(bytes,0,readDate));
            }
        }
    }
}
