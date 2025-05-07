package com.hucong.file;

import org.junit.jupiter.api.Test;

import java.io.File;

public class Directory_ {
    public static void main(String[] args) {

    }
    @Test
    public void m(){
//        File file = new File("D:\\aaa02");
//        //这个delete只能删除空目录或某个文件
//        System.out.println(file.delete());//t
        //创建单级目录用mkdir多级目录用mkdirs
        File file1 = new File("D:\\a01");
        file1.mkdir();
        System.out.println(file1.exists());//t
        File file2 = new File("D:\\a0\\a02\\a03");
        file2.mkdirs();
        System.out.println(file2.exists());//t
        System.out.println(file1.delete());//t
        System.out.println(file2.delete());//只会删除a03
    }
}
