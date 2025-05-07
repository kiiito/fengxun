package com.hucong.override_;

public class OverrideExercise {
    public static void main(String[] args) {
        Person person = new Person("mo",18);
        System.out.println(person.say());
        Student student = new Student("jack",18,100,2024430174);
        System.out.println(student.say());

    }
}
