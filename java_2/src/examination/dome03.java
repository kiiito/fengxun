package examination;

import java.util.Scanner;

public class dome03 {
    public static void main(String[] args){
        int temp = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("请依次输入三个数");
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        if (a > b){
          temp = a;
          a = b;
          b = temp;
        }
        if(a > c){
            temp = a;
            a = c;
            c = temp;
        }
        if(b > c){
            temp = b;
            b = c;
            c = temp;
        }
        System.out.println("从小到大排序为" + a + " " + b + " " + c);
    }
}
