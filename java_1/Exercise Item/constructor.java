//构造器的使用

public class constructor {
    public static void main(String[] agrs) {
    //构造器不需要进行 person p1 = new person();（对类）
    //方法对比， Tower tower = new Tower();
    //tower.move();构造器是不能自己去调用
    //构造器是完成对象的初始化，而不是创建对象
      person p1 = new person("jack",29);
      System.out.println(p1.name + p1.age);
       //无参构造器
        person p2 = new person();
        System.out.println(p2.name + p2.age);
    }
}
class person{
    String name;//属性
    int age;
    //构造器名必须与类名相同
    public person(String thisName, int thisAge){
        name = thisName;
        age = thisAge;
    }
    //无参构造器
   public person(){
        age = 18;
    }
}
class taster{
    String name;
//构建构造器的快捷方式 Alt + inset  连续选择多个参数需要用到Ctrl
    public taster(String name) {
        this.name = name;
    }
}
