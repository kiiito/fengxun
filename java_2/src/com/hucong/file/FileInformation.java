package com.hucong.file;

import org.junit.jupiter.api.Test;

import java.io.File;

public class FileInformation {
    public static void main(String[] args) {

    }
    @Test
    public void info(){
        File file = new File("D:\\aaa01");
        System.out.println("文件名称 = " + file.getName());
        System.out.println("文件绝对路径 = " + file.getAbsoluteFile());
        System.out.println("文件父级目录 = " + file.getParent());
        System.out.println("文件大小 = " + file.length());
        System.out.println("文件名称是否存在 = " + file.exists());
        System.out.println("是不是应该文件 = " + file.isFile());
        System.out.println("是不是应该目录 = " + file.isDirectory());
    }
}
