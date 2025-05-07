package homework.demo_11_25;

public class Homework02 {
    public static void main(String[] args) {
        Animal [] n = new Animal[5];
        n[0] = new Animal("ganYu",18);
        n[1] = new Animal("eula",19);
        n[2] = new Animal("lisa",22);
        n[3] = new Animal("keQin",21);
        n[4] = new Animal("hu",18);
//        for (Animal a : n) {
//            System.out.println(a);
//        }
        for (int i = 0; i < n.length; i++) {
            System.out.println(n[i].getName() + " ");
        }
    }
}
