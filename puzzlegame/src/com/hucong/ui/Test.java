package com.hucong.ui;

import javax.swing.*;

public class Test {
    public static void main(String[] args) {
        //创建一个游戏主界面
        JFrame gameJFrame = new JFrame();
        gameJFrame.setSize(603,680);//设置界面的宽高
        gameJFrame.setVisible(true);// 默认界面不可见 需要设置

        //创建一个登录界面
        JFrame longJFrame = new JFrame();
        longJFrame.setSize(488,430);//设置界面的宽高
        longJFrame.setVisible(true);// 默认界面不可见 需要设置

        //创建一个注册页面
        JFrame registerJFrame = new JFrame();
        registerJFrame.setSize(488,500);//设置界面的宽高
        registerJFrame.setVisible(true);// 默认界面不可见 需要设置


    }
}
