package homework.demo_11_25;

public class Homework06 {
    public static void main(String[] args) {
        w(new MoveAble() {
            @Override
            public void run() {
                System.out.println("–°π∑œÚ«∞≈‹");
            }
        });
    }
    public static void w(MoveAble a){
        a.run();
    }
}
interface MoveAble{
    void run();
}
