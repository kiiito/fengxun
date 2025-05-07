package com.hucong.abstract_.template_mode;

public class BB extends Template{
    @Override
    public void job() {
        long num = 0;
        for (int i = 0; i < 100000000; i++) {
            num += i;
        }
    }
}
