package com.hucong.collection_.map_;

import java.util.Comparator;
import java.util.TreeMap;
@SuppressWarnings({"all"})
public class TreeMap_ {
    public static void main(String[] args) {
        /**
         * 1 使用构造器 创建TreeMap 是无序(也没有排序)
         */
        TreeMap treeMap = new TreeMap(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                //按照传入的k的大小进行排序
                return ((String) o1).compareTo((String) o2);
                //return ((String) o1).length()-((String) o2).length();
            }
        });
        treeMap.put("eula","优菈");
        treeMap.put("ganYu","甘雨");
        treeMap.put("li","丽莎");
        treeMap.put("hu","胡");
        System.out.println("tree =" + treeMap);

        /**
         * 1 构造器 把传入的实现了Comparator接口的匿名内部类 传给了TreeMap 的comparator
             * public TreeMap(Comparator<? super K> comparator) {
             *         this.comparator = comparator;
             *     }
         * 2 调用put方法
         * 第一次添加 把k-v封装到Entry对象 放入root
         * public V put(K key, V value) {
         *         Entry<K,V> t = root;
         *         if (t == null) {
         *             compare(key, key); // type (and possibly null) check
         *
         *             root = new Entry<>(key, value, null);
         *             size = 1;
         *             modCount++;
         *             return null;
         *         }
         * 以后再添加
         *  Comparator<? super K> cpr = comparator;
         *         if (cpr != null) {
         *             do {//遍历所有的key 给当前的key找到合适的位置
         *                 parent = t;
         *                 cmp = cpr.compare(key, t.key);//动态绑定到我们的匿名内部类的compare
         *                 if (cmp < 0)
         *                     t = t.left;
         *                 else if (cmp > 0)
         *                     t = t.right;
         *                 else//如果遍历过程中 发现准备添加的key 和当前已有的key相等 就不添加
         *                     return t.setValue(value);
         *             } while (t != null);
         *         }
         */
    }
}
