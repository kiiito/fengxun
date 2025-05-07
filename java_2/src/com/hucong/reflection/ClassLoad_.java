package com.hucong.reflection;
import java.util.Scanner;

public class ClassLoad_ {
    public static void main(String[] args) throws ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入你的选择");
        String s = scanner.next();
        switch (s){
            case "1":
                //静态加载 如果编译加载相关类时没有发现这个类 就会报错 依赖性太强
               // Dog dog = new Dog();
                break;
                case "2":
                    //动态加载 运行时才会加载的类 如果没有运行到这里 没有这个类也不会报错 降低了依赖性
                    Class<?> none = Class.forName("none");
                    break;
        }
    }
}
