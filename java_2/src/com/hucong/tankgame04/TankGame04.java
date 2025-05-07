package com.hucong.tankgame04;

import javax.swing.*;

public class TankGame04 extends JFrame {
    //定义MyPanel
    MyPanel mp = null;
    public static void main(String[] args) {
        TankGame04 tankGame01 = new TankGame04();
    }
    public TankGame04(){
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
