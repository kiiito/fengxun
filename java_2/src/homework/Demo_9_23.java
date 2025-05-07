package homework;

import java.util.Scanner;

public class Demo_9_23 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double radius;
        radius = scanner.nextDouble();
        double c;
        double s;
        double a = 3.14;
        c = radius * 2 * a;
        s = a * radius * radius;
        System.out.println("圆的周长为:" + c + "面积为:" + s);
    }
}
