package com.hc.springboot.builderpattern;

/**
 * 建造者模式 23种设计模式之一
 */
public class Person {
    /**
     * 一般建造者模式的bean属性使用final修饰
     */
    private final String name;
    private final int age;

    /**
     * 提供一个私有的全参的构造方法
     * @param name
     * @param age
     */
    private Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    /**
     * 提供一个静态的builder方法 获取建造器对象
     * @return
     */
    public static PersonBuilder builder(){
        return new PersonBuilder();
    }

    /**
     * 建造者类
     */
    public static class PersonBuilder{
        private String name;
        private int age;

        /**
         * 通过链式调用设置属性 将name设置到builder对象中 返回builder对象 实现链式调用
         * @param name
         * @return
         */
        public PersonBuilder name(String name){
            this.name = name;
            return this;
        }
        public PersonBuilder age(int age){
            this.age = age;
            return this;
        }
        public Person build(){
            return new Person(name,age);
        }
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
