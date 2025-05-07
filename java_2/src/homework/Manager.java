package homework;

public class Manager extends Staff{
    int bonus = 1000;

    public Manager(String name, double salary, int days, double grade) {
        super(name, salary, days, grade);
    }

    @Override
    public void info() {
        System.out.println("ĞÕÃû\t" + getName() + "\t¹¤×Ê\t" + getSalary() * getDays() * getGrade() + bonus);
    }
}
