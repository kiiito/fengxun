package com.hucong.generic;

import java.util.*;

public class GenericExercise {
    public static void main(String[] args) {
        //限定了只能是student的类
        HashSet<Student> set = new HashSet<Student>();
        set.add(new Student("eula",18));
        System.out.println("=========================");
        //用hashmap
        HashMap<String, Student> map = new HashMap<String,Student>();
        map.put("ganYu",new Student("ganYu",20));
        map.put("eula",new Student("eula",22));
        map.put("keQin",new Student("keQin",24));
        Set<Map.Entry<String, Student>> entries = map.entrySet();
        for (Map.Entry<String, Student> entry : entries) {
           System.out.println(entry);
            //System.out.println(entry.getKey() + " " + entry.getValue());
        }
        //迭代器
        Iterator<Map.Entry<String, Student>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Student> next = iterator.next();
            System.out.println(next.getKey() + " " + next.getValue());
        }

    }


}

class Student{
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
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
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
