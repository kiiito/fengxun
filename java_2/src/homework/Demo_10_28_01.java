package homework;

public class Demo_10_28_01 {
    public static void main(String[] args) {
        Demo_10_28_02 demo_10_28_02 = new Demo_10_28_02("李明",20,40);
        demo_10_28_02.say();
        Demo_10_28_02 demo_10_28_03 = new Demo_10_28_02("钱丽",16,20);
        demo_10_28_03.say();
    }
}
class Demo_10_28_02 {
    private String name;
    private int age;
    private double price;

    public Demo_10_28_02() {
    }

    public Demo_10_28_02(String name, int age, double price) {
        this.name = name;
        this.age = age;
        this.price = price;
    }

    public void say(){
        System.out.println("姓名 " + name + " 年龄 " + age + " 票价 " + price );
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
