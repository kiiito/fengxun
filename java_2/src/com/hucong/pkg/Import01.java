//一个类中最多只有一个package
//package必须放在类的最上面
package com.hucong.pkg;

import java.util.Arrays;

public class Import01 {
    public static void main(String[] args) {
        int [] arr = {1,4,3,59,68,23};
        //引用了Java.util的工具包，进行排序
        Arrays.sort(arr);
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + "\t");
        }
    }
}
