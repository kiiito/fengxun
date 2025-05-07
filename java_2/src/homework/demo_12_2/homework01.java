package homework.demo_12_2;

public class homework01 {
    public static void main(String[] args) {
        try {
            int result = divide(4,0);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("捕获的异常信息为" + e.getMessage());
        } finally {
            System.out.println("进入了final代码块");
        }
        System.out.println("程序继续向下运行");
    }
    public static int divide(int x, int y){
        int result = x / y;
        return result;
    }
}
