package homework;

import java.util.Scanner;

public class Demo_9_23_5 {
    public static void main(String[] args) {
     //   Demo_9_23_5 demo_9_23_5 = new Demo_9_23_5();
//        int balance = 20;
        int balance = 8;
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入你需要买的物品：");
        String goods = "";
        String info = "您购买了书本\t";
        int count = 0;
        boolean loop = true;
        do{
            goods = scanner.nextLine();
            switch (goods) {
//                case "书本":
//                    if (((balance - 12) < 0) || balance < 0) {
////                       loop = false;
//                        System.out.println("你的余额不足，无法购买此物品");
//                       break;
//                    } else {
//                        balance -= 12;
//                        System.out.println(info += goods += " ");
//                        System.out.println("你的余额还有" + balance);
//                        count++;
//                    }
//                    break;
                case "铅笔":
                    if (((balance - 1) < 0) || balance < 0 ) {
//                        loop = false;
                        System.out.println("你的余额不足，无法购买此物品");
                        break;
                    } else {
                        balance -= 1;
                        System.out.println(info += goods += " ");
                        System.out.println("你的余额还有" + balance);
                        count++;
                    }
                    break;
                case "橡皮":
                    if(((balance - 2) < 0) || balance < 0){
                        System.out.println("你的余额不足，无法购买此物品");
                        break;
                    }else {
                        balance -= 2;
                        System.out.println(info += goods += " ");
                        System.out.println("你的余额还有" + balance);
                        count++;
                    }
                    break;
                case "可乐":
                    if(((balance - 3) < 0) || balance < 0){
                        System.out.println("你的余额不足，无法购买此物品");
                        System.out.println("你的余额还有" + balance);
                        break;
                    }else {
                        balance -= 3;
                        System.out.println(info += goods += " ");
                        count++;
                    }
                    break;
                case "零食":
                    if(((balance - 5) < 0) || balance < 0){
                        System.out.println("你的余额不足，无法购买此物品");
                        break;
                    }else {
                        balance -= 5;
                        System.out.println(info += goods += " ");
                        System.out.println("你的余额还有" + balance);
                        count++;
                    }
                    break;
                default:
                    System.out.println("请输入正确的商品");
            }
            if(balance <= 0){
                loop = false;
            }

        }while (loop);
        System.out.println("你一共买了" + count + "\t样物品" + info);
    }
//public void xx(int balance){
//    if (balance < 0){
//                   return;
//                }
//}

}
