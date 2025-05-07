package examination;

public class dome04 {
    public static void main(String[] args){
        for (int i = 10; i <= 100; i++) {
            boolean a = true;
            for (int j = 2; j <= Math.sqrt(i);j++){
                if(i % j == 0){
                    a = false;
                    break;
                }
            }
            if(a){
                System.out.print(i + " ");
            }
        }
    }
}
