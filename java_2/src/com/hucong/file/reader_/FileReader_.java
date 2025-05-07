package com.hucong.file.reader_;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;

public class FileReader_ {
    public static void main(String[] args) {

    }
    @Test
    public void read(){
        FileReader fileReader = null;
        int date = 0;
        char[] buffer = new char[8];
        try {
             fileReader = new FileReader("D:\\fd02.txt");
//             while ((date = fileReader.read()) != -1){
//                 System.out.print((char)date);
//             }
             while ((date = fileReader.read(buffer)) != -1){
                 System.out.print(new String(buffer,0,date));
             }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(fileReader != null){
                    fileReader.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
