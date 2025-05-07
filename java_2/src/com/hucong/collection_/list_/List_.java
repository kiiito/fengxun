package com.hucong.collection_.list_;

import java.util.ArrayList;
import java.util.List;

public class List_ {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        //1 list 集合类中元素有序 即添加顺序和取出顺序一致 且可以重复
        List list = new ArrayList();
        list.add("jack");
        list.add("jack");
        list.add("hc");
        list.add("tom");
        //2 list 集合每个元素都对应顺序索引 是从0开始
        System.out.println(list.get(2));//hc
    }
}
