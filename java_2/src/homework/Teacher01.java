package homework;

public class Teacher01 extends Person01{
    private int work_age;

    public Teacher01(String name, char gender, int age, int work_age) {
        super(name, gender, age);
        this.work_age = work_age;
    }
    public void teacher(){
        System.out.println("我一定好好教书");
    }

    @Override
    public String toString() {
        return "Teacher01{" +
                "work_age=" + work_age +
                '}'+ super.toString();
    }

    @Override
    public String pay() {
        return super.pay() + "象棋";
    }
    public void Info() {
        System.out.println("老师信息:");
        System.out.println(super.baseInfo());
        System.out.println("工龄 :" + work_age);
        teacher();
        System.out.println(pay());
    }
}
