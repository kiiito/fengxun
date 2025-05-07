package com.hucong.poly;

public class Master {
    private String name;

    public Master(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    //animal 编译类型是Animal，可以指向(接收) Animal子类的对象
    public void feed(Animal animal,Food food) {
        System.out.println("主人 " + name + " 给 " +animal.getName() + " 喂食 " + food.getName());

    }
}
