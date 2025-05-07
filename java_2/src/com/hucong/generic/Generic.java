package com.hucong.generic;

public class Generic {
    public static void main(String[] args) {
        /**
         * 1 泛型的作用 可以在类声明时通过一个标识表示类中某个属性的类型 或者是某个方法的返回值的类型 或者是参数类型
         * 2 Person<String> stringPerson = new Person<String>("eula");
         * 可以认为将class Person<E> 中里面所有的E都替换成了String
         * 如果规定的E 的类型 在传入其他类型 编译器就会报错
         */
        Person<String> stringPerson = new Person<String>("eula");
    }
}
class Person<E>{
    E s;//E表示s的数据类型 该数据类型在定义person对象的时候指定 即在编译期间 就确定E是什么类型

    public Person(E s) {//E也可以是参数类型
        this.s = s;
    }
    public E f(){//返回使用E
        return s;
    }
}