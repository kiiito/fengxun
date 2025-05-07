package com.hucong.exception_;

import java.util.Scanner;

public class TryCatchExercise {
    public static void main(String[] args) {
        //如果用户不是输入一个整数 就提示他反复输入 直到输入一个整数为止
        Scanner scanner = new Scanner(System.in);
        int num = 0;
        while (true){
            try {
                num = Integer.parseInt(scanner.next());
                break;
            } catch (NumberFormatException e) {
                System.out.println("你输入的不是一个整数");
            }

        }
        System.out.println("你输入的值是" + num);
    }
}
