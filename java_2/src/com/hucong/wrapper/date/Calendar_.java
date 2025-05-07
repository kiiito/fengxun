package com.hucong.wrapper.date;
/**
 * 第二代日期
 */

import java.util.Calendar;

public class Calendar_ {
    public static void main(String[] args) {
        //Calendar是一个抽象类 并且构造器是私有化
        //可以通过 getInstance()类获取实例化
        Calendar c = Calendar.getInstance();//创建实例对象 比较简单 自由
        System.out.println(c);
        //获取日历对象的某个日历字段
        //Calendar没有提供相应的格式化的类 需要自己组合
        System.out.println("年:" + c.get(Calendar.YEAR));
        System.out.println("月:" + c.get(Calendar.MONTH) + 1);//Calendar返回月时 是按照 0 开始编号的 需要加一
        System.out.println("日:" + c.get(Calendar.DAY_OF_MONTH));
        System.out.println("小时:" + c.get(Calendar.HOUR));//如果要是二十四小时字段则改成Calendar.HOUR_OF_DAY
        System.out.println("分钟:" + c.get(Calendar.MINUTE));
        System.out.println("秒:" + c.get(Calendar.SECOND));
        System.out.println(c.get(Calendar.YEAR) + "年" + (c.get(Calendar.MONTH) + 1) + "月" + c.get(Calendar.DAY_OF_MONTH)+"日");
    }
}
