package homework.demo_11_25;

public class Homework01 {
    public static void main(String[] args) {
        Person doctor = new Doctor();
        Person lawyer = new Lawyer();
        w(doctor);
        w(lawyer);
    }
    public static void w(Person a){
        a.work();
    }
}

class Doctor extends Person{
    @Override
    public void work() {
        System.out.println("¾ÈËÀ·öÉË");
    }
}
class Lawyer extends Person {
    @Override
    public void work() {
        System.out.println("Î¬»¤¹«Æ½");
    }
}