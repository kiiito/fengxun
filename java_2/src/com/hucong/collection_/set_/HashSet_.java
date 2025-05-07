package com.hucong.collection_.set_;

import java.util.HashSet;
import java.util.Set;

public class HashSet_ {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        /**
         * 1 构造器源码
         *  public HashSet() {
         *         map = new HashMap<>();
         *     }
         *     2 HashSet可以存放null 但只能有一个null 即元素不能重复
         *     3 取出的顺序虽然与存入的顺序不一定相同
         */

        Set hashSet = new HashSet();
        hashSet.add(null);
    }
}
