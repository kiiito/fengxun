package com.hucong.wrapper;

import java.util.Comparator;

public class ArraysSortCustom {
    public static void main(String[] args) {
        int [] arr = {-1,1,23,53,13};
        bubble(arr,new Comparator(){
            public int compare(Object o1,Object o2) {
                int a1 = (Integer)o1;
                int a2 = (Integer)o2;
                return a2 - a1;
            }
        });
    }
    public static void bubble(int [] arr,  Comparator c) {
        int temp = 0;
        for(int i = 0; i < arr.length - 1; i++){
            for(int j = 0; j < arr.length -i -1; j++){
                if(c.compare(arr[j],arr[j+1]) > 0){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }
}

