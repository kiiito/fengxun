package com.hucong.enum_;

/**
 * 只需要固定的对象 不希望被修改 和 添加
 * 枚举的运用
 * 1 将构造器私有化
 * 2 本类内部创建一组对象
 * 3 对外暴露对象 通过为对象添加 public final static 修饰符
 * 4 可以提供get方法 但不要提供set方法
 */

public class Enumeration {
    public static void main(String[] args) {
        System.out.println(Season.AUTUMN);
        System.out.println(Season.SUMMER);
        System.out.println(Season.WINTER);
        System.out.println(Season.SPRING);
    }
}

class Season{
    private String name;
    private String desc;

    // 1 将构造器私有化

    private Season(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    // 2 本类内部创建一组对象
    // 3 对外暴露对象 通过为对象添加 public final static 修饰符
    public final static Season SPRING = new Season("春天","温暖");
    public final static Season SUMMER = new Season("夏天","炎热");
    public final static Season AUTUMN = new Season("秋天","凉爽");
    public final static Season WINTER = new Season("冬天","寒冷");

    // 4 可以提供get方法 但不要提供set方法

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "Season{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}