package examination;

public class dome07 {
    public static void main(String[] args) {
//        int count = 0;
        int max = 0;
        double sum = 0;
        int[]arr = {10,24,13,18,30,48,4};
        int min = arr[0];
        for (int i = 0; i < arr.length; i++) {
//            count += i;
            if(arr[i] < min){
                min = arr[i];
            }
            if(arr[i] > max) {
                max = arr[i];
            }
            sum += arr[i];
        }
        System.out.println("总和为 = " + sum + " 平均分为 = " + (sum/arr.length) + " 最大值为 = " + max + " 最小值 = " + min);
    }
}
