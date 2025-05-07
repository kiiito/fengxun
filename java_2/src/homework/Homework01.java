package homework;

public class Homework01 {
    public static void main(String[] args) {
        Cellphone cellphone = new Cellphone();
        cellphone.testWork(new IL() {
            @Override
            public double work(double x, double y) {
                return x + y;
            }
        },10,8);
        AE ae = new AE();
        ae.cc();
    }
}
interface IL{
    double work(double x, double y);
}
class Cellphone {
    public static void  testWork(IL il ,double x, double y) {
        double result = il.work(x, y);
        System.out.println("计算后的结果" + result);
    }
}
class AE{
    private String name = "tom";
    public  void cc(){
        class BB{
            private String name = "Jack";
            public void show(){
                System.out.println("name = " + name);
                System.out.println("AE name = " + AE.this.name);
            }
        }
        BB bb = new BB();
        bb.show();
    }
}