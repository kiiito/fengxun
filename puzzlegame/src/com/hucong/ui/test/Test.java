package com.hucong.ui.test;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
        //要求把一个数组的数据 0-15 打乱顺序 然后再按四个一组的顺序添加到二维数组当中
        int []tampArr = {0,1,2,3,4,5,6,7,8,9,10,11,13,14,15};
        Random r = new Random();//获取的随机对象
        for (int i = 0; i < tampArr.length; i++) {
            int index = r.nextInt(tampArr.length);//获取随机索引
            int temp = tampArr[i];
            tampArr[i] = tampArr[index];
            tampArr[index] = temp;
        }
        for (Object o : tampArr) {
            System.out.print(o +" ");
        }
        System.out.println();
        //给二维数组添加元素
        int [][] data = new int[4][4];

        //第一种遍历一威数组
//        for (int i = 0; i < tampArr.length; i++) {
//            data[i / 4][i % 4] = tampArr[i];
//        }

        //第二中遍历 二维数组
//        int index = 0;
//        for (int i = 0; i < data.length; i++) {
//            for (int j = 0; j < data[i].length; j++){
//                data[i][j] = tampArr[index] ;
//                index++;
//            }
//        }
        int index = 0;
        for (int i = 0; i < data.length && index < tampArr.length; i++) {
            for (int j = 0; j < data[i].length && index < tampArr.length; j++){
                data[i][j] = tampArr[index];
                index++;
            }
        }
        //遍历二维数组
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length;j++){
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }

    }
}
