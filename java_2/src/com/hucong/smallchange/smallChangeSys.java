package com.hucong.smallchange;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class smallChangeSys {
    public static void main(String[] args) {
        //定义相关变量
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        String key = "";
        //完成零钱通明细
        //1 可以把收益入账和消费保存到数组 2 可以使用对象 3 简单的话可以使用string拼接
        String details = "------------零钱通明细------------";
        //完成收益入账
        //定义变量
        double balance = 0;
        double money = 0;
        Date date = new Date();//date类型 表示时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); //自定义时间格式化

        //消费
        String note = "";

        //退出
       // String key02 = "";


        do{
            System.out.println("\n=========零钱通=========");
            System.out.println("\t\t\t零钱通明显");
            System.out.println("\t\t\t收益入账");
            System.out.println("\t\t\t消费");
            System.out.println("\t\t\t退     出");

            System.out.println("请输入1-4");
            key = scanner.next();
            switch (key){
                case "1":
                    System.out.println(details);
                    break;
                case "2":
                    System.out.println("收益入账金额:");
                    money = scanner.nextDouble();
                    if(money <= 0){
                        System.out.println("您输入的金额不合法");
                        break;
                    }
                    balance += money;
                    date = new Date();//获取当前日期
                    details += "\n收益入账\t+" + money + "\t" + sdf.format(date) + "\t" + balance;
                    break;
                case "3":
                    System.out.println("消费金额:");
                    money = scanner.nextDouble();
                    if(money > balance || money <= 0){
                        System.out.println("您输入的金额不合法");
                        break;
                    }
                    System.out.println("消费目的");
                    note = scanner.next();
                    balance -= money;
                    date = new Date();
                    details += "\n"+ note +"\t-" + money + "\t" + sdf.format(date) + "\t" + balance;
                    break;
                case "4":
                    System.out.println("4 退     出");
//                    do{
//                        System.out.println("请输入y/n");
//                        key02 = scanner.next();
//                        if (key02.equals("y")){
//                            loop = false;
//                        }else if(key02.equals("n")){
//                            loop = true;
//                        }
//                    }while ( !key02.equals("y") && !key02.equals("n"));

                    //用while + break 来接收输入的值是否是y或n
                    String choice = "";
                    while (true){
                        System.out.println("您确定要退出吗？ y/n");
                        choice = scanner.next();
                        if("y".equals(choice) || "n".equals(choice)){
                            break;
                        }
                    }
                    //当用户退出while，进行判断
                    if(choice.equals("y")){
                        loop = false;
                    }
                    break;
                default:
                    System.out.println("您的输入错误，请输入1-4");
            }
        }while (loop);
        System.out.println("========退出了零钱通========");
    }
}
