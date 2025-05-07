package com.hucong.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class BufferedCopy {
    public static void main(String[] args)throws Exception {
        BufferedInputStream b = new BufferedInputStream(new FileInputStream("C:\\Users\\∑Á—∞\\Desktop\\mv.mp4"));
        BufferedOutputStream c = new BufferedOutputStream(new FileOutputStream("D:\\a.mp4"));
        int readData = 0;
        byte[] bytes = new byte[1024];
        while ((readData = b.read(bytes)) != -1) {
            c.write(bytes,0,readData);
        }
        System.out.println("øΩ±¥ÕÍ≥…");
    }
}
