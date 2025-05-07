package com.hucong.collection_;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CollectionIterator {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        Collection col = new ArrayList();
        col.add(new Book("三国演义","罗贯中",10.1));
        col.add(new Book("小李飞刀","古龙",5.1));
        col.add(new Book("红楼梦","曹雪芹",30.1));

//        System.out.println(col);
        //遍历 col集合
        //1 先得到 col 对应的 迭代器
        Iterator iterator = col.iterator();
        // 2 使用while循环遍历
        /**
         * 快速生成while循环 快捷键 itit
         * ctrl + j 快捷键模板
         */
        while (iterator.hasNext()){//判断是否还存在数据
            //返回下一个元素类型是object
            Object obj = iterator.next();
            System.out.println("obj = " + obj);
        }
        //如果希望再次遍历 需要重置我们的迭代器
         iterator = col.iterator();
        System.out.println("第二次遍历");
        while (iterator.hasNext()){//判断是否还存在数据
            //返回下一个元素类型是object
            Object obj = iterator.next();
            System.out.println("obj = " + obj);
        }
    }
}
class Book{
    private String name;
    private String author;
    private double price;

    public Book(String name, String author, double price) {
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
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                '}';
    }
}