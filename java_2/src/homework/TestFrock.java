package homework;

public class TestFrock {
    public static void main(String[] args) {
        System.out.println(Frock.getCurrentNum());
        System.out.println(Frock.getCurrentNum());
        Frock frock = new Frock();
        System.out.println(frock.getCurrentNum());
        Frock frock1 = new Frock();
        System.out.println(frock1.getCurrentNum());
        Frock frock2 = new Frock();
        System.out.println(frock2.getCurrentNum());

    }
}
class Frock {
    private static int currentNum = 10000;
    private static int serialNumber;

    public static int getSerialNumber() {
        return serialNumber;
    }

    public static int getCurrentNum() {
        currentNum += 100;
        return currentNum;
    }

    public Frock() {
        serialNumber = getSerialNumber();
    }
}

