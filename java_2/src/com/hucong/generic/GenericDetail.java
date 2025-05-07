package com.hucong.generic;

import java.util.ArrayList;
import java.util.List;

public class GenericDetail {
    public static void main(String[] args) {
        /**
         * 1 给出的泛型指向的数据类型要求是引用类型 不能是基本数据类型
         * 2 在给泛型指定具体类型后 可以传入该类型或该类型的子类
         * 3 在开发中一般会简写 编译器会进行类型判断
         * 4 如果没有标注泛型 它默认是 Object
         */
        ArrayList<Integer> integers = new ArrayList<Integer>();
        //Type argument cannot be of primitive type
        //给出的泛型指向的数据类型要求是引用类型 不能是基本数据类型
        //List<int> objects = new List<int>();

        //因为E 指定了 A 类型 构造器传入了 new A()
        // 在给泛型指定具体类型后 可以传入该类型或该类型的子类
        C c1 = new C<A>(new A());
        // 因为B 继承了 A 所以在传入时并不会 报错
        C c2 = new C<A>(new B());

        //在开发中一般会简写 编译器会进行类型判断 即后面尖括号的类型省略
        ArrayList<A> as = new ArrayList<>();

        //如果没有标注泛型 它默认是 Object
        ArrayList arrayList = new ArrayList();//等价于ArrayList<Object> arrayList = new ArrayList();
    }
}
class A{}
class B extends A{}
class C<E>{
    E e;//E表示e的数据类型 该数据类型在定义C对象的时候指定 即在编译期间 就确定E是什么类型

    public C(E e) {
        this.e = e;
    }
}