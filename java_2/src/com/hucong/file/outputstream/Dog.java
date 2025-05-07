package com.hucong.file.outputstream;

import java.io.Serializable;

/**
 * 1 要求读写顺序一致
 * 2 要求序列化或反序列化对象 需要实现Serializable
 * 3 序列化的类中建议添加serialVersionUID 为了提高版本的兼容性
 *  (当类中变量或方法发生变化时 让系统不会判定是创建了一个新的类)
 * 4 序列化对象时 默认将里面所有属性都进行序列化 但除了static或transient修饰的成员
 * 5 序列化对象时 要求里面属性类型也需要实现序列化接口
 * 6 序列化具备可继承性 也就是如果某类实现了序列化 则它所有子类也默认实现序列化
 */
public class Dog implements Serializable {
        private String name;
        private int age;
        //序列化的类中建议添加serialVersionUID 为了提高版本的兼容性
        private static final long serialVersionUID = 1L;
        //序列化对象时 默认将里面所有属性都进行序列化 但除了static或transient修饰的成员
        private static String nation;
        private transient String color;
        /*
        public class Master implements Serializable {}
        序列化对象时 要求里面属性类型也需要实现序列化接口
          */
        private Master master = new Master();

    public Dog(String name, int age, String color, String nation) {
        this.name = name;
        this.age = age;
        this.color = color;
        this.nation = nation;
    }

    public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                ", master=" + master +
                '}' + nation;
    }
}

