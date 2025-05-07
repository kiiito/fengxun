package com.hucong.wrapper;

import java.util.Arrays;
import java.util.Comparator;

public class ArraysMethod {
    public static void main(String[] args) {
        //遍历数组 Arrays.toString
        Integer[] a = {1,23,54,31};
        System.out.println(Arrays.toString(a));
        // sort 数组排序 可通过一个接口 Comparator 实现定制排序
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));
        Arrays.sort(a,new Comparator(){
            @Override
            public int compare(Object o1,Object o2){
                Integer a1 = (Integer)o1;
                Integer a2 = (Integer)o2;
                return a2 - a1; //返回正数就是正排序 返回负数 就是反排序
            }
        });
        System.out.println(Arrays.toString(a));
        Integer[] a1 = {1,2,6,8,36,98};
        //Arrays.binarySearch() 通过 二叉查找 要求该数组是有序的
        // 如果数组不存在该元素 就返回 return -(low + 1 ) low是可能是要寻找的数 比如 99 就是98相近的 下标为6 low就为6
        System.out.println("index = " + Arrays.binarySearch(a1,99));//返回-7

        //copyOf 数组的复制 从 a1数组中 拷贝 a1.length个元素到新数组当中
        //如果拷贝的长度 >   a1.length 的长度 就在新数组后面加 null
        Integer[] newArr = Arrays.copyOf(a1,a1.length);
        System.out.println(Arrays.toString(newArr));

        // fill 数组填充 将55全部替换掉原有数组的元素
        Arrays.fill(newArr,55);
        System.out.println(Arrays.toString(newArr));

        //equals 比较两个数组元素内容是否完全一致
        System.out.println(Arrays.equals(a1,newArr));

        //asList 将一组值 转换成List 集合
        System.out.println(Arrays.asList(2,3,4,5,6,1));
    }
}
