package com.hucong.tankgame02;

import javax.swing.*;

public class TankGame02 extends JFrame {
    //定义MyPanel
    MyPanel mp = null;
    public static void main(String[] args) {
        TankGame02 tankGame01 = new TankGame02();
    }
    public TankGame02(){
        mp = new MyPanel();
        this.add(mp);//把游戏的绘图区域加进来
        this.setSize(1000,750);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(3);
        this.setVisible(true);
    }
}
