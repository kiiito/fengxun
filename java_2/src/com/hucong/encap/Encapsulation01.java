//简单的封装案例


package com.hucong.encap;

public class Encapsulation01 {
    public static void main(String[] args) {
        Person person = new Person();
        person.setName("John");
        person.setAge(49);
        person.setSalary(3000);
        System.out.println(person.info());
        Person jack = new Person("jack", 200, 50000);
        System.out.println(jack.info());
    }
}

class Person {
    public String name;
    private int age;
    private double salary;
    //构造器实现，先要构建一个无参构造器

    public Person() {
    }

    public Person(String name, int age, double salary) {
        //如果只是这样写封装的效果就不存在了
//        this.name = name;
//        this.age = age;
//        this.salary = salary;
        //将构造器与set相结合就可以达到封装效果
        setName(name);
        setAge(age);
        setSalary(salary);
    }


    //利用快捷键快速创建get和set Alt + inset


    public String getName() {
        return name;
    }

    public void setName(String name) {
        //给名字确定字符范围
        if (name.length() >= 2 && name.length() <= 6) {
            this.name = name;
        } else {
            System.out.println("您输入的名字不合法，请输入2到6个字符，默认名");
            this.name = "无名";
        }

    }

    public int getAge() {
        return age;
    }

    //判断年龄合法
    public void setAge(int age) {
        if (age >= 1 && age <= 120) {
            this.age = age;
        } else {
            System.out.println("您输入的年龄不合法，请输入1到120之间的年龄，默认年龄");
            this.age = 18;
        }

    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String info() {
        return "name=" + name + " age=" + age + " 薪水=" + salary;
    }
}
