package com.hc.beanCycle;

public class vip {
    private String name;

    public void setName(String name) {
        System.out.println("第二步 给对象属性赋值");
        this.name = name;
    }

    public vip() {
        System.out.println("第一步 无参构造方法执行");
    }

    /**
     * 这个方法需要自己写 自己配 名字随意
     */
    public void initBean(){
        System.out.println("第三步 初始化bean");
    }

    /**
     * 这个方法需要自己写 自己配 名字随意
     */
    public void destroyBean(){
        System.out.println("第五步 销毁bean");
    }
}
