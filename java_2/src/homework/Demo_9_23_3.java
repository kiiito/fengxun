package homework;

import java.util.Scanner;

public class Demo_9_23_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a,b,c,max;
        a = scanner.nextInt();
        b = scanner.nextInt();
        c = scanner.nextInt();
        max = a > b ? a > c ? a : b : b > c ? b :c;
        System.out.println("最大数是" + max);
    }
}
