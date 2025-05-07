package com.hucong.interface_;

public class InterfaceExercise {

}
interface L{
    //等价与 public static final int x = 0;
    int x = 0;
}
class B{
    int x = 1;
}
class Test02 extends B implements L{
    public void px(){
        //x 的值模糊不清
        //System.out.println(x);
        //应该明确指明
        System.out.println("x=" + L.x + " x = " + super.x);
    }

    public static void main(String[] args) {
        new Test02().px();
    }
}