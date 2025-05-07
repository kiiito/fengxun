package com.hucong.collection_.list_;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ListExercise01 {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        List list = new ArrayList();
        list.add(new Book01("红楼梦", "曹雪芹", 100));
        list.add(new Book01("西游记", "吴承恩", 280));
        list.add(new Book01("三国演义", "罗贯中", 350));
        list.add(new Book01("水浒传", "施耐庵", 200));
        for (int i = 0; i < list.size() -1; i++) {
            for (int j = 0; j < list.size() - 1 - i; j++) {
                // 向下转型 因为list.get(j)  list.get(j+1) 都是Object类型
                if (((Book01) list.get(j)).getPrice() > ((Book01) list.get(j + 1)).getPrice()) {
                    Book01 temp = (Book01) list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
        for (Object o :list) {
            System.out.println(o);
        }


    }
}
class Book01{
    private String name;
    private String author;
    private double price;

    public Book01(String name, String author, double price) {
        this.name = name;
        this.author = author;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book01{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                '}';
    }
}
