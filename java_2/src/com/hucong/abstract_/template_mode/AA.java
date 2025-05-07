package com.hucong.abstract_.template_mode;

/**
 * 子类继承父类 实现job方法的重写
 */
public class AA extends Template{
    @Override
    public void job() {
        long num = 0;
        for (int i = 0; i < 100000; i++) {
            num += i;
        }
    }
}
