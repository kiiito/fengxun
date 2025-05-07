import java.util.Scanner;
public class loop01 {
    public static  void main(String[] agrs){
        Scanner myScanner = new Scanner(System.in);
        System.out.println("ÇëÊäÈë²ãÊı¡£");
        int total = myScanner.nextInt();
        for(int i = 1; i <= total; i++){
            for(int j = 1; j <= total - i; j++){
                System.out.print(" ");
            }
            for(int k = 1; k <= 2 * i - 1; k++){
                if(k == 1 || k == 2 * i - 1 || i == total){
                    System.out.print("*");
                }else {
                    System.out.print(" ");
                }
            }
            System.out.println("");
        }
    }
}
