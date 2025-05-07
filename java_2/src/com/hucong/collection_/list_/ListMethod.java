package com.hucong.collection_.list_;

import java.util.ArrayList;
import java.util.List;

public class ListMethod {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("jack");
        list.add("hc");
        list.add("tom");
        list.add("tom");
        //在index = 1 处插入一个对象
        list.add(1,"lisa");
        System.out.println("list = " + list);
        //获取一个对应索引的对象
        System.out.println(list.get(2));
        List list1 = new ArrayList();
        list1.add("1");
        list1.add("2");
        //addAll
        //list.addAll(list1);
        //也可以在指定的位置插入
        list.addAll(2,list1);
        System.out.println("list = " + list);
        //indexOf 返回obj在当前集合首次出现的位置
        System.out.println(list.indexOf("hc"));
        //lastIndexOf 返回obj在当前集合末次出现的位置
        System.out.println(list.lastIndexOf("tom"));
        //remove 删除元素
        //System.out.println(list.remove("tom"));//如果没有返回false 有返回ture
        System.out.println(list.remove(5));//删除索引元素 并返回该元素
        System.out.println("list = " + list);
        //set 替换元素
        System.out.println(list.set(2,"3"));//替换该索引的元素 并返回该元素
        System.out.println("list = " + list);
        //list.subList(0, 2) 返回从0 到 2 的子集合
        List subList = list.subList(0, 2);
        System.out.println("subList "+ subList);
    }
}
