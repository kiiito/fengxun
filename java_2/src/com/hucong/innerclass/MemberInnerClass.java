package com.hucong.innerclass;

/**
 * 成员内部类
 */
public class MemberInnerClass {
    public static void main(String[] args) {
        Outer06 outer06 = new Outer06();
        outer06.teat01();

        // 7 外部其他类 访问 成员内部类
        // 第一种方式
        // outer06.new Inner06(); 相当于把 new Inner06() 当做是 outer06成员
        Outer06.Inner06 inner06 = outer06.new Inner06();
        inner06.say();
        // 第二种方式
        // 在内部类中 编写一个方法 可以返回 Inner06对象
        Outer06.Inner06 inner06Insert = outer06.getInner06Instance();
        inner06Insert.say();
    }
}
class Outer06{
    private int n = 100;
    public String name = "jack";

    // 1 成员内部类是定义在外部类的成员位置 并且没有static修饰
    // 3 可以添加任意访问修饰符 因为它的地位就是一个成员
    // 4 作用域 在外部类Outer06 当中
    public class Inner06{
        private int n = 200;
        public void say(){
         // 2 可以直接访问外部类的所有成员 包含私有
            // 5 成员内部类 访问 外部类 直接访问
            // 8 如果成员内部类和外部类的成员重名 会遵守就近原则 可以通过 外部类名.this.成员 去访问
            System.out.println("内部类n = " + n + " name " + name + "外部类n = " + Outer06.this.n);
        }
    }
    // 6 外部类 访问 成员内部类 创建对象 再访问
    public void teat01(){
        Inner06 inner06 = new Inner06();
        inner06.say();
    }
    // 方法 返回一个 Inner06 的实例
    public Inner06 getInner06Instance(){
        return new Inner06();
    }
}