package homework.demo_12_2;

import java.util.Scanner;

public class homework06 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("请输入除数");
        int divisor = input.nextInt();
        System.out.println("捕获异常开始");
        try {
            int result  = 10 / divisor;
            System.out.println(result);
        } catch (ArithmeticException e) {
            System.out.println("捕获了异常" + e);
            System.out.println("注意除数不能为0");
        } finally {
            input.close();
        }
        System.out.println("捕获异常结束");
    }
}
