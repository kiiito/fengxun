package com.hucong.collection_.map_;

import java.util.*;

@SuppressWarnings({"all"})
public class MapFor {
    public static void main(String[] args) {
        Map map = new HashMap();
        map.put("no1","eula");
        map.put("no2","ganYu");
        map.put("no3","hu");
        map.put("no2","keQing");//将ganYu替换成keQing
        map.put(null,null);//key 为 null只能有一个 但value可以有多个
        map.put("no4",null);
        map.put(new Object(),"lisa");//可以直接是一个object对象

        Set keySet = map.keySet();
        //可以用多种方法遍历
        // 第一种增强for循环
        for (Object key :keySet) {
            System.out.println(key + "-" + map.get(key));
        }
        System.out.println("===============================");
        //第二种 使用迭代器
        Iterator iterator = keySet.iterator();
        while (iterator.hasNext()) {
            Object key = iterator.next();
            Object value = map.get(key);
            System.out.println(key + "-" + value );
        }

        System.out.println("===============================");
        Collection values = map.values();
        //这里可以使用Collection的所有遍历方法 把所有的value取出
        //第一种 增强for
        for (Object value : values) {
            System.out.println(value);
        }
        System.out.println("===============================");
        //第二种 迭代器
        Iterator iterator1 = values.iterator();
        while (iterator1.hasNext()) {
            Object value =  iterator1.next();
            System.out.println(value);
        }

        System.out.println("===============================");
        //第三 通过EntrySet 来获取k-v
        Set entrySet = map.entrySet();
        for (Object entry : entrySet) {
            //将entry转成Map.entry 因为entry 没有get方法
            Map.Entry m = (Map.Entry) entry;
            System.out.println(m.getKey() + "-" + m.getValue());
        }
        System.out.println("===============================");
        Iterator iterator2 = entrySet.iterator();
        while (iterator2.hasNext()) {
            Object entry =  iterator2.next();
            //同样先将entry转成Map.entry
            Map.Entry m = (Map.Entry) entry;
            System.out.println(m.getKey() + "-" + m.getValue());
        }
    }
}
