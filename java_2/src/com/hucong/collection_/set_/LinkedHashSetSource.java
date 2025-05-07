package com.hucong.collection_.set_;

import java.util.LinkedHashSet;
import java.util.Set;
@SuppressWarnings({"all"})
public class LinkedHashSetSource {
    public static void main(String[] args) {
        /**
         *  1 LinkedHashSet 加入的顺序和取出来元素/数据的顺序一致
         *  2 LinkedHashSet 底层维护的是一个LinkedHashMap(HashMap的子类)
         *  3 LinkedHashSet 底层结构 (数组table + 双向链表)
         *  4 添加第一次 直接将 数组table 扩容 16 存放的结点类型是 LinkedHashMap$Entry
         *  5 数组是 HashMap$Node[] 存放的元素/数据是 LinkedHashMap$Entry 类型
         *  6 LinkedHashMap.Entry 是 HashMap.Node 的父类
         */
        Set set = new LinkedHashSet();
        set.add(new String("AA"));
        set.add(456);
        set.add(456);
        set.add("hc");
        System.out.println("set" + set);
    }
}
