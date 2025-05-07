
//方法重构的用法
public class overload {
    public static void main(String[] agrs) {
        Method method = new Method();
        System.out.println("最大值为" + method.max(1,3));
        System.out.println("最大值为" + method.max(1.2,2.2));
        System.out.println("最大值为" + method.max(1.1,3.3,3.2));

    }
}

class Method{
    public int max(int a,int b){
        return a > b ? a : b;
    }
    public double max(double a,double b){
        return a > b ? a : b;
    }
    public double max(double a,double b,double c){
       double max1 = a > b ? a : b;
       return max1 = max1 > c ? max1 : c;
    }
}