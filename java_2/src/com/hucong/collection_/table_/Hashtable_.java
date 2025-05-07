package com.hucong.collection_.table_;

import java.util.Hashtable;

public class Hashtable_ {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        /**
         * 1 底层有数组 Hsahtable$Entry[] 初始化大小为11
         * 2 临界值 threshold 8 = 11 * 0.75
         * 3 Hashtable的键和值都不能为null 否则会抛出异常
         * 4 Hashtable的使用方法基本和HashMap一样
         */
        Hashtable table = new Hashtable();
        table.put("ganYu",100);
        //table.put(null,100);//异常
        //table.put("hu",null);//异常
        table.put("eula",80);
        table.put("eula",100);//替换
    }
}
