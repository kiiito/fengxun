package com.hucong.wrapper;

import java.awt.print.Book;
import java.util.Arrays;
import java.util.Comparator;

public class ArrayExercise {
    public static void main(String[] args) {
        Books[] books = new Books[4];
        books[0] = new Books("红楼梦",100);
        books[1] = new Books("金瓶梅新",90);
        books[2] = new Books("剑来",500);
        books[3] = new Books("Java从入门到放弃",300);

        Arrays.sort(books,new Comparator(){
            @Override
            public int compare(Object o1,Object o2){
                //这里是对Book数组排序 因此 o1 和 o2 就是book的对象
                Books a1 = (Books) o1;
                Books a2 = (Books) o2;
//                return a1.getPrice() - a2.getPrice(); //价格从小到大
//                return a2.getPrice() - a1.getPrice(); //价格从大到小
                return a1.getName().length() - a2.getName().length();//从名字的长度来排序
            }
        });
        System.out.println(Arrays.toString(books));
//
    }
}
class Books{
    private   String name;
    private   int price;

    public Books(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Books{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}