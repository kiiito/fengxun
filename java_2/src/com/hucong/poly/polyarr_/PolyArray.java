package com.hucong.poly.polyarr_;
public class PolyArray {
    public static void main(String[] args) {
        Person [] person = new Person[5];
        person[0] = new Person("jack",18);
        person[1] = new Student("jack",20,100);
        person[2] = new Student("tom",20,70);
        person[3] = new Teacher("mark",40,2000);
        person[4] = new Teacher("kill",38,9000);

        for (int i = 0; i < person.length; i++){
            //person的编译类型是 Person 运行类型是根据实际情况IVM来判断
            System.out.println(person[i].say());//使用到动态绑定机制
            //如何调用子类特有的方法
            //可以用到 instanceof  比较操作符，用于判断对象的运行类型是否为xx类型或xx类型的子类型
            if(person[i] instanceof Student){
                ((Student)person[i]).student01();//向下转型，简写方法
                //原有的写法
               //Student student = (Student)person[i];
               //student.student01();
            } else if (person[i] instanceof Teacher) {
                ((Teacher)person[i]).teacher01();
            } else if (person[i] instanceof Person) {

            }else {
                System.out.println("类型有误");
            }
        }
    }

}
