package homework.demo_11_18;

public class Homework05 {
    public static void main(String[] args) {
        Fly fly = new Fly(6);
        fly.say();
        fly.fly();
        fly.reproduce();
    }
}
interface Flyable{
    void fly();
}
abstract class Insect{
    int num;

    public Insect(int num) {
        this.num = num;
    }
    abstract void reproduce();
}
class Fly extends Insect implements Flyable{
    public Fly(int num) {
        super(num);
    }

    public void say(){
        System.out.println("苍蝇有 " + num +" 条腿");
    }

    @Override
    public void fly() {
        System.out.println("苍蝇可以在空中飞行");
    }

    @Override
    void reproduce() {
        System.out.println("苍蝇的繁殖方式是产卵");
    }
}