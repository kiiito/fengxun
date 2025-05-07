package com.hucong.collection_.set_;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SetMethod {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        /**
        1 以set接口实现类 HashSet 类讲解set接口的方法
         2 set接口实现类的对象(set接口对象) 不能存放重复的元素 可以存放一个null
         3 set接口对象存放数据是无序的 即添加的顺序与取出的顺序不同
         4 取出的顺序虽然与存入的顺序不同 但他取出的顺序是固定的
         5 set接口对象不可以通过获取索引来获取 所以不能够用for来遍历 没有get方法
         6 set接口collection的子接口 所以后者的方法set都可以调用
         */
        Set set = new HashSet();
        set.add("eual");
        set.add("eual");//重复
        set.add("ganYu");
        set.add("hu");
        set.add(null);

        //遍历
        //1 迭代器
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Object next =  iterator.next();
            System.out.println("next= " + next);
        }
        System.out.println("=====================");
        //2 增强for
        for (Object obj :set) {
            System.out.println("obj=" + obj);
        }

    }
}
