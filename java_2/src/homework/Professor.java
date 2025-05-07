package homework;

public class Professor extends Teacher{
    double multiple = 1.3;
    public Professor(String name, int age, String post, double salary) {
        super(name, age, post, salary);
    }

    @Override
    public String introduce() {
        return  "姓名\t" + getName() + "\t年龄" + getAge() + "职称\t" + getPost() + "基本工资\t" + getSalary() * multiple;
    }
}
