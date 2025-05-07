import java.util.Scanner;
public class breakExercise {
    public static  void main(String[] agrs){
        Scanner Scanner = new Scanner(System.in);
        String name = "";
        String  password = "";
        for(int i = 1; i <= 3; i++){
            System.out.println("请输入你的名字");
             name = Scanner.next();
            System.out.println("请输入你的密码");
             password = Scanner.next();

             //equals 将此字符串与指定对象相比较
            if("胡聪".equals(name) && "666".equals(password)){
                System.out.println("登入成功");
                break;  //满足要求，跳出for循环
            }else {
                if(!("胡聪".equals(name))){
                    System.out.println("用户名错误");
                }else if(!("666".equals(name))){
                    System.out.println("密码错误");
                }
                System.out.println("请重新输入，您还有" + (3 - i)+ "次机会。");
            }
        }
    }
}
