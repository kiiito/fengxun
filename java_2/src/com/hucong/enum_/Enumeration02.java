package com.hucong.enum_;

/**
 * 实现enum关键字进行枚举
 * 1 使用关键字enum 代替 class
 * 2  public final static Season SPRING = new Season("春天","温暖"); 直接使用 SPRING("春天","温暖") 常量名(实参列表)
 * 3 如果有多个常量(对象) 使用 逗号间隔
 * 4 如果使用 enum 来实现枚举 要求将定义常量对象 写在最前面
 */
public class Enumeration02 {
    public static void main(String[] args) {
        System.out.println(Season.AUTUMN);
        System.out.println(Season.SUMMER);
        System.out.println(Season.WINTER);
        System.out.println(Season.SPRING);
    }
}
//使用关键字enum 代替 class
enum Season02{
    //2 public final static Season SPRING = new Season("春天","温暖"); 直接使用 SPRING("春天","温暖") 常量名(实参列表)
    //3 如果有多个常量(对象) 使用 逗号间隔
    // 4 如果使用 enum 来实现枚举 要求将定义常量对象 写在最前面
    // 5 使用无参构造器 创建枚举对象 则实参列表和小括号都可以省略
    SPRING("春天","温暖"),SUMMER("夏天","炎热"),
    AUTUMN ("秋天","凉爽"),WINTER("冬天","寒冷")/*,what*/;
    private String name;
    private String desc;
    // 1 将构造器私有化
    private Season02() {
    }
    private Season02(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }
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