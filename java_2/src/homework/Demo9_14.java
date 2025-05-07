package homework;

import static java.lang.Math.PI;

public class Demo9_14 {
    public static void main(String[] args) {
        int diameter = 10;
        int height = 3;
        double cubicMeter;
        int count = 750;
        double a = 3.14;
        cubicMeter = a * (diameter / 2) *(diameter / 2) * height;
        System.out.println("该粮仓放入体积为:" + cubicMeter );
        System.out.println("该粮仓一共可以储存" + (cubicMeter * count) + "千克");
    }


}
