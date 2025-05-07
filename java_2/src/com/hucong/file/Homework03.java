package com.hucong.file;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Properties;

public class Homework03 {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.setProperty("name","tom");
        properties.setProperty("age","5");
        properties.setProperty("color","red");
        properties.store(new FileOutputStream("src/com/hucong/file/Properties_/dog.properties"),null);
        Dog dog = new Dog
                (properties.getProperty("name"),
                        properties.getProperty("age"),
                        properties.getProperty("color"));
        System.out.println(dog.toString());
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("D:\\dog.txt"));
        oos.writeObject(dog);
        oos.close();
    }
    @Test
    public void m1() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("D:\\dog.txt"));
        Dog dog = (Dog) ois.readObject();
        System.out.println(dog.getName());
        ois.close();
    }
}
class Dog implements Serializable {
    private String name;
    private String age;
    private String color;

    public Dog(String name, String age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}