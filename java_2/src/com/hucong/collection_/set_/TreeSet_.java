package com.hucong.collection_.set_;
import java.util.Comparator;
import java.util.TreeSet;

@SuppressWarnings({"all"})
public class TreeSet_ {
    public static void main(String[] args) {
        /**
         * 1 当我们使用无参构造器时 创建TreeSet时 仍然是无序的
         * 2 使用TreeSet 提供的一个构造器 可以传入一个比较器(匿名内部类)
         * 3
         */
         TreeSet treeSet = new TreeSet(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                //下面 调用String的compareTo方法进行字符串大小比较
                return ((String) o1).compareTo((String) o2); //不能有字符相同 会覆盖
               // return ((String) o1).length()-((String) o2).length();//不能字符个数相同
            }
        });
         //添加数据
        treeSet.add("ganYu");
        treeSet.add("Yu");
        treeSet.add("eula");
        treeSet.add("H");

        System.out.println("tree = " + treeSet);
    }
}
