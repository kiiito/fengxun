package homework;

public class Person01 {
    private String name;
    private char gender;
    private int age;

    public Person01(String name, char gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String pay() {
        return name + "爱玩";
    }

    @Override
    public String toString() {
        return "Person01{" +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                '}';
    }
    public String baseInfo() {
       return  "信息:\n" + "姓名\t" + name + "\n" + "性别\t" + gender +"\n";
    }

}
