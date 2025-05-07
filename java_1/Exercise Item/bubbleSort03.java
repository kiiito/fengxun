public class bubbleSort03 {
    public static  void main(String[] agrs){
//        int average = 0;
        int []arry = new int[10];
        int temp = 0;
        int sum = 0;
        for(int i = 0; i < arry.length;i++){
            //随机生成1到100的随机数
            int num = (int)(Math.random() * 100) + 1;
            arry[i] = num;
            sum += num;
        }
        for(int i = 0; i < arry.length - 1; i++){
            for(int j = 0;j < arry.length - i - 1;j++){
                if(arry[j] < arry[j + 1]){
                    temp = arry[j];
                    arry[j] = arry[j + 1];
                    arry[j + 1] = temp;
                }
            }
        }
        for(int i = 0;i < arry.length;i++){
            System.out.print( arry[i] + "\t");
        }
        System.out.println("\n" + "平均数是" + (sum / arry.length));
    }
}
