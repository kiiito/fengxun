package com.hucong.tankgame;

import javax.swing.*;

public class TankGame01 extends JFrame {
    //定义MyPanel
    MyPanel mp = null;
    public static void main(String[] args) {
        TankGame01 tankGame01 = new TankGame01();
    }
    public TankGame01(){
        mp = new MyPanel();
        this.add(mp);//把游戏的绘图区域加进来
        this.setSize(1000,750);
        this.setDefaultCloseOperation(3);
        this.setVisible(true);
    }
}
