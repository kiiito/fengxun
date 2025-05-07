package homework;

public class Lecturer extends Teacher{
    double multiple = 1.1;

    public Lecturer(String name, int age, String post, double salary, double multiple) {
        super(name, age, post, salary);
        this.multiple = multiple;
    }

    @Override
    public String introduce() {
        return  "姓名\t" + getName() + "\t年龄" + getAge() + "职称\t" + getPost() + "基本工资\t" + getSalary() * multiple;
    }
}
