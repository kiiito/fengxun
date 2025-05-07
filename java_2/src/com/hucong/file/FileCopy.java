package com.hucong.file;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy {
    public static void main(String[] args) {

    }
    @Test
    public  void copy() throws IOException {
        int readDate = 0;
        byte[] bytes = new byte[1024];
        try(FileInputStream fileInputStream = new FileInputStream("D:\\1.jpg")){
            while ((readDate = fileInputStream.read(bytes)) != -1){
                try(FileOutputStream fileOutputStream = new FileOutputStream("D:\\a00",true)){
                  fileOutputStream.write(bytes,0,readDate);
                    System.out.println("转化成功");
                }
            }
        }
    }

}
