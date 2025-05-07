package com.hucong.collection_;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CollectionExercise01 {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        List list= new ArrayList();
        list.add(new Dog("大黄",100));
        list.add(new Dog("小黑",200));
        list.add(new Dog("大壮",300));

        for (Object dog : list) {
            System.out.println("list = " + dog);
        }
        System.out.println("===============================");
        //获取迭代器
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            Object obj = iterator.next();
            System.out.println("list = " + obj);
        }
    }
}
class Dog{
    private String name;
    private double price;

    public Dog(String name, double price) {
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
        return "Dog{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
