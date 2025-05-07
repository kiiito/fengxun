package com.hucong.interface_;

/**
 * 继承与接口的区别
 * 继承是单继承 而接口可以多个
 */
public class ExtendsVsInterface {
}
class Monkey{
    private String name;

    public Monkey(String name) {
        this.name = name;
    }

    public void climb(){
        System.out.println("猴子爬树");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
interface FishAble{
    void swimming();
}
interface BirdAble{
    void flying();
}

/**
* 当子类继承了父类 就会自动拥有父类的功能
 * r如果子类需要扩展功能 可以通过实现接口来扩展
 * 可以理解 实现接口是对Java 单继承机制的补充
*/

class LitterMonkey extends Monkey implements FishAble,BirdAble {
    public LitterMonkey(String name) {
        super(name);
    }

    @Override
    public void climb() {
        System.out.println(getName() + "猴子会爬树");
    }

    @Override
    public void swimming() {
        System.out.println( getName() + "猴子通过学习学会了游泳");
    }
    @Override
    public void flying() {
        System.out.println( getName() + "猴子通过学习学会了飞");
    }
}
