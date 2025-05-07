package com.hucong.file;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Homework02 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("D:\\fd02.txt"));
        String line = "";
        int i = 1;
        while ((line = br.readLine()) != null) {
            System.out.println("µÚ "+ i + " ÐÐ :" + line );
            i++;
        }
        br.close();
    }
}
