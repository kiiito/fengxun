package examination;

public class dome06 {
    public static void main(String[] args) {
        System.out.println(add(1,2));
        System.out.println(add(1,2,3));
        System.out.println(add(1.0,3.5,8.7));
    }
    public static int add(int x,int y){
        return x + y;
    }
    public static int add(int x,int y,int z){
        return x + y + z;
    }
    public static double add(double x,double y,double z){
        return x + y + z;
    }
}
