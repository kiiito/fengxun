package com.hucong.extend_;

public class Sub extends Base{
    public Sub() {
        //super();//默认会调用父类的无参构造器
        System.out.println("子类无参构造器Sub被调用");
    }
    //若无父类的无参构造器就则需要用super();去指定父类的构造器完成初始化
    public Sub(String name, int age) {
        super(name, age);
        System.out.println("子类String name, int age构造器Sub被调用");
    }
    public void say(){
        getN4();
        getTestN4();
    }
}
