package com.hucong.innerclass;

/**
 * 演示局部内部类
 */
public class LocalInnerClass {
    public static void main(String[] args) {
        Outer outer = new Outer();
        outer.m1();
        System.out.println("Outer 的 hashcode = " + outer );
    }
}
class Outer{
    private int n1 = 100;
    public void m2(){
        System.out.println("外部类m2方法调用");
    }
    // 1 局部类是定义在外部类的的局部类位置 比如方法中 并且有类名
    public void m1(){
        // 3 不能添加访问修饰符 但是可以使用final修饰  不能使用public 这些 因为这只是一个局部变量
        // 4 作用域 仅仅在定义它的方法或代码块中
        final class Inner01{
            private int n1 = 200;
            public void xx(){
                // 2 可以直接访问外部类的所有成员 包含私有的
                // 5 局部 访问 外部 直接访问
                // 7 如果外部类和局部类的成员重名时 默认遵循就近原则 如果想要访问外部类的成员 则可以使用 (外部类名.this.成员) 去访问
                //   Outer.this 本质就是外部类的对象 即哪个对象调用了m1 Outer.this 就是哪个对象
                System.out.println("内部类n1 = " + n1 + "  外部类的n1= " +  Outer.this.n1);
                System.out.println("Outer.this 的 hashcode = " + Outer.this);
                m2();
            }
        }
        // 6 外部类 访问 局部类  创建对象 再访问(必须在作用域内)
        // 7 外部其他类 不能访问 局部内部类
        Inner01 inner01 = new Inner01();
        inner01.xx();
    }
}