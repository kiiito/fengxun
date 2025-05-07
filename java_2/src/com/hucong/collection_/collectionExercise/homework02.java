package com.hucong.collection_.collectionExercise;

import java.util.ArrayList;
import java.util.Iterator;

@SuppressWarnings({"all"})
public class homework02 {
    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();
         Car bwm = new Car("宝马",400000);
        arrayList.add(bwm);
        arrayList.add(new Car("劳斯莱斯",800000000));
        arrayList.add(new Car("GTR",20000000));
        arrayList.add(new Car("法拉利拉法",300000000));

        Iterator iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            Object list =  iterator.next();
            System.out.println("list = " + list);
        }
        System.out.println("==========================");
        arrayList.remove(1);
        for (Object list : arrayList) {
            System.out.println(list);
        }
        System.out.println(arrayList.contains(bwm));//查找集合中是否有这个元素
        System.out.println(arrayList.size());//集合元素的个数
        System.out.println(arrayList.isEmpty());//判断集合是否为空
        ArrayList arrayList1 = new ArrayList();
        arrayList1.add(new Car("兰博基尼",20000000));
        arrayList1.add(new Car("法拉利918",300000000));
        arrayList.addAll(arrayList1);//添加多个元素 一般是将另一个集合添加进来
        System.out.println(arrayList.containsAll(arrayList1));//查找多个元素
        arrayList.removeAll(arrayList1);//删除多个元素
        arrayList1.clear();//清空
    }
}
class Car{
    private String name;
    private double price;

    public Car(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}