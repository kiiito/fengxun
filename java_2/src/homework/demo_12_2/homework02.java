package homework.demo_12_2;

public class homework02 {
    public static void main(String[] args)  {
        try {
            int result = divide(4,2);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static int divide(int x, int y)throws Exception {
        int result = x / y;
        return result;
    }
}
