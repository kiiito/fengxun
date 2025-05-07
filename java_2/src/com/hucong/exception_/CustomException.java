package com.hucong.exception_;

/**
 * 自定义异常
 */
public class CustomException {
    public static void main(String[] args) {
        int age = 1;
        if(!(age >= 18 && age <= 120) ){
            //这里我们可以通过构造器 设置信息
            throw new AgeException("年龄需要在 18到120之间");
        }
        System.out.println("你输入的年龄范围正确");
    }
}
//一般自定义继承运行类型 可以使用默认的处理机制
class AgeException extends RuntimeException{
    public AgeException(String message) {
        super(message);
    }
}