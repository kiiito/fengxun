package homework.demo_11_18;

public class Homework02 {
    public static void main(String[] args) {
        Student a = new Student("张三", 16);
        System.out.println(a);
        Student b = new Student("李四", 18);
        System.out.println(b);
    }
}
 class Student{
    String name;
    int age;

     public Student(String name, int age) {
         this.name = name;
         this.age = age;
     }

     @Override
     public String toString() {
         return "我叫" + name + ", 今年 " + age + "岁";
     }
 }
