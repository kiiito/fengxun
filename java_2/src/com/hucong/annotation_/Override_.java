package com.hucong.annotation_;

public class Override_ {
}
class Father{
    public void say(){}

}
class char_ extends  Father{
    // 1 这里如果没有写 @Override 还是重写了父类
    // 2 如果你写了@Override注解 编译器就会去检查该方法是否真的重写了父类的方法 如果没有构成重写 则编译错误
    @Override
    public void say() {
        System.out.println("重写了方法");
    }
}