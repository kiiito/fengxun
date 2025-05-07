package com.hucong.innerclass;

/**
 * 静态内部类演示
 */
public class StaticInnerClass {
    public static void main(String[] args) {
        Outer07 outer07 = new Outer07();
        outer07.m1();

        //外部其他类 访问 静态内部类
        // 方法1
        // 因为是静态内部类 是可以通过类名直接访问 前提是有访问权限
        Outer07.Inner07 inner07 = new Outer07.Inner07();
        inner07.say();
        // 方法2
        // 编写一个方法 可以返回静态内部类的对象实例
        Outer07.Inner07 inner071 = outer07.getInner07();
        inner071.say();

        //也可以用静态方法来返回
        Outer07.Inner07 inner072 = outer07.getInner071();
        inner072.say();
    }
}
class Outer07{
    private static int n = 100;
    private  String name = "jack";
    // 1 成员内部类是定义在外部类的成员位置
    // 3 可以添加任意访问修饰符 因为它的地位就是一个成员
    // 4 作用域 在外部类Outer07 当中
    public static class Inner07{
        private  int n = 200;
            public void say(){
                // 2 可以直接访问外部类的所有静态成员 包含私有 但不能直接访问非静态成员
                // 5 静态内部类 访问 外部类 直接访问
                // 8 如果静态内部类和外部类的成员重名 会遵守就近原则 可以通过 外部类名.成员 去访问
                System.out.println(" 内部类n = " + n + " 外部类 n = " + Outer07.n);
            }
    }
    // 6 外部类 访问 静态内部类 创建对象 再访问
    public void m1(){
        Inner07 inner07 = new Inner07();
        inner07.say();
    }

    public Inner07 getInner07() {
        return new Inner07();
    }
    public static Inner07 getInner071(){
        return new Inner07();
    }
}