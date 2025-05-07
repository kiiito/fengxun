package com.hucong.collection_.list_;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListExercise {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        List list = new ArrayList();
        for (int i = 0; i <10;i++){
            list.add("hello");
        }
//        Iterator iterator = list.iterator();
//        while (iterator.hasNext()) {
//            Object next =  iterator.next();
//            System.out.println(next);
//        }

        list.add(2,"hc");
        list.remove(7);
        list.set(7,"jack");
        System.out.println(list.get(5));
        for (Object obj : list) {
            System.out.println("list = " + obj);
        }
    }
}
