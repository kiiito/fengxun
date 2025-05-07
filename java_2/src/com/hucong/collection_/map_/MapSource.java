package com.hucong.collection_.map_;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"all"})
public class MapSource {
    public static void main(String[] args) {
        Map map = new HashMap();
        map.put("no1","eula");
        map.put("no2","ganYu");
        /**
         * 1 k - v 最后是HashMap$Node node = new Node(hash,key,value,null)
         * 2 k - v 为了方便程序员的遍历 创建 EntrySet 集合 该集合存放的元素的类型 Entry
         * 而一个 Entry对象就有k v EntrySet<Entry<k,v>>
         * 3 entrySet 中 定义的类型是Map.Entry 但是实际上存放的还是 HashMap$Node
         * 这是因为static class Node<k,v> implement Map.Entry<k,v>
         * 4 当把HashMap$Node 对象 存放到 entrySet 就方便我们的遍历 因为Map.Entry提供了重要的方法
         * K getKey() V getValue()
         */
        Set set = map.entrySet();
        System.out.println(set.getClass());//HashMap$EntrySet
        for (Object obj : set) {
            //为了从HashMap$Node取出 k-v
            // 1 先做一个向下转型
            Map.Entry entry = (Map.Entry) obj;
            System.out.println(entry.getClass() + "-" + entry.getValue());
        }

        //有单独存储key和value的
        Set set1 = map.keySet();
        System.out.println(set1.getClass());//HashMap$KeySet
        Collection values = map.values();
        System.out.println(values.getClass());//HashMap$Values

    }
}
