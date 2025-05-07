

public class Comment02 {
    public static void main(String[] args)
    {
        int sum = 0;
        int count = 0;
        for(int i = 1; i <= 100; i++){
            sum = sum + i;
            count += sum;
        }
        System.out.println(count);

    }
}
