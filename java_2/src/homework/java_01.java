package homework;

public class java_01 {
    public static void main(String[] args) {
        Person [] persons = new Person[3];
        persons [0] = new Person("jack",18,"无工作");
        persons[1] = new Person("tom",48,"it");
        persons[2] = new Person("mei",38,"高管");

        for (int i = 0; i < persons.length - 1; i++){
            for (int j = 0; j < persons.length -i -1;j++){
              Person person = null;
              if(persons[j].getAge() > persons[j + 1].getAge()){
                    person = persons[j];
                    persons[j] = persons[j + 1];
                    persons[j + 1] = person;
              }
            }
        }
        for (int i = 0; i < persons.length;i++){
            System.out.println(persons[i]);
        }

    }

}


class Person{
   private String name;
   private int age;
   private String job;

    public Person(String name, int age, String job) {
        this.name = name;
        this.age = age;
        this.job = job;
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

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
//  public void info() {
//      System.out.println("姓名" + name + " 年龄" + age + "工作" + job);
//  }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", job='" + job + '\'' +
                '}';
    }
}
