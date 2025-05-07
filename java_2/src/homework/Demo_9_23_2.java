package homework;

import java.util.Scanner;

public class Demo_9_23_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num;
        num = scanner.nextInt();
        String s;
        s = (num % 2 == 0) ? "num 是一个偶数" : " num是一个奇数";
        System.out.println(s);
    }
}
