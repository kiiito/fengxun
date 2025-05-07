package homework;

public class Demo_9_23_4 {
    public static void main(String[] args) {
        int i1 = 98, i2 = 98, j1 = -99, j2 = -99;
        int x = 3, y = 5;
        int a1 = 3, a2 = 3, b1 = 5, b2 = 5;
        long l = 23678910L;
        char chr1 = '国';
        double d = -8.07E-4;
        System.out.println("字符型变量chr1=" + chr1);
        System.out.println("长整型变量l=" + l);
        System.out.println("双精度变量d=" + d);
        boolean z1 = x < y || i1++ == -j1--;
        boolean z2 = x < y || i2++ == j2--;
        boolean c1 = x > y && a1++ == b1--;
        boolean c2 = x > y & a2++ == b2--;
        System.out.print("整型变量i1=" + i1);
        System.out.print("整型变量j1=" + j1);
        System.out.println("逻辑变量z1=" + z1);
        System.out.print("整型变量12=" + i2);
        System.out.print("整型变量j2=" + j2);
        System.out.println("逻辑变量z2=" + z2);
        System.out.print("整型变量al=" + a1);
        System.out.print("整型变量b1=" + b1);
        System.out.println("逻辑变量cl=" + c1);
        System.out.print("整型变量a2=" + a2);
        System.out.print("整型变量b2=" + b2);
        System.out.println("逻辑变量c2=" + c2);
    }
}
