public class homeWork02 {
    public static void main(String[] agrs) {
        guess g = new guess();
        System.out.println(g.guessnumber());
    }
}

class guess {
    double num1;
    double num2;

    public double guessnumber() {
        num1 = Math.floor(Math.random() * 3);
        num2 = Math.floor(Math.random() * 3);

        return;
    }
}