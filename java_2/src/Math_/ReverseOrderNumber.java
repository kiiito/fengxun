package Math_;

import java.util.Scanner;

public class ReverseOrderNumber {


    public static void main(String[] args) {
        int num;
        int sum = 0;
        Scanner scanner = new Scanner(System.in);
        num = scanner.nextInt();
        ReverseOrderNumber reverseOrderNumber = new ReverseOrderNumber();
        // System.out.println(reverseOrderNumber.count(num));
         //System.out.println(num);
        int digit = reverseOrderNumber.count(num);
        int [] array = new int[digit];
        for (int i = array.length ; i > 0 ;i--){
            if(num > 0){
                array[i - 1] = num % 10;
                num /= 10;
            }
        }
//        for(int i =0;i < array.length ;i++){
//            System.out.println(array[i]);
//        }
//        for(int i = 0;i < array.length - 1;i++){
//            for(int j = 1; j < array.length - i -1;j++){
//                if(array[i] == array[j]){
//                    System.out.println("错误，不可有重复数字");
//                }
//            }
//        }

//        for(int i = 0; i < array.length - 1; i++){
//            for (int j = array.length - 1;j > i; i++ ){
////                if(array[i] == array[j]){
////               System.out.println("错误，不可有重复数字");
////                }
//                System.out.println("i=" + i + "  j=" + j);
//                System.out.println("ai=" + array[i] + "  aj=" + array[j]);
//            }
//        }
        for (int i = 0; i < array.length;i++){
           // System.out.println("i=" + array[i] );
            for (int j = array.length - 1; j > i;j--){
                //System.out.println("j=" + array[j] );
               // System.out.println(  "i=" +array[i] + "j=" + array[j]  );
                if(array[i] == array[j]){
                        System.out.println("错误，不可有重复数字");
                  }
            }
        }
        for (int i = 0; i < array.length - 1; i++){

//            System.out.println("i=" + array[i]);
//             System.out.println("======");
            for(int j = array.length - 1; j > i ;j--){
                if(array[i] > array[j]){
                   // System.out.println(array[i] +"   "+ array[j]);
                    sum++;
//                    System.out.println("sum" + sum);
//                System.out.println("j的数" + j);
//                System.out.println("j = " + array[j]);


                }

            }
            //System.out.println(array[i]);
        }
        System.out.println("逆序数"+ sum);

    }

    public int count(int num) {
        return String.valueOf(num).length();
    }

   public boolean xx(int [] array){
        boolean su = false;
       for (int i = 0; i < array.length;i++){
           // System.out.println("i=" + array[i] );
           for (int j = array.length - 1; j > i;j--){
               //System.out.println("j=" + array[j] );
               // System.out.println(  "i=" +array[i] + "j=" + array[j]  );
               if(array[i] == array[j]){
                   su = false;
                   System.out.println("错误，不可有重复数字");
               }else {
                  su = true;
               }
           }
       }
       return su;
   }

}

