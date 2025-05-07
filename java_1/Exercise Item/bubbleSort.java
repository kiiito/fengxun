public class bubbleSort {
    public static  void main(String[] agrs){
        int[] arr = {22,79,50,89,10};
        int temp = 0;
        int len = arr.length - 1;
        for(int i = 0; i < len; i++){
            for(int j = 0; j < len - i; j++){
                if(arr[j] > arr[j + 1]){
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        for(int j = 0; j < arr.length; j++){
            System.out.print(arr[j] + "\t");
        }
    }
}
