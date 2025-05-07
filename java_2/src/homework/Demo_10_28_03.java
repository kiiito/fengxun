package homework;

public class Demo_10_28_03 {
    public static void main(String[] args) {
        Student01 student01 = new Student01();
        Student01 student02 = new Student01("jack",18);
        student02.read();
        System.out.println(student02.age);
        System.out.println(student02.name);
    }
}
class Student01{
    String name;
    int age;

    public Student01() {
        System.out.println("调用了无参构造器");
    }

    public Student01(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("调用了有参构造器");
    }
    public void read(){
        System.out.println("大家好，我是"+name+"，我正在读书");
    }
}
