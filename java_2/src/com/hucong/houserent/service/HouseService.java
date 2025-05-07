package com.hucong.houserent.service;

import com.hucong.houserent.domain.House;

/**
 * 定义house数组 保存house对象
 * 响应houseView的调用
 * 完成对房屋信息的各种操作
 */
public class HouseService {
    private House[] houses;

    private int houseNums = 1;//因为添加过一位 所以初始化为1
    private int idCount = 1;
    public HouseService(int size) {
        houses = new House[size];//当创建HouseService对象，指定数组大小
        houses[0] = new House(1,"jack","110","北京二环",100000,"未出租");

    }
    //返回house
    public House[] list() {
        return houses;
    }
    //add方法，添加新对象，返回boolean
    public boolean add(House newHouse) {
        //判断是否能够继续添加
        if(houseNums == houses.length){
            System.out.println("数组已满，无法再添加");
            return false;
        }
        houses[houseNums++]  = newHouse;
//        houseNums++;
//        idCount++;
        newHouse.setId(++idCount);
        return true;
    }
    //del方法,删除一个房屋信息
    public boolean del(int delId){
       //应当找到要删除的房屋信息对应的下标
       int index = -1;
        for (int i = 0; i < houseNums; i++) {
            if(delId == houses[i].getId()){
                index = i;
            }
        }
        if(index == -1){
            return false;
        }
        //删除，用后面的 i + 1 的信息给 i 然后需要少运行一次 也算是少一个值 最后面是null
        for (int i = index; i < houseNums - 1; i++) {
            houses[i] = houses[i +1];
        }
//        houses[houseNums - 1] = null;
//        houseNums--;
        houses[--houseNums] = null;//把当前有存在的房屋信息的最后一个设置为null
        return true;
    }
    //seek方法 查找房屋信息
//    public int seek(int seekIn){
//        int index = -1;
//        for (int i = 0; i < houseNums; i++) {
//            if(seekIn == houses[i].getId()){
//                index = i;
//                return index;
//            }
//        }
//        return index;
//    }
    public House seek(int seekIn){
        for (int i = 0; i < houseNums; i++) {
            if (seekIn == houses[i].getId()){
                return houses[i];
            }
        }
        return null;
    }
}
