//import  java.util.Scanner;
public class homeWork01 {
    public static void main(String[] agrs) {

        double [] arr = {1.1,4.3,5.6};
        String[] arr2 = {"苹果","香蕉","菠萝"};
//        A01 a = new A01();
//        System.out.println("最大值是" + a.max(arr));
//        A02 b = new A02();
//        if(b.find("苹果",arr2) != -1){
//            System.out.println("查到相应的字符，下标为" + b.find("苹果",arr2));
//        }else{
//            System.out.println("未能找到字符");
//        }
        //
//        A03 a03 = new A03("活着",154);
//        a03.info();
//        a03.update();
//        a03.info();
        //
//        int [] arr02 = {13,23,67};
//        A04 a04 = new A04(arr02);
        //
        A05 a05 = new A05(5.0);
        System.out.println("圆的周长是" + a05.Perimeter());
        System.out.println("圆的面积是" + a05.area());

    }
}
class A01{
    public double max(double [] arr){
        double max01 = 0.0;
    for(int i=0; i<arr.length; i++){
        if(arr[i] > max01){
            max01 = arr[i];
        }
    }
    return max01;
    }
}
class A02{

    public int find(String fruits,String [] arr2){
//        Scanner myScanner = new Scanner(System.in);
//        System.out.println("请输出你要查找的字符串");
//        String str2 = myScanner.nextLine();
        for(int i = 0; i < arr2.length; i++){
            if (fruits.equals(arr2[i])){
                return i;
            }
        }
        return -1;
    }
}
class A03{
    String book;
    double price;
    //创造构造器
    public A03(String book,double price){
        this.book = book;
        this.price = price;
    }
    public void update(){
        if(price > 150){
            this.price = 150;
        }else if(price > 100){
            this.price = 100;
        }
    }
    //利用方法输出结果
    public void info(){
        System.out.println("这本" + book + "的价格更新后为" + price);
    }

}
class A04{
    public A04(int [] arr02){
        int [] arr03 = new int[arr02.length];
        for(int i = 0,j = 0;i < arr02.length;i++,j++){
            arr03[j] = arr02[i];
        }
        for(int i = 0;i < arr03.length;i++){
            System.out.print(arr03[i] + "\t");
        }
    }
}
class A05{
    double radius;
    public A05(double radius){
        this.radius = radius;
    }
    public double Perimeter(){
        return (2 * Math.PI * radius);
    }
    public double area(){
        return (Math.PI * radius * radius);
    }
}