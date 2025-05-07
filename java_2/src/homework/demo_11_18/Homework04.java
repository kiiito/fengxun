package homework.demo_11_18;

public class Homework04 {
    public static void main(String[] args) {
        //向上转型
        People people = new Teacher();
        people.work();
        //向下转型 简写
        ((Teacher) people).take();
        People people02 = new Student04();
        people02.work();
        // 向下转型
        Student04 stu = (Student04) people02;
        stu.take();
//        ((Student04) people02).take();
    }
}
class People{
    public void work(){
        System.out.println("每个人都要工作");
    }
}
class Teacher extends People{
    @Override
    public void work() {
        System.out.println("教师要认真授课");
    }
    public void take() {
        System.out.println("老师正在讲课");
    }
}
class Student04 extends People{
    @Override
    public void work() {
        System.out.println("学生要好好学习");
    }
    public void take() {
        System.out.println("学生正在学习");
    }
}
