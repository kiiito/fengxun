package homework;

public class Demo_10_21_1 {
    public static void main(String[] args) {
        Demo_10_21.gys(3,6);
        Demo_10_21.mj(2,5);
        Demo_10_21.mj(3,5);
        System.out.println(Demo_10_21.dg(5));
        int []a = {1,3};
        Demo_10_21.jh(a);
        Demo_10_21.ff();
        Demo_10_21.sxh(153);
        Demo_10_21.pd(9);
        Demo_10_21.dd(6,8);
    }
}
class Demo_10_21{
    public static void gys(int u,int y) {
        for (int i = 2; i <= u * y ; i++) {
            if(i % u == 0 && i % y == 0){
                System.out.printf("最大公约数是" + i);
                break;
            }
        }
    }
    public static void mj(int h,int k){
        System.out.println("面积为" + h * k);
    }
    public static void mj(double h,double k){
        System.out.println("面积为" + h * k);
    }

   //递归必须有一个限制条件 比如说这里n = 1 时返回 1 而我们最后的递归必须朝着这个限制条件靠近
    public static int dg(int n){
        if(n == 1){
            return 1;
        }else {
            return n * dg(n - 1);
        }
    }
    public static void sxh(int i) {
        int a = i / 100 % 10;
        int b = i / 10 % 10;
        int c = i % 10;
        if((a*a*a + b*b*b + c*c*c == i) && i % 2 == 0){
            System.out.println(i + "是水仙花数和偶数");
        }
    }
    public static void jh(int [] a){
        System.out.println("交换前:s[0]="+a[0]+",s[1]="+a[1]);
        int temp;
        temp = a[0];
        a[0] = a[1];
        a[1] = temp;
        System.out.println("交换后:s[0]="+a[0]+",s[1]="+a[1]);
    }
    public static boolean zs(int num){
        boolean f = true;
        for (int i = 2; i < num; i++) {
            if (num % i == 0){
                f = false;
            }
        }
        return f;
    }
    public static void ff(){
        for (int i = 20; i <= 100 ; i++) {
            if(zs(i)){
                System.out.println(i + "是素数");
            }
        }
    }
    public static void pd(int num){
        if(num%2==0){
            System.out.println(num+"是偶数");
        }
        else {
            System.out.println(num + "是奇数");
        }
        for (int i = 0; i < 4 ; i++) {
            for(int j = 0; j < 4;j++){
                System.out.print("*");
            }
            System.out.println();
        }
    }
    public static void dd(int x,int y){
        System.out.println("x="+x+",y="+y);
        int temp = x;
        x = y;
        y = temp;
        System.out.println("x="+x+",y="+y);
    }
}
