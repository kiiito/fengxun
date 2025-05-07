package homework;

public class Student extends Person01{
    private int stu_id;

    public Student(String name, char gender, int age, int stu_id) {
        super(name, gender, age);
        this.stu_id = stu_id;
    }
    public void study(){
        System.out.println("我会好好学习");
    }

    @Override
    public String pay() {
        return super.pay() + "足球";
    }

    @Override
    public String toString() {
        return "Student{" +
                "stu_id=" + stu_id +
                '}' + super.toString();
    }

    public void Info() {
        System.out.println("学生信息:");
        System.out.println(super.baseInfo());
        System.out.println("学号:" + stu_id);
        study();
        System.out.println(pay());
    }
}
