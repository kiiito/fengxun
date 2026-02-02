package com.hc.springboot.bean;

import lombok.*;

/**
 * 采用lombok插件自动生成getter和setter方法
 * @Data注解相当于同时使用了@ToString,@EqualsAndHashCode,@Getter,@Setter注解
 * 无参构造器采用注解@NoArgsConstructor生成
 * 注意如果不想要某个属性生成或展示 可以用exclude注解排除
 *@RequiredArgsConstructor 生成必要参数的构造器（如果属性中有final修饰的，就是必要参数）有这个注释
 * 就不可以用@NoArgsConstructor注解了
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "age")
public class Person {
    private String name;
    private  Integer age;

}
