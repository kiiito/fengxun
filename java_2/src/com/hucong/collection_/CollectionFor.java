package com.hucong.collection_;

import java.util.ArrayList;
import java.util.Collection;

public class CollectionFor {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        Collection col = new ArrayList();
        col.add(new Book("三国演义","罗贯中",10.1));
        col.add(new Book("小李飞刀","古龙",5.1));
        col.add(new Book("红楼梦","曹雪芹",30.1));

        //使用增强for循环 底层还是迭代器 简化版的迭代器 快捷键 大写的 I
        for (Object  book: col) {
            System.out.println("book = " + book);
        }
        //增强for也可以用于数组
    }
}
