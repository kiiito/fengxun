package com.hucong.file.reader_;

import java.io.FileReader;
import java.io.BufferedReader;
public class BufferReader_ {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferReader = new BufferedReader(new FileReader("D:\\fd02.txt"));
        //按行读取
        //bufferReader.readLine() 是按行读取文件
        //当返回null时 表示文件读取完毕
        String line;
        while ((line = bufferReader.readLine()) != null) {
            System.out.println(line);
        }
        //关闭流 只需要关闭BufferedReader 因为底层会自动关闭节点流FileReader
        /*
                 public void close() throws IOException {
                synchronized (lock) {
                    if (in == null)//这里的in 就是我们传入的new FileReader("D:\\fd02.txt")
                        return;
                    try {
                        in.close();
                    } finally {
                        in = null;
                        cb = null;
                    }
                }
            }
         */
        bufferReader.close();
    }
}
