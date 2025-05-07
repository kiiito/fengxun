package homework.demo_12_2;

public class homework04 {
    public static void main(String[] args) {
        int arr[] = new int[4];
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println(arr[i]);
            }
        } catch (Exception e) {
            System.out.println("捕获的异常信息为" + e.getMessage());
        }
    }
}
