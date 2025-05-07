package com.hucong.extend_;

public class Extend01 {
    public static void main(String[] args) {
        Pupil pupil = new Pupil();
        pupil.name = "jack";
        pupil.age = 10;
        pupil.testing();
        pupil.setScore(60);
        pupil.info();
        System.out.println("=========");
        Graduate graduate = new Graduate();
        graduate.name = "hu";
        graduate.age = 23;
        graduate.testing();
        graduate.setScore(80);
        graduate.info();

    }


}
