package homework;

public class Employees extends Staff{
    public Employees(String name, double salary, int days, double grade) {
        super(name, salary, days, grade);
    }

    @Override
    public void info() {
        System.out.println("ÐÕÃû\t" + getName() + "\t¹¤×Ê\t" + getSalary() * getDays() * getGrade());
    }
}
