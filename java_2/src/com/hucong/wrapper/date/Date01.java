package com.hucong.wrapper.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 第一代日期
 */
public class Date01 {
    public static void main(String[] args) throws ParseException {
        Date date = new Date();//获取当前系统时间
        Date date1 = new Date(98765);//通过指定毫秒数得到的时间
        System.out.println(date.getTime());//获取某个时间对应的毫秒数
        System.out.println(date1);

        // 创建SimpleDateFormat对象 可以指定相应的格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss E");
        String format = sdf.format(date);// format 将日期转换成指定格式的字符串
        System.out.println("当前日期 ="+ format);

        //可以把一个格式话的String 转成对应的 Date
        //在把String -> Date 使用的sdf格式需要和 String 格式一样否则会抛出转换异常
        String s = "2024年11月10日 13:16:10 星期一";
        System.out.println(sdf.parse(s));
    }
}
