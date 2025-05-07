package homework.demo_11_25;

public class Homework05 {
    public static void main(String[] args) {
        Person05 person05 = new Person05();
        person05.eat(new Fruits() {
            @Override
            void getName() {
                System.out.println("³ÔÆ»¹û");
            }
        });
        person05.eat(new Fruits() {
            @Override
            void getName() {
                System.out.println("³ÔÏã½¶");
            }
        });
    }
}

abstract class Fruits {
   abstract void getName();
}
class Person05{
    public void eat(Fruits f){
        f.getName();
    }
}