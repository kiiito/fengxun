package com.hucong.poly;

public class PolyExercise {
    public static void main(String[] args) {
        Sub sub = new Sub();
        System.out.println(sub.count);//属性看编译类型 Sub为编译类型 count = 20
        sub.display();//display 调用方法 属性看运行类型 Sub 20
        Base b = sub; //将sub 的指向地址给 b
        System.out.println(b == sub);//地址一致 其中的属性一致
        System.out.println(b.count); //编译类型 Base 10
        b.display();//运行类型 sub 20
        "hu".equals("abc");
    }
}
class Base{
    int count = 10;
    public void display(){
        System.out.println(this.count);
    }
}
class Sub extends Base{
    int count = 20;
    public void display(){
        System.out.println(this.count);
    }
}