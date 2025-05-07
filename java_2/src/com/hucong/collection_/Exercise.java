package com.hucong.collection_;

import java.util.HashSet;
import java.util.Objects;

public class Exercise {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        HashSet hashSet = new HashSet();
        Person p1 = new Person(1001, "AA");
        Person p2 = new Person(1002, "BB");
        hashSet.add(p1);//ok
        hashSet.add(p2);//ok
        p1.name = "CC";//改变了p1存储在hashset的哈希值
        hashSet.remove(p1);//根据哈希值查找p1 但p1的哈希值已经发生变化 所以会指向一个空的空间 所以不会删除p1
        System.out.println(hashSet);//返回两个对象
        hashSet.add(new Person(1001,"CC"));//会将此元素添加到刚才指向的那个空的空间当中
        System.out.println(hashSet);//返回3个对象
        hashSet.add(new Person(1001,"AA"));//会找到原先改变value值的那个p1 以链表的形式添加
        System.out.println(hashSet);//返回4个对象
    }
}
class Person{
    public String name;
    public int id;

    public Person(int id, String name) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}