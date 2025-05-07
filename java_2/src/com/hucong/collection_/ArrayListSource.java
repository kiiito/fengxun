package com.hucong.collection_;

import java.util.ArrayList;

public class ArrayListSource {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        //创建一个ArrayList无参的构造器
        //ArrayList list = new ArrayList();
        ArrayList list = new ArrayList(8);
        for (int i = 0; i <= 10; i++) {
            /*
             public boolean add(E e) {
                    ensureCapacityInternal(size + 1);  // 判断是否需要扩容
                    elementData[size++] = e; // 然后在执行 赋值
                    return true;
                }
             */
            list.add(i);
        }
        for (int i = 11; i <= 15; i++) {
            list.add(i);
        }
        list.add(100);
        list.add(200);
    }
}
