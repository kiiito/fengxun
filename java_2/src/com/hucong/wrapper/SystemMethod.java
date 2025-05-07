package com.hucong.wrapper;

import java.util.Arrays;

public class SystemMethod {
    public static void main(String[] args) {
        System.out.println("1");
        //exit 退出当前程序
        // exit(0) 表示程序的退出 0 表示一个状态 正常的状态
//        System.exit(0);
//        System.out.println("2");//不会执行

        //arraycopy 复制数组元素 比较适合底层调用 一般使用Arrays.copyOf()完成复制数组
        int [] scr = {1,2,3};
        int [] dest = new int[3];//当前是{0，0，0}
        //Params:
        //src – the source array.   //源数组
        // srcPos – starting position in the source array. //从源数组的哪个索引位置开始拷贝
        // dest – the destination array //目标数组 即把源数组的数据拷贝到这个数组
        // destPos – starting position in the destination data. //把源数组的数据拷贝到目标数组的哪个索引
        // length – the number of array elements to be copied.//从源数组拷贝多少个数据到目标数组
        System.arraycopy(scr, 0, dest, 0,3);
        System.out.println("dest =" + Arrays.toString(dest));

        //currentTimeMillens 返回当前时间距离1970-1-1的毫秒数
        System.out.println(System.currentTimeMillis());
    }
}
