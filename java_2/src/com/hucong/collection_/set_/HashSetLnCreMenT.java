package com.hucong.collection_.set_;

import java.util.HashSet;

public class HashSetLnCreMenT {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        /**
         * HashSet底层是HashMap 第一次添加时 table 数组扩容到16
         * 临界值(threshold)是16 * 加载因(FloadFactor)是0.75 = 12
         * 如果table 数组使用到了临界值 12 就会扩容到 16 * 2 = 32 新的临界值 32 * 0.75 = 24 依次类推
         */
        HashSet hashSet = new HashSet();
//        for (int i = 0; i <= 100; i++){
//            hashSet.add(i);
//        }
        /**
         * 在Java8 中 如果一条链表的元素个数到达 默认是8 并且table的大小 大于 默认64 就会树化(红黑树) 否则仍然采用数组扩容机制
         */
        for (int i = 0; i <= 7; i++){//在table的某一条链表上添加7个A对象
            hashSet.add(new A(i));
        }
        /**
         * 当我们向hashSet增加一个元素 -> Node -> 加入table 就算是增加一个
         * 就算是链表添加了12个也会进行扩容
         */
        for (int i = 0; i <= 7; i++){//在table的某一条链表上添加7个B对象
            hashSet.add(new B(i));
        }
    }
}
class B{
    private int n;

    public B(int n) {
        this.n = n;
    }
    // 统一返回100 就是加入到链表
    @Override
    public int hashCode(){
        return 200;
    }
}
class A{
    private int n;

    public A(int n) {
        this.n = n;
    }
    // 统一返回100 就是加入到链表
    @Override
    public int hashCode(){
        return 100;
    }
}
