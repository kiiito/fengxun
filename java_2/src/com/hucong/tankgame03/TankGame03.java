package com.hucong.tankgame03;

import javax.swing.*;

public class TankGame03 extends JFrame {
    //定义MyPanel
    MyPanel mp = null;
    public static void main(String[] args) {
        TankGame03 tankGame01 = new TankGame03();
    }
    public TankGame03(){
        mp = new MyPanel();
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp);//把游戏的绘图区域加进来
        this.setSize(1000,750);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(3);
        this.setVisible(true);
    }
}
