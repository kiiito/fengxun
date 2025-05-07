package use;

import com.a.Dog;

public class test {
    public static void main(String[] args) {
        Dog dog = new Dog();
        System.out.println(dog);
        //调用不同包下相同类名的类在前面加包名
        com.b.Dog dog1 = new com.b.Dog();
        System.out.println(dog1);

        String[] names = {"Bob", "Alice", "Grace"};
        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i]);
        }
        String s = String.join(", ", names);
        System.out.println(s);
    }
}
