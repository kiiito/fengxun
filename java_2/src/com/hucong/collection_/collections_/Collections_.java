package com.hucong.collection_.collections_;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
@SuppressWarnings({"all"})
public class Collections_ {
    public static void main(String[] args) {
        //创建ArrayList 集合 用于测试
        List list = new ArrayList();
        list.add("ganYu");
        list.add("eula");
        list.add("lisa");
        list.add("hu");

        //reverse(list) 反转 list 中元素的顺序
        Collections.reverse(list);
        System.out.println("list = " + list);
        System.out.println("=============================");
        //shuffle(list) 对list 集合元素进行随机排序
        Collections.shuffle(list);
        System.out.println("list = " + list);
        System.out.println("=============================");
        //sort(list) 根据元素的自然顺序对指定 list 集合元素按升序排序
        Collections.sort(list);
        System.out.println("list = " + list);
        System.out.println("=============================");
        //sort(list,comparator) 根据指定的comparator 产生的顺序对list集合元素按照我们要求的排序
        //我们希望按照 字符串长度大小排序
        Collections.sort(list,new Comparator() {
            @Override
            public int compare(Object o1,Object o2) {
                return ((String)o2).length() - ((String)o1).length();
            }
        });
        System.out.println("字符串长度大小排序 = " + list);
        System.out.println("=============================");
        //swap(list,int,int) 将指定list集合的 i 处元素和j元素进行交换
        Collections.swap(list,0,1);
        System.out.println("交换后的情况 =" + list);

        //Object max(Collection) 根据元素的自然顺序 返回给定集合的最大元素
        System.out.println("自然元素最大元素 = " + Collections.max(list));
        //Object max(Collection,Comparator) 根据 Comparator 指定的顺序 返回给定集合中的最大元素
           Object maxObject = Collections.max(list,new Comparator(){
            @Override
            public int compare(Object o1, Object o2) {
                return ((String)o1).length() - ((String)o2).length();
            }
        });
        System.out.println("list = " + maxObject);
        //Object min(Collection) 根据元素的自然顺序 返回给定集合的最小元素
        //Object min(Collection,Comparator) 根据 Comparator 指定的顺序 返回给定集合中的最小元素

        //int frequency(Collection,object) 返回指定集合中指定元素出现次数
        System.out.println("hu出现的次数 =" + Collections.frequency(list,"hu"));

        //void copy(List dest,List src) 将src中的内容复制到dest中
        ArrayList dest = new ArrayList();
        //为了完成一个完整的拷贝 我们需要先给dest赋值 大小与list.size()一样 否则会报出异常
        for (int i = 0; i < list.size(); i++) {
            dest.add("");
        }
        Collections.copy(dest,list);
        System.out.println("dest = " + dest);

        //boolean replaceAll(list,object,object);
        //如果list中 有ganYu 就替换成 甘雨
        Collections.replaceAll(list,"ganYu","甘雨");
        System.out.println("list = " + list);
    }
}
