package com.hucong.collection_;

import java.util.ArrayList;

public class CollectionMethod {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
        //add 添加单个元素 可添加 object下所有的类型
        arrayList.add("jack");
        arrayList.add(10);// arrayList.add(new Integer(10))
        arrayList.add("jack");
        System.out.println("list = " + arrayList);
        //remove 删除指定元素 可以是小标 也可以是指定的元素
//        arrayList.remove("jack");
        arrayList.remove(1);
        System.out.println("list = " + arrayList);
        //contains 查找元素是否存在
        System.out.println(arrayList.contains("jack"));
        //size 获取元素的个数
        System.out.println(arrayList.size());
        //isEmpty 判断是否为空
        System.out.println(arrayList.isEmpty());
        //clear 清空
        arrayList.clear();
        System.out.println(arrayList);

        //addAll 添加多个元素 放入集合
        ArrayList arrayList1 = new ArrayList();
        arrayList1.add("三国演义");
        arrayList1.add("金瓶梅");
        arrayList.addAll(arrayList1);
        //containsAll 查找多个元素是否存在 放入集合
        System.out.println(arrayList.containsAll(arrayList1));
        //removeAll 删除多个元素 放入集合
        arrayList.add("聊斋");
        arrayList.removeAll(arrayList1);
        System.out.println(arrayList);
    }
}
