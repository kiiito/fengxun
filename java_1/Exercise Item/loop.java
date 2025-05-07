import java.util.Scanner;
public class loop {
    public static  void main(String[] agrs){
        Scanner scanner = new Scanner(System.in);
//        double sum = 0;
        double allSum = 0;
        double average = 0;
//        int count = 0;
        int allCount = 0;
        for(int i = 1; i <= 3; i++){
            double sum = 0;
            int count = 0;
            for(int j = 1; j <= 5; j++){
                System.out.println("请输入第" + i +"个班第" + j + "个学生的成绩:");
                double score = scanner.nextDouble();
                if(score >= 60){
                    count++;
                }
                sum += score;
            }
            System.out.println("本班及格人数有" + count + "人。");
            allCount += count;
//            count = 0;
            average = sum / 5;
            System.out.println("班级平均分是：" + average);
//            sum = 0;
            allSum += average;
        }
        System.out.println("总班级平均分是：" + (allSum / 3));
        System.out.println("三个班总共及格人数有" + allCount + "人。");
    }
}
