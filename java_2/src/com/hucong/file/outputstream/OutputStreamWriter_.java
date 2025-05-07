package com.hucong.file.outputstream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class OutputStreamWriter_ {
    public static void main(String[] args) throws IOException {
        String charSet = "gbk";
        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("D:\\c.txt"),charSet);
        osw.write("我喜欢优菈");
        osw.close();
        System.out.println("按照 " + charSet + " 保存文件成功");
    }
}
