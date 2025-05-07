package com.hucong.codeblock;

/**
 * 代码块
 */

public class CodeBlock {
    public static void main(String[] args) {
        Movie movie = new Movie("星际穿越");
        System.out.println("---------");
        Movie movie1 = new Movie("毒液3", 100);
    }
}

class Movie {
    private String name;
    public double price;

    //代码块的调用顺序优先于构造器
    //我们可以把相同的语句放在一个代码块中
    {
        System.out.println("电影正在开始----");
    }

    public Movie(String name) {
        System.out.println("构造器1");
        this.name = name;
    }

    public Movie(String name, double price) {
        System.out.println("构造器2");
        this.name = name;
        this.price = price;
    }
}