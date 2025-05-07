package homework;

public class Demo_9_30_1 {
    public static void main(String[] args) {
      //A.xx();
       //A.hh();
      //A.jj();
 A.kk();
        // A.ll();
        // A.gg();
      // A.oo();
//        A.ss();
    }
}

class A {
    public static void xx() {
        int count = 0;
        for (int i = 100; i < 999; i++) {
            int a = i / 100 % 10;
            int b = i / 10 % 10;
            int c = i % 10;
            if ((a * a * a + b * b * b + c * c * c) == i) {
                System.out.print(i + " ");
                count++;
            }
        }
        System.out.println("一个有" + count + "个水仙花数");
    }

    public static void hh() {
        for (int i = 20; i <= 200; i++) {
            boolean judge = true;
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    judge = false;
                    break;
                }
            }
            if (judge) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    public static void jj() {
        int a = 4;
        for (int i = 1; i <= a; i++) {
            for (int k = 1; k <= a - i; k++) {
                System.out.print(" ");
            }
            for (int j = 1; j <= 2 * i - 1; j++) {
                System.out.print("*");
            }
            System.out.println("");
        }
    }

    public static void kk() {
//        int count = 100;
//        int money = 100;
//        for (int i = 1; i <= count; i++) {
//            for (int j = 1; j <= count; j++) {
//                for (int k = 1; k <= count; k++) {
//                    if (i + j + k == count & (5 * i + 3 * j + k / 3 == money)) {
//                        System.out.println("公鸡" + i);
//                        System.out.println("母鸡" + j);
//                        System.out.println("小鸡" + k);
//                        System.out.println("===");
//                    }
//                }
//            }
//        }
        for (int i = 1; i <= 20;i++){
            for(int j = 1; j <= 34;j++){
               int k = 100 - i - j;
                if(5 * i + 3 * j + k / 3 == 100 && k % 3 == 0){
                    System.out.println("公鸡 " + i + " 母鸡 " + j + " 小鸡 "  + k);
                }
            }
        }
    }

    //    public static void oo(){
//        int sum = 0;
//        int temp = 0;
//        int count = 1;
//        for (int i = 1; i <= 20;i++) {
//           for(int j = 1; j <= i;j++){
//               count *= j;
//           }
//           sum += count;
//           count = 1;
//        }
//        System.out.println(sum);
//    }
    public static void ss() {
        int i = 1;
        long sum = 0;
        while (i <= 20) {
            sum += pp(i);
            i++;
        }
        System.out.println("1/" + sum);
    }

    public static long pp(int n) {
        if (n == 0 || n == 1) {
            return 1;
        } else {
            return n * pp(n - 1);
        }
    }

}
