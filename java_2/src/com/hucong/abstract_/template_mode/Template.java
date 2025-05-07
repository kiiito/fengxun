package com.hucong.abstract_.template_mode;

/**
 * 定义抽象类父类
 * 运用到了计时器的类  System.currentTimeMillis
 */

abstract public class Template {
   public abstract void job();

   public void calculateTime(){
    //得到开始的时间
    long start = System.currentTimeMillis();
    job();//动态绑定机制
    long end = System.currentTimeMillis();
    System.out.println("任务执行时间" + (end - start));
   }
}
