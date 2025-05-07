package com.hucong.codeblock;

public class CodeBlockDetail {
    public static void main(String[] args) {
        //创建对象实例时类被加载，代码块也加载
        AA aa = new AA();
        //创建子类对象实例时，父类也会被加载
        AA aa1 = new AA();
        //使用类的静态成员时(静态属性，静态方法)
        System.out.println(Cat.sum);
        //不管创建多少个DD的实例对象，static代码块只会执行一次
        //DD dd = new DD();
        //如果只是使用类的静态成员时，普通代码块不会执行
        System.out.println(DD.age);

        //调用顺序是 1 静态代码块和静态属性初始化(有多个的话按照定义的顺序调用) 2 普通代码块和普通属性初始化，多个同理 3 构造器
        A a = new A();
    }
}
class DD{
    public static int age = 18;
    static {
        System.out.println("类加载执行，只会执行一次");
    }
    {
        System.out.println("每创建一个实例对象就执行一次");
    }
}
class Animal{
    static {
        System.out.println("Animal被调用");
    }
}
class Cat extends Animal{
    public static int sum = 10;
    static {
        System.out.println("Cat被调用");
    }
}
class BB {
    static {
        System.out.println("BB被加载");
    }
}
class AA extends BB{
    static {
        System.out.println("AA被加载");
    }
}
class A{
    public  int q  = getQ();
    {
        System.out.println(" A 普通代码块");
    }
    public int getQ(){
        System.out.println("A 普通属性初始化");
       return 200;
    }

    private static int n = getn();
    static{
        System.out.println("A 静态方法");
    }
    public static int getn() {
        System.out.println("A 静态属性初始化");
        return 100;
    }

    public A() {

        System.out.println("调用构造器");
    }
}