package com.hucong.reflection;

public class Class01 {
    public static void main(String[] args) throws ClassNotFoundException {
        // 1 class 也是类 因此也继承object类
        // 2 class类对象不是new出来的 而是系统创建的
        /* 传统new对象
         public Class<?> loadClass(String name) throws ClassNotFoundException {
                return loadClass(name, false);
            }
         */
       // Cat cat = new Cat();
        /*反射方式 必须要注释掉Cat cat = new Cat(); 因为类只会加载一次
                public Class<?> loadClass(String name) throws ClassNotFoundException {
                        return loadClass(name, false);
                    }
         */
        Class cls1 = Class.forName("com.hucong.reflection.Cat");

        // 3 对于某个类的class类对象 在内存中只存在一份 因此类只加载一次
        Class cls2 = Class.forName("com.hucong.reflection.Cat");
        System.out.println(cls1.hashCode() + " " + cls2.hashCode() + (cls1.hashCode() == cls2.hashCode()));

        // 4 每个类的实例都会记得自己是由哪个class实例所生成
        // 5 通过class对象可以完整地得到一个类的完整结构 通过调用一系列的API
        // 6 class对象是存放在堆中的
        // 7 类的字节码二进制是存放在方法区的 有的地方称为类的元数据(包括 方法代码 变量名 方法名访问权限等等)
    }
}
