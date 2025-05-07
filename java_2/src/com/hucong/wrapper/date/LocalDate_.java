package com.hucong.wrapper.date;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * 第三代日期
 */
public class LocalDate_ {
    public static void main(String[] args) {
        LocalDateTime ldt =  LocalDateTime.now();
        System.out.println(LocalDate.now());  //返回当前日期 只有年月日
        System.out.println(LocalTime.now());//返回当前时间 只有时分秒
        System.out.println(ldt);
        System.out.println("年 = " + ldt.getYear());
        System.out.println("月 = " + ldt.getMonth());//返回英文的月份
        System.out.println("月 = " + ldt.getMonthValue());//返回数字的月份

        //DateTimeFormatter 对象进行格式化
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH小时mm分钟ss秒");

        System.out.println("格式化的日期 = " + dtf.format(ldt) );

        //提供了大量的方法 plus 和 minus 可以对当前时间进行加或减
        System.out.println( "加了890天的日期是 = " + dtf.format(ldt.plusDays(890)));
        System.out.println("减了600分钟的时间 =" +dtf.format(ldt.minusMinutes(600)));
    }
}
