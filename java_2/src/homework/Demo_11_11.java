package homework;

public class Demo_11_11 {
    public static void main(String[] args) {
        Student05 student05 = new Student05();
        student05.order();
        Rectangle rectangle = new Rectangle("黄色");
        rectangle.info();
        Dog01 dog01 = new Dog01("牧羊犬", 3, "黑色");
        System.out.println(dog01.info());
        System.out.println("编号 "+Animal01.getId());
        Dog02 dog02 = new Dog02();
        dog02.info();
        dog02.shout();
        dog02.eat();
    }
}
abstract class Restaurant{
    abstract void order();
}
class Student05 extends Restaurant{
    @Override
    void order() {
        System.out.println("火腿炒面");
    }
}
class Teacher05 extends Restaurant {
    @Override
    void order() {
        System.out.println("香辣肉丝");
    }
}
class doctor05 extends Restaurant {
    @Override
    void order() {
        System.out.println("麻辣香锅");
    }
}
abstract class Graphical{
    private String color;

    abstract void info();
    public Graphical(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
class Rectangle extends Graphical{
    double c = 6.0;
    double k = 2.0;

    public Rectangle(String color) {
        super(color);
    }

    @Override
    void info() {
        System.out.println("长为" + c + "宽为" + k + "的" + super.getColor() + "长方形的面积是 " + (c * k));
    }
}
interface Graphical01{
    void info();
}
class Rectangle01 implements Graphical01{
    @Override
    public void info() {
        System.out.println("长方形的面积是 长乘宽" );
    }
}
class Animals{
    private String name;
    private int age;

    public Animals(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public String info(){
        return "名称" + this.getName()+",年龄 "+ this.getAge();
    }
}
class Dog01 extends Animals{
    private String color;

    public Dog01(String name, int age, String color) {
        super(name, age);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String info() {
        return super.info() + ",颜色 " + this.getColor();
    }
}
interface Animal01{
    int ID = 1;
    String NAME = "牧羊犬";
    void shout();
    public void info();
    static int getId(){
        return Animal01.ID;
    }
}
interface Action{
    void eat();
}
class Dog02 implements Animal01,Action{
    @Override
    public void eat() {
        System.out.println("喜欢吃骨头");
    }

    @Override
    public void shout() {
        System.out.println("汪汪叫");
    }

    @Override
    public void info() {
        System.out.println("名称 " + NAME);
    }
}