package com.hucong.collection_;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Collection_ {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        /**
         * 集合主要是两组 单列集合 双列集合
         * Collection 接口有两个重要的接口 list set 他们实现的子类都是单例集合
         * Map 接口实现的子类 是双列集合
         */
        ArrayList arrayList = new ArrayList();
        arrayList.add("jack");
        arrayList.add("tom");

        HashMap hashMap = new HashMap();
        hashMap.put("第一名","jack");
        hashMap.put("第二名","tom");
    }
}
