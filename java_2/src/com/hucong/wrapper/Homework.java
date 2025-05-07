package com.hucong.wrapper;

import java.util.Scanner;

public class Homework {
    public static void main(String[] args) {
//        while (true){
//            System.out.println("请输入用户名");
//            Scanner scanner = new Scanner(System.in);
//            String name = scanner.nextLine();
//            if(name.length() >= 2 && name.length() <= 4){
//                return;
//            }
//            System.out.println("用户名必须是 2到4位长度");
//        }
        try {
            user("jack","123456","123456@qq.com");
            System.out.println("恭喜你，注册成功");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        String name = "hu cong ming";
        String str = "hu3876AKN45hu";
        modify(name);
        Judge(str);
    }
    public static void user(String name,String pwd,String email){
        if (!(name != null && pwd != null && email != null)){
            throw new RuntimeException("参数不能为空");
        }
        if (!(name.length() >=2 && name.length() <= 4)){
            throw new RuntimeException("用户名长度不对");
        }
        if(!(pwd.length() == 6 && isDigit(pwd))){
            throw new RuntimeException("密码的长度为6且要求全部是数字");
        }
//        if (email.indexOf("@.") == -1){
//            throw new RuntimeException("格式错误");
//        }
        int i = email.indexOf("@");
        int j = email.indexOf(".");
        if(!(i > 0 &&  j>i)){
            throw new RuntimeException("格式不正确");
        }
    }
    public static boolean isDigit(String str){
        char []arr = str.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            //通过字符的ASCLL 来判断这个字符是否是数字或者其他字符
            if (arr[i] < '0' || arr[i] > '9') {
                return false;
            }
        }
        return true;
    }

    /**
     * 编写Java 输入形式为 Hu Cong Ming 的人名 以 Ming,Hu .C的形式打印
     * @param str
     */

    public static void  modify(String str){
        //首先判断字符是否为空
        if (str == null){
            System.out.println("输入的字符不能为空");
            return;
        }
        //用split 按照空格分割 字符串
        String [] arr = str.split(" ");
        //如果长度不为3 退出
        if (arr.length != 3){
            System.out.println("输入的格式不正确");
            return;
        }
        //采用字符格式输出 最后一个是获取首字母大写
       String info = String.format("%s,%s .%c",arr[2],arr[0],arr[1].toUpperCase().charAt(0));
        System.out.println(info);
    }

    /**
     * 输入字符串 判断里面有多少个 大写字母 小写字母 多少个数字
     */
    public static void Judge(String str){
        if(str == null){
            System.out.println("输入的字符串不能为空");
            return;
        }
        //遍历字符串 定义计数器
        int numCount = 0;
        int lowerCount = 0;
        int upperCount = 0;
        for(int i=0; i<str.length(); i++){
            if(str.charAt(i) >= '0' && str.charAt(i) <= '9'){
                numCount++;
            }else if(str.charAt(i) >= 'a' && str.charAt(i) <= 'z'){
                lowerCount++;
            }else if(str.charAt(i) >= 'A' && str.charAt(i) <= 'Z'){
                upperCount++;
            }
        }
        System.out.println("数字有 " + numCount + " 小写字母有 " + lowerCount +" 大写字母有 " +upperCount);
    }
}
