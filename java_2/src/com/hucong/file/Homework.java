package com.hucong.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Homework {
    public static void main(String[] args) throws IOException {
        File file = new File("D:\\mytemp");
        if (!file.exists()) {
            if(file.mkdirs()) {
                System.out.println("文件夹创建成功");
            }else{
                System.out.println("文件夹已经存在 无需在创建");
            }
        }
        File file1 = new File("D:\\mytemp\\aa.txt");
        if (!file1.exists()){
            if (file.createNewFile()){
                System.out.println("文件创建成功");
                BufferedWriter bw = new BufferedWriter(new FileWriter(file1));
                bw.write("hello,word");
                bw.close();
            }else {
                System.out.println("文件已经存在 无需在创建");
            }
        }
    }
}
