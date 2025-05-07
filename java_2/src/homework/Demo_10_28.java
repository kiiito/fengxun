package homework;

public class Demo_10_28 {
    public static void main(String[] args) {
        System.out.println("学号" + "\t\t" + "姓名" + "\t\t" + "语文" + "\t\t" + "数学" + "\t\t" + "英语" + "\t\t" +"平均分");
        System.out.println("--------------------------------------------------------------");
        Demo_10_28_1 demo_10_28_1 = new Demo_10_28_1("张三",1,91.5,98.0,89.0);
        demo_10_28_1.say();
        Demo_10_28_1 demo_10_28_2 = new Demo_10_28_1("李四",2,96.0,98.5,93.0);
        demo_10_28_2.say();
        Demo_10_28_1 demo_10_28_3 = new Demo_10_28_1("王五",3,97.0,100.0,98.5);
        demo_10_28_3.say();
        Demo_10_28_1 demo_10_28_4 = new Demo_10_28_1("钱六",4,77.0,83.0,81.0);
        demo_10_28_4.say();

    }
}
class Demo_10_28_1{
    private String name;
    private int num;
    private double score01;
    private double score02;
    private double score03;
    private double score04;
    public void say(){
        score04 = (score01 + score02 + score03)/3;
        System.out.println(num + "\t\t" + name + "\t\t" + score01 + "\t" + score02 + "\t" + score03 + "\t" +score04);
    }

    public Demo_10_28_1(String name, int num, double score01, double score02, double score03) {
        this.name = name;
        this.num = num;
        this.score01 = score01;
        this.score02 = score02;
        this.score03 = score03;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getScore01() {
        return score01;
    }

    public void setScore01(double score01) {
        this.score01 = score01;
    }

    public double getScore02() {
        return score02;
    }

    public void setScore02(double score02) {
        this.score02 = score02;
    }

    public double getScore03() {
        return score03;
    }

    public void setScore03(double score03) {
        this.score03 = score03;
    }

    public double getScore04() {
        return score04;
    }

    public void setScore04(double score04) {
        this.score04 = score04;
    }
}
