package com.hucong.tankgame;

import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {
    //定义我的坦克
    Hero hero = null;
    public MyPanel(){
         hero = new Hero(100, 100);//初始化自己的坦克

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0,1000,750);//填充矩形 默认黑色
        drawTank(hero.getX(),hero.getY(),g,0,0);
        //画出坦克
    }

    //编写方法 画出坦克

    /**
     *
     * @param x 坦克的左上角x坐标
     * @param y 坦克的左上角y坐标
     * @param g 画笔
     * @param direct 控制坦克的方向
     * @param type 坦克的类型
     */
    public void drawTank(int x, int y, Graphics g,int direct ,int type){
        //根据不同类型的坦克 设置不同的颜色
        switch(type){
            case 0:
                g.setColor(Color.cyan);//我方坦克
                break;
            case 1:
                g.setColor(Color.yellow);//敌方坦克
                break;
        }
        //根据坦克方向 来绘制坦克
        switch (direct){
            case 0:
                g.fill3DRect(x,y,10,60,false);
                g.fill3DRect(x +30,y,10,60,false);
                g.fill3DRect(x + 10,y +10,20,40,false);
                g.fillOval(x +10,y +20,20,20);
                g.drawLine(x + 20, y + 30,x + 20,y);
                break;
            default:
                System.out.println("暂时没有处理");
        }
    }
}
