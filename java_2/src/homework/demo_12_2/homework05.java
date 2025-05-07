package homework.demo_12_2;

public class homework05 {
    public static void main(String[] args) {
        try {
            Object obj = "hello";
            Integer i = (Integer) obj;
        } catch (Exception e) {
            System.out.println("捕获的异常信息为" + e.getMessage());
        }
    }
}
