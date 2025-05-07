package com.hucong.extend_;

public class Base {
    //父类的无参构造器
     public String name;
     public  int age;
     private int n4 = 400;
    public Base() {
        System.out.println("父类无参构造器Base被调用");
    }

    public Base(String name, int age) {
        System.out.println("父类String name, int age构造器Base被调用");
    }
    //私有的属性可以通过父类公共的方法来调用
    public int getN4(){
        return n4;
    }
    private void testN4(){
        System.out.println("testN4");
    }
    //私有的方法可以通过父类公共的方法来调用
    public void getTestN4(){
       testN4();
    }

}
