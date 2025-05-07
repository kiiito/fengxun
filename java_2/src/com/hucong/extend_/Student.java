package com.hucong.extend_;
//父类  pupil 和 Graduate 为子类
public class Student {
    public String name;
    public int age;
    private double score;

    public void setScore(double score) {
        this.score = score;
    }
    public void info() {
        System.out.println("学生名字 " + name + "年龄 " + age + "成绩 " + score);
    }
}
