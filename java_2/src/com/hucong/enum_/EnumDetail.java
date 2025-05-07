package com.hucong.enum_;

/**
 * 1 使用enum关键字后 就不能在继承其他的类 因为enum会隐式继承Enum 而Java是单继承机制
 * 2 枚举类和普通一样 可以实现接口
 */
public class EnumDetail {
    public static void main(String[] args) {
        test.CLASSICMUSIC.playing();
    }
}
interface IE{
    void playing();
}
//1 使用enum关键字后 就不能在继承其他的类 因为enum会隐式继承Enum 而Java是单继承机制
//2 枚举类和普通一样 可以实现接口
enum test implements IE{
    CLASSICMUSIC;

    @Override
    public void playing() {
        System.out.println("音乐播放中");
    }
}