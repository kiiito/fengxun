package com.hucong.exception_;

public class Homework05 {
    public static void main(String[] args) {
        try {
            if (args.length != 2){
                throw new ArrayIndexOutOfBoundsException("参数个数不正确");
            }
            int n1 = Integer.parseInt(args[0]);
            int n2 = Integer.parseInt(args[1]);
            double res = cal( n1,n2);
            System.out.println("计算结果 " + res);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException(e);
        }catch (NumberFormatException e){
            System.out.println("参数格式不正确 需要输出整数");
        }catch (ArithmeticException e){
            System.out.println("出现除零");
        }
    }
    public static double cal(int n1, int n2){
       return n1 /n2;
    }
}
