package homework.demo_11_25;

public class Homework04 {
    public static void main(String[] args) {
        Person04 person04 = new Person04();
        Person04.Heart heart = person04.new Heart();
        heart.beat();
    }
}
class Person04{
    class Heart{
        public  void beat(){
            System.out.println("ĞÄÔàÔÚÌø¶¯");
        }
    }
}