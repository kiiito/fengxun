package com.hucong.tankgame05;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

public class TankGame05 extends JFrame {
    //定义MyPanel
    MyPanel mp = null;
    public static void main(String[] args) {
        TankGame05 tankGame01 = new TankGame05();
    }
    public TankGame05(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入1 新游戏 或者 2 继续上局游戏");
        int key = scanner.nextInt();
        mp = new MyPanel(key);
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp);//把游戏的绘图区域加进来
        this.setSize(1300,750);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(3);
        this.setVisible(true);

        //在JFrame 中添加相应关闭窗口的处理
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.keepRecord();
                System.exit(0);
            }
        });
    }
}
