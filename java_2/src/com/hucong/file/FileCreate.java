package com.hucong.file;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class FileCreate {
    /**
     * 创建文件的三种方法
     * @param args
     */
    public static void main(String[] args) {

    }
    @Test
    public void create01(){
        File file = new File("D:\\aaa01");
        try {
            file.createNewFile();
            System.out.println("创建成功");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void create02(){
        File parentFile = new File("D:\\");
        String fileName = "aaa02";
        //这里的file只是个对象 只有执行了createNewFile才会在磁盘创建该文件
        File file = new File(parentFile, fileName);
        try {
            file.createNewFile();
            System.out.println("创建成功");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void create03(){
        String parentFile = "D:\\";
        String fileName = "aaa03";
        File file = new File(parentFile, fileName);
        try {
            file.createNewFile();
            System.out.println("创建成功");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
