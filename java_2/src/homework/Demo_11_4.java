package homework;

public class Demo_11_4 {
    public static void main(String[] args) {
        Customer jack = new Customer("jack");
        Customer tom = new Customer("tom");
        Customer king = new Customer("king");
        System.out.println(Customer.count);
        Pool.zs();
        Pool.zs();
        Pool.fs();
        Pool.info();
        Student001 s = new Student001();
        s.setName("jack");
        s.setAge(-18);
        s.read();
        Resume resume = new Resume("jack", 18, "男");
        resume.introduce();
        Dog dog = new Dog();
        dog.setName("牧羊犬");
        dog.setAge(3);
        dog.setColor("黑色");
        System.out.println("名称" + dog.getName() + "年龄" + dog.getAge() + "颜色" + dog.getColor());
    }
}

class Customer {
    static int count;
    private String name;

    public Customer(String name) {
        this.name = name;
        count++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Pool {
    static int a = 0;

    public static int zs() {
        return a = a + 3;
    }

    public static int fs() {
        return a = a - 2;
    }

    public static void info() {
        System.out.println("水池还有" + a + "升水");
    }
}

class Student001 {
    private String name;
    private int age;

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
        if (age < 0 && age > 200) {
            System.out.println("你输入的年龄有误");
        } else {
            this.age = age;
        }
    }

    public void read() {
        System.out.println("大家好，我是" + name + ",年龄 " + age);
    }
}

class Resume {
    private String name;
    private int age;
    private String sex;

    public Resume() {
    }

    public Resume(String name, int age, String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void introduce() {
        System.out.println("姓名 " + getName() + "\n" + "性别" + getSex() + "\n" + "年龄" + getAge());
    }
}

class Animal {
    private String name;
    private int age;

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
}

class Dog extends Animal {
    private String color;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}