package com.hucong.enum_;

/**
 * 演示枚举常用方法应用实例
 */
public class EnumMethod {
    public static void main(String[] args) {
        Season02 autumn = Season02.AUTUMN;
        // 1 name 返回当前对象名(常量名) 子类中不能重写
        System.out.println(autumn.name());
        // 2 ordinal 返回当前对象的位置编号 默认从0开始
        System.out.println(autumn.ordinal());
        // 3 values 返回当前枚举类中所有的常量
        Season02[] values = Season02.values();
        //使用增强for
        for(Season02 season : values) { //数据类型 变量名 : 对象
            System.out.println(season);
        }
        // 4 valueOf 将字符串转换成枚举对象 要求字符串必须为已有的常量名 否则报异常
        Season02 autumn1 = Season02.valueOf("AUTUMN");
        System.out.println(autumn1);
        System.out.println(autumn == autumn1);
        // 5 compareTo 比较两个枚举常量 比较的就是编号
        //   Season02.SPRING的编号[0] - Season02.AUTUMN的编号[2] -2
        System.out.println(Season02.SPRING.compareTo(Season02.AUTUMN));
    }
}
