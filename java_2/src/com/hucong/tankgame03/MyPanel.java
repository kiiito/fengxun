package com.hucong.tankgame03;

import com.hucong.tankgame04.Shot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class MyPanel extends JPanel implements KeyListener ,Runnable {
    //定义我的坦克
    Hero hero = null;
//    int direction = 0;
//    Enemy enemy01 = new Enemy(40, 0);
//    Enemy enemy02 = new Enemy(100, 0);
//    Enemy enemy03 = new Enemy(160, 0);
    //直接构建一个可以支持多线程的集合
    Vector<Enemy> enemies = new Vector<>();
    //设置敌人的个数
    int enemySize = 3;
    public MyPanel() {
        hero = new Hero(100,100);//初始化自己的坦克
        hero.setSpeed(5);
        //初始化敌人的坦克
        for (int i = 0; i < enemySize; i++) {
            //先创建敌人对象
            Enemy enemy = new Enemy(100 * (i + 1), 0);
            //在定义好方向
            enemy.setDirect(2);

            //添加到集合当中
            enemies.add(enemy);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);//填充矩形 默认黑色
        //画出我方坦克
        drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 1);
        //画出我方射击子弹
        if(hero.shot != null && hero.shot.isLive == true){
            System.out.println("子弹被绘制");
            g.draw3DRect(hero.shot.x,hero.shot.y,3,3,false);
        }
        //画出敌方坦克
        for (int i = 0; i < enemies.size(); i++) {
            //获取到集合中的对象
            Enemy enemy = enemies.get(i);
            //画出坦克
            drawTank(enemy.getX(), enemy.getY(), g, enemy.getDirect(),0);
        }
    }

    //编写方法 画出坦克

    /**
     * @param x      坦克的左上角x坐标
     * @param y      坦克的左上角y坐标
     * @param g      画笔
     * @param direct 控制坦克的方向
     * @param type   坦克的类型
     */
    public void drawTank(int x, int y, Graphics g, int direct, int type) {
        //根据不同类型的坦克 设置不同的颜色
        switch (type) {
            case 0:
                g.setColor(Color.cyan);//敌方坦克
                break;
            case 1:
                g.setColor(Color.yellow);//我方坦克
                break;
        }
        //根据坦克方向 来绘制坦克
        //0 向上 1 向右 2 向下 3 向左
        switch (direct) {
            case 0:
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x + 20, y);
                break;
            case 1:
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x + 60, y + 20);
                break;
            case 2:
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x + 20, y + 60);
                break;
            case 3:
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x, y + 20);
                break;
            default:
                System.out.println("暂时没有处理");
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W){
            hero.setDirect(0);
            hero.moveUp();
        }else if (code == KeyEvent.VK_D){
            hero.setDirect(1);
            hero.moveRight();
        } else if (code == KeyEvent.VK_S) {
            hero.setDirect(2);
            hero.moveDown();
        } else if (code == KeyEvent.VK_A) {
            hero.setDirect(3);
            hero.moveLeft();
        }
        if (code == KeyEvent.VK_J){
            hero.ShotEnemyTank();
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            this.repaint();
        }
    }
}
