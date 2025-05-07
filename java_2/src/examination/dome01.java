package examination;

import java.util.Scanner;

public class dome01 {
    public static void main(String[] args){
        int count = 0;
        System.out.println("请输入一个数\n");
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        while(num > 0){
            num = num / 10;
            count++;
        }
        System.out.println("num是" + count + "位数");
    }
}
