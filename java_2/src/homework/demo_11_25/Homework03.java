package homework.demo_11_25;

public class Homework03 {
    public static void main(String[] args) {
        animalShout(new Animal03(){
            @Override
            public void shout() {
                System.out.println("¹·½Ð");
            }
        });
    }
    public static void animalShout(Animal03 an){
        an.shout();
    }
}
interface Animal03{
    void shout();
}
