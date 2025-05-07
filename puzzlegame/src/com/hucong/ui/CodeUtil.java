package com.hucong.ui;

import java.util.Random;

public class CodeUtil {
   static StringBuilder str = new StringBuilder();
    static String [] num = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
    static String [] letter = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
            "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    static String [] letter_upper = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
            "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    static Random r = new Random();

    public static String getCode(){
        for(int i=0; i<5;i++){
            switch (r.nextInt(3)){
                case 0:
                    str.append(num[r.nextInt(10)]);
                    break;
                case 1:
                    str.append(letter[r.nextInt(26)]);
                    break;
                case 2:
                    str.append(letter_upper[r.nextInt(26)]);
                    break;
                default:
                    break;
            }
        }

    String strNew = str.toString();
        str = new StringBuilder();
    return strNew;
    }
}
