package com.hucong.override_;

public class Student extends Person{
    private int id;
    private int score;
    public Student(String name, int age,int score,int id) {
        super(name, age);
        this.score = score;
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String say() {
        return super.say() + " ID " + id + " ³É¼¨ " + score;
    }
}
