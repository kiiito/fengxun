package com.hucong.collection_.collectionExercise;

import java.util.*;

@SuppressWarnings({"all"})
public class homework03 {
    public static void main(String[] args) {
        HashMap hashMap = new HashMap();
        hashMap.put("java",650);
        hashMap.put("tom",1200);
        hashMap.put("smith",2900);
        hashMap.put("java",2900);

        Set set = hashMap.keySet();
        for (Object map : set) {
            hashMap.put(map,(Integer)hashMap.get(map) + 100);
        }
        for (Object map : set) {
            System.out.println(map);
        }
        Collection values = hashMap.values();
        for (Object value : values) {
            System.out.println(value);
        }

        Set entrySet = hashMap.entrySet();
        Iterator iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Object key = iterator.next();
            Map.Entry entry = (Map.Entry) key;
            System.out.println(entry.getKey() + "-" + entry.getValue());

        }
    }
}
