package Math_;

import java.util.Scanner;

public class ReverseOrderNumber01 {
    public static void main(String[] args) {
        int num;
        Scanner scanner = new Scanner(System.in);
        num = scanner.nextInt();
        ReverseOrderNumber01 reverseOrderNumber01 = new ReverseOrderNumber01();
        int digit = reverseOrderNumber01.count(num);
       // int [] array = new int[digit];
        int [] array2 = reverseOrderNumber01.jj(digit,num);
       //System.out.println(reverseOrderNumber01.xx(array2));
       //reverseOrderNumber01.hh(array2);
        if(reverseOrderNumber01.xx(array2)){
            reverseOrderNumber01.hh(array2);
        }else {
            System.out.println("错误，不可有重复数字");
        }
    }
    public int count(int num) {
        return String.valueOf(num).length();
    }
    public int[] jj(int digit,int num){
        int [] array = new int[digit];
        for (int i = array.length ; i > 0 ;i--){
            if(num > 0){
                array[i - 1] = num % 10;
                num /= 10;
            }
        }
        return array;
    }
    public boolean xx(int [] array) {
        boolean su = true;
        for (int i = 0; i < array.length;i++){
            // System.out.println("i=" + array[i] );
            for (int j = array.length - 1; j > i;j--){
                if(array[i] == array[j]){
                    su = false;
                }
            }
        }
        return su;
    }

    public void hh(int [] array2){
        int sum = 0;
        for (int i = 0; i < array2.length - 1; i++){
            for(int j = array2.length - 1; j > i ;j--){
                if(array2[i] > array2[j]){
                    sum++;
                }
            }
        }
        System.out.println("逆序数"+ sum);
    }
}
