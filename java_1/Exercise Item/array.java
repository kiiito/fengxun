import java.util.Scanner;
public class array {
    public static  void main(String[] agrs){
        int arr[] = {1,2,3,4,5};
        Scanner myScanner = new Scanner(System.in);
        do{
            int arrNew[] = new int[arr.length - 1];
            System.out.println("是否进行删减 y/n");
            char key = myScanner.next().charAt(0);
            if(key == 'y'){
                for(int i = 0; i < arr.length -1; i++){
                    arrNew[i] = arr[i];
                }
            }
            //必须将新数组的地址赋给原数组，不然在判断是否删减时原数组的长度不会发生变化。
            arr = arrNew;
            for(int i = 0; i < arr.length; i++){
                System.out.println(arr[i]);
            }
            if(arrNew.length <= 1){
                System.out.println("这是最后一个元素，不能在进行删减。");
                break;
            }
        }while(true);
    }
}
