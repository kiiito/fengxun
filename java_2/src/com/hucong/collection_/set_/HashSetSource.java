package com.hucong.collection_.set_;

import java.util.HashSet;

public class HashSetSource {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        HashSet hashSet = new HashSet();
        hashSet.add("java");
        hashSet.add("php");
        hashSet.add("java");
        System.out.println("set ="+ hashSet);

        /**
         * 源码解析
         * 1 执行HashSet()
         * public HashSet() {
         *         map = new HashMap<>();
         *         }
         *  2 执行 add()
         *   public boolean add(E e) {  //e = "java"
         *         return map.put(e, PRESENT)==null;
         *     }
         *   3 执行put() 该方法会执行hash(key) 得到key对应的hash值
         *   public V put(K key, V value) {//key = "java" value = PRESENT 共享
         *         return putVal(hash(key), key, value, false, true);
         *     }
         *     4 执行
         *     final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
         *                    boolean evict) {
         *         Node<K,V>[] tab; Node<K,V> p; int n, i;// 定义了辅助变量
         *
         *         //table 就是HashMap 的一个数组 类型是 Node[]
         *         // if 语句表示如果当前table 是null 或者 大小 = 0
         *         //就是第一次扩容 到16个空间
         *         // if ((tab = table) == null || (n = tab.length) == 0)
         *                      n = (tab = resize()).length;
         *
         *         // 1 根据key 得到hash 去计算该key 应该存放到table表的哪个索引位置 并把这个位置的对象 赋给p
         *         // 2 判断p 是否为null
         *         // 如果p为null 表示没有存放元素 就创建一个Node (key = "java" value = PRESENT)
         *         // 就放在该位置 tab[i] = newNode(hash, key, value, null);
         *
         *
         *         if ((tab = table) == null || (n = tab.length) == 0)
         *             n = (tab = resize()).length;
         *         if ((p = tab[i = (n - 1) & hash]) == null)
         *             tab[i] = newNode(hash, key, value, null);
         *         else {
         *             Node<K,V> e; K k;
         *
         *             //如果当前索引位置对应的链表的第一个元素和准备添加key的hash值一样 并且满足下面条件之一
         *             // 1 准备加入的key 和 p 指向的 Node 的节点的 key 是同一个对象
         *             // 2  p 指向的Node 结点的key 的equals() 和准备加入的key比较后相同
         *             // 就不能加入
         *
         *             if (p.hash == hash &&
         *                 ((k = p.key) == key || (key != null && key.equals(k))))
         *                 e = p;
         *                 //再判断 p 是不是一颗红黑树
         *                 //如果是一颗红黑树 就调用 putTreeVal来添加
         *             else if (p instanceof TreeNode)
         *                 e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
         *             else {
         *
         *             //如果table对应索引位置 已经是一个链表 就使用for循环比较
         *             // 1 依次和该链表的每一个元素对比 都不相同 则加入到该链表的最后
         *             // 注意 在把元素添加在链表后 立即判断 该链表是否达到8个结点 就调用  treeifyBin()
         *             // 对当前这个链表进行树化
         *             // 注意 转化成红黑树 要进行判断
         *             // if (tab == null || (n = tab.length) < MIN_TREEIFY_CAPACITY)//MIN_TREEIFY_CAPACITY = 64
         *             resize();
         *             // 如何上面条件成立 先tabler扩容
         *             //只有上面条件不成立时 才进行转成红黑树
         *             // 2 依次和该链表的每一个元素对比 如果有相同的情况 就直接break
         *                 for (int binCount = 0; ; ++binCount) {
         *                     if ((e = p.next) == null) {
         *                         p.next = newNode(hash, key, value, null);
         *                         if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
         *                             treeifyBin(tab, hash);
         *                         break;
         *                     }
         *                     if (e.hash == hash &&
         *                         ((k = e.key) == key || (key != null && key.equals(k))))
         *                         break;
         *                     p = e;
         *                 }
         *             }
         *             if (e != null) { // existing mapping for key
         *                 V oldValue = e.value;
         *                 if (!onlyIfAbsent || oldValue == null)
         *                     e.value = value;
         *                 afterNodeAccess(e);
         *                 return oldValue;
         *             }
         *         }
         *         ++modCount;
         *         if (++size > threshold)
         *             resize();
         *         afterNodeInsertion(evict);
         *         return null;
         *     }
         */
    }
}
