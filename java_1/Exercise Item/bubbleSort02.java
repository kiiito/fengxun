public class bubbleSort02 {
    public static  void main(String[] agrs){
        int[] arr = {10,12,45,90};
        int age = 0;
        int num = 23;
            int arrNew[] = new int[arr.length + 1];
            for(int i = 0; i < arr.length; i++){
                if(num < arr[i]){
                    age = i;
                    System.out.println(i);
                    break;
                }
            }
            for(int i = 0,j = 0; i < arrNew.length; i++){
                if(i != age){
                    arrNew[i] = arr[j];
                    j++;
                }else{
                    arrNew[i] = num;
                }
            }
//           自己理解
//            for(int i = 0; i < arrNew.length; i++){
//              if(i < age){
//                  arrNew[i] = arr[i];
//              }else if(i == age){
//                  arrNew[i] = num;
//              }else{
//                  arrNew[i] = arr[i - 1];
//              }
//            }
            arr = arrNew;
            for(int i = 0; i < arr.length; i++){
                System.out.print(arr[i] + "\t");
            }

    }
}
