package com.hucong.single_;

/**
 * 单例模式懒汉式
 * 可能存在线程安全问题
 */
public class SingleTon02 {
    public static void main(String[] args) {
        //只有通过方法才会创建对象，并不像饿汉式，类的加载就已经创建好对象
        Cat cat = Cat.getInstance();
        System.out.println(cat);
        //再创建时，cat已经不为null，依旧返回 原来的cat
        Cat cat01 = Cat.getInstance();
        System.out.println(cat == cat01);
    }
}
class Cat{
    private String name;
    private static Cat cat;//创建一个默认为空的对象
    private Cat(String name) {
        this.name = name;
    }
    public static Cat getInstance(){
        if(cat == null){
             cat = new Cat("小橘");
        }
        return cat;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                '}';
    }
}