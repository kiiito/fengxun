import java.util.Scanner;
public class bubbleSort01 {
    public static  void main(String[] agrs){
        int[] arr = {10,12,45,90};
        Scanner myScanner = new Scanner(System.in);
        int temp = 0;
        do{
            System.out.println("请输入您要添加的数。");
            int num = myScanner.nextInt();
            int arrNew[] = new int[arr.length + 1];
            for(int i = 0; i < arrNew.length - 1; i++){
                arrNew[i] = arr[i];
                arrNew[arr.length] = num;
            }
            arr = arrNew;
            for(int i = 0; i < arr.length - 1; i++){
                for(int j = 0; j < arr.length - 1 - i; j++){
                    if(arr[j] > arr[j + 1]){
                        temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }
            }
            for(int i = 0; i < arr.length; i++){
                System.out.print(arrNew[i] + "\t");
            }
            System.out.println("\n" + "您是否需要继续添加y/n");
            char key = myScanner.next().charAt(0);
            if(key == 'n'){
                break;
            }
        }while (true);

    }
}