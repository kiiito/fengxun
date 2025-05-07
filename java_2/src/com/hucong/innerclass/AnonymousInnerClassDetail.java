package com.hucong.innerclass;

/**
 * 细节与内部类基本一样
 */
public class AnonymousInnerClassDetail {
    public static void main(String[] args) {
        Outer05 outer05 = new Outer05();
        outer05.xx();
    }
}

class Outer05{
    public void xx(){
        Person p =  new Person(){
            @Override
            public void hi() {
                System.out.println("匿名内部类 重写Hi方法");
            }
        };
        p.hi();

        //也可以换一种直接形式 匿名内部类本身也是返回对象
        new Person(){
            @Override
            public void say(String str) {
                super.say(str);
            }
        }.say("jack");

    }
}
class Person{
    public void hi(){
        System.out.println("say hi");
    }
    public void say(String str){
        System.out.println("say " + str);
    }
}