import java.util.Scanner;
public class ifelse {
    public static  void main(String[] agrs){

        Scanner myScanner = new Scanner(System.in);
        System.out.println("请输入你的余额:");
        int balance = myScanner.nextInt();
        if(balance >= 1 && balance <= 10000){
            if(balance >=9000 && balance <= 10000){
                System.out.println("你很富裕。");
            }else if(balance >=5000 && balance <= 9000){
                System.out.println("还可以。");
            }else if(balance <5000){
                System.out.println("请再努力。");
            }
        }else{
            System.out.println("你输入的数值非法。");
        }
        System.out.println("请输入成绩：");
        double achievement = myScanner.nextDouble();
        if(achievement > 8.0){
            System.out.println("请输入你的性别：");
            char sex = myScanner.next().charAt(0);
            if(sex == '男' || sex == '女'){
                if(sex == '男'){
                    System.out.println("请进入男子组。");
                }else if(sex == '女'){
                    System.out.println("请进入女子组。");
                }
            }else{
                System.out.println("请输入正确性别。");
            }
        }else{
            System.out.println("你的成绩不能进入决赛，请再努力。");
        }
        double money = 60;
        System.out.println("请输入月份：");
        int month = myScanner.nextInt();
        if(month >= 1 && month <= 12){
            System.out.println("请输入年龄：");
            int age = myScanner.nextInt();
            if(month >= 4 && month <= 10){
                if(age >= 18 && age <= 60){
                    System.out.println("您需要支付成人票"+ money +"元");
                }else if(age < 18 && age >= 1){
                    System.out.println("您需要支付儿童票" + (money/2) +"元");
                }else if(age > 60 && age <= 100){
                    System.out.println("您需要支付老人票" + (money/3) +"元");
                }else{
                    System.out.println("您输入的年龄不合法。");
                }
            }else {
                if(age >= 1 && age <= 100){
                    if(age >= 18 && age <=60){
                        System.out.println("您需要支付成人票" + money +"元");
                    }else {
                        System.out.println("您需要支付" + (money/3) +"元");
                    }
                }else {
                    System.out.println("您输入的年龄不合法。");
                }
            }
        }else{
            System.out.println("请输入正确月份。");
        }
    }
}
