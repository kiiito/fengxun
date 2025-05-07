package examination;

public class dome09 {
    public static void main(String[] args){
        Bird bird = new Bird();
        bird.sport();
        Lion lion = new Lion();
        lion.sport();
        Fish fish = new Fish();
        fish.sport();
    }
}

abstract class Animal{
    abstract void sport();
}
class Bird extends Animal{

    @Override
    void sport() {
        System.out.println("ƒÒ¿‡∑…œË");
    }
}
class Lion extends Animal{

    @Override
    void sport() {
        System.out.println(" ®◊”±º≈‹");
    }
}
class Fish extends Animal{

    @Override
    void sport() {
        System.out.println("”„∂˘”Œ”æ");
    }
}