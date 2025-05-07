package homework;

public class Demo_10_12 {
 public static void main(String[] args) {
  Demo.xx();
  Demo.hh();
  AA.ss();
  AA.ww();
  AA.ss();
  AA.ff();
  AA.ll();
  AA.pp();
  AA.qq();
 }
}
class Demo{
    public static void xx(){
    char [] arr = {'Q','W','E','R','T','Y','U','I','O','P'};
    char [] arr2 = {'A','S','D','F','G','H','J','K','L'};
    char [] arr3 = {'Z','X','C','V','B','N','M'};
     System.out.println("数组arr的个数为" + arr.length);
     System.out.println("数组arr2的个数为" + arr2.length);
     System.out.println("数组arr3的个数为" + arr3.length);
    }
    public static void hh(){
     int []arr = {2,3,5,8,12,13,16,19,20};
     int sum = 0;
     for(int i = 0;i <= 20;i++){
      for(int j = 0;j <= arr.length - 1;j++){
       if(arr[j] == i){
        sum++;
       continue;
       }
      }
     }
     int[]arr2 = new int[20-sum];
     System.out.println(arr.length);
     System.out.println(arr2.length);
    }
}
class AA {
    public static void ss() {
        int[] arr = {89, 65, 79, 69, 85, 96, 78, 74};
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        int max = 0;
        int min = 89;
        double sum = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
            sum += arr[i];
        }
        System.out.println("最大值是  " + max + " 最小值  " + min + " 平均分 " + (sum / arr.length));
        int temp = 0;
        for (int i = 0, j = arr.length - 1; i < arr.length; i++) {
            if (j + 1 == i) {
                break;
            }
            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            j--;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void ll() {
        int[] arr = {1, 2, 3, 4};
        System.out.println("aee[0]=" + arr[0]);
        System.out.println("aee[1]=" + arr[1]);
        System.out.println("aee[2]=" + arr[2]);
        System.out.println("aee[3]=" + arr[3]);
    }

    public static void pp() {
        int[] arr = {1, 2, 3, 4, 5};
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
    public static void ff(){
        int[] arr={4,1,6,3,9,8};
        int max=arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]>max){
                max=arr[i];
            }
        }
        System.out.println("最大值为：" + max);
    }
    public static void qq(){
        int sum = 0;
        for(int i = 1; i <= 99; i++){
            if(i % 2 == 0){
                continue;
            }
            sum += i;
        }
        System.out.println(sum);
    }
    public static void ww(){
        int i = 1;
        int sum = 1;
        int count = 0;
        do {
            sum *= i;
            count += sum;
            i++;
        }while (i <= 5);
        System.out.println(count);
    }
}