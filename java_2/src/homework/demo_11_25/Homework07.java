package homework.demo_11_25;

public class Homework07 {
    public static void main(String[] args) {
        q(new Animal07(){
            @Override
            void eat() {
                System.out.println("√®≥‘”„£¨π∑≥‘»‚");
            }
        });
    }
    public static void q(Animal07 a){
        a.eat();
    }
}
class Animal07{
    void eat(){}
}