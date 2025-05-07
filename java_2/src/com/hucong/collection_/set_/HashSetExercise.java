package com.hucong.collection_.set_;

import java.util.HashSet;
import java.util.Objects;

public class HashSetExercise {
    @SuppressWarnings({"all"})
    public static void main(String[] args) {
        HashSet hashSet = new HashSet();
        hashSet.add(new Employee("ganYu",18));
        hashSet.add(new Employee("eula",23));
        hashSet.add(new Employee("ganYu",18));
        System.out.println(hashSet);
    }
}
class Employee{
    private String name;
    private int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
    // 如果name和age的值相同 在使用equals则返回true
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return age == employee.age && Objects.equals(name, employee.name);
    }
    // 如果name和age的值相同 在使用hashCode时返回相同的值
    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
