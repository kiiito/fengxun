package com.hucong.generic;
import java.util.ArrayList;
import java.util.List;

public class GenericExtends {
    public static void main(String[] args) {
        /**
         *  泛型的继承和通配符
         * 1 泛型没有继承性
         * 2 <?> 支持任意泛型类型
         * 3 <? extends A> 支持A类以及A类的子类 规定了泛型的上限
         * 4 <? super A> 支持A类以及A类的父类 不限于直接父类 规定了泛型的下限
         */
        Object s = new String("xxx");
        //泛型没有继承性
        //List<Object> list = new ArrayList<String>();报错

        //举例说明下面三个方法的使用
        List<Object> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<AAAA> list3 = new ArrayList<>();
        List<BBBB> list4 = new ArrayList<>();
        List<CCCC> list5 = new ArrayList<>();

        //List<?> 任意泛型都可以接受
        printCollection1(list1);
        printCollection1(list2);
        printCollection1(list3);
        printCollection1(list4);
        printCollection1(list5);

        //List<? extends AA> 表示上限 可以接受 AA 或者 AA的子类
//        printCollection2(list1);//f
//        printCollection2(list2);//f
        printCollection2(list3);
        printCollection2(list4);
        printCollection2(list5);

        //List<? super AA> 表示下限 可以接受AA类以及AA类的父类 不限于直接父类
        printCollection3(list1);
        //printCollection3(list2);//f
        printCollection3(list3);
       // printCollection3(list4);//f
        //printCollection3(list5);//f
    }
    //List<?> 表示任意泛型类型都可以接受
    public static void printCollection1(List<?> c){
        for (Object object : c) {//通配符 取出时 就是object
            System.out.println(object);
        }

    }
    //List<? extends AA> 表示上限 可以接受 AA 或者 AA的子类
    public static void printCollection2(List<? extends AAAA> c){
        for (Object object : c) {
            System.out.println(object);
        }

    }
    //List<? super AA> 表示下限 可以接受AA类以及AA类的父类 不限于直接父类
    public static void printCollection3(List<? super AAAA> c){
        for (Object object : c) {
            System.out.println(object);
        }

    }
}
class AAAA{}
class BBBB extends AAAA{}
class CCCC extends AAAA {}