package com.hucong.tankgame04;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class MyPanel extends JPanel implements KeyListener, Runnable {
    //定义我的坦克
    Hero hero = null;
    //    int direction = 0;
//    Enemy enemy01 = new Enemy(40, 0);
//    Enemy enemy02 = new Enemy(100, 0);
//    Enemy enemy03 = new Enemy(160, 0);
    //直接构建一个可以支持多线程的集合
    Vector<Enemy> enemies = new Vector<>();
    //定义一个集合 用于存放炸弹
    //当子弹击中坦克时 加入一个Bomb对象到bombs
    Vector<Bomb> bombs = new Vector<>();
    //设置敌人的个数
    int enemySize = 6;
    //定义三张图片 用于显示爆炸效果
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;

    public MyPanel() {
        hero = new Hero(100, 100);//初始化自己的坦克
        hero.setSpeed(5);
        //初始化敌人的坦克
        for (int i = 0; i < enemySize; i++) {
            //先创建敌人对象
            Enemy enemy = new Enemy((int)(Math.random() * 900), (int)(Math.random() * 600));
            //在定义好方向
            enemy.setDirect(2);
            //启动敌人坦克线程 让其动起来
            new Thread(enemy).start();
            //给敌方加入一颗子弹
               Shot shot = new Shot(enemy.getX() + 20, enemy.getY() + 60, enemy.getDirect());
               //加入enemy的vector成员
               enemy.shots.add(shot);
               //启动shot对象
               new Thread(shot).start();
            //添加到集合当中
            enemies.add(enemy);
        }
        //初始化图片照片
        image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.png"));
        image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.png"));
        image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.png"));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1000, 750);//填充矩形 默认黑色
        //画出我方坦克
        if (hero.isLive && hero != null) {
            drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 1);
            //画出我方射击子弹

            //System.out.println("子弹被绘制");
            for (int i = 0; i < hero.shots.size(); i++) {
                Shot shot = hero.shots.get(i);
                if (shot != null && shot.isLive) {
                    g.draw3DRect(shot.x, shot.y, 3, 3, false);
                } else {//如果该shot对象已经无效 就从shots集合中去除
                    hero.shots.remove(shot);
                }
            }
        }
        //如果bombs集合中有对象时 就画出
        for (int i = 0; i < bombs.size(); i++) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Bomb bomb = bombs.get(i);
            //根据当前这个bomb对象的life值去画出对应的图片
            if (bomb.life > 6){
                g.drawImage(image1,bomb.x,bomb.y,60,60,this);
            } else if (bomb.life > 3) {
                g.drawImage(image2,bomb.x,bomb.y,60,60,this);
            }else {
                g.drawImage(image3,bomb.x,bomb.y,60,60,this);
            }
            //让这个炸弹的生命值减少
            bomb.lifeDown();
            //如果炸弹的life为0 就从bombs集合中移除
            if (bomb.life == 0){
                bombs.remove(bomb);
            }
        }
        //画出敌方坦克

        for (int i = 0; i < enemies.size(); i++) {
            //获取到集合中的对象
            Enemy enemy = enemies.get(i);
            //判断当前坦克是否存活
            if (enemy.isLive) {
                //画出坦克
                drawTank(enemy.getX(), enemy.getY(), g, enemy.getDirect(), 0);
                //画出敌方所有的子弹
                for (int j = 0; j < enemy.shots.size(); j++) {
                    Shot shot = enemy.shots.get(j);
                    //绘制
                    if (shot.isLive) {
                        g.draw3DRect(shot.x, shot.y, 3, 3, false);
                    } else {
                        enemy.shots.remove(shot);
                    }
                }
            }
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

    public void hitEnemy(Shot s, Tank enemy) {
        //根据敌方坦克的方向来确定子弹击中坦克的坐标范围
        if (enemy.isLive) {
            switch (enemy.getDirect()) {
                case 0:
                case 2:
                    if (s.x > enemy.getX() && s.x < enemy.getX() + 40 &&
                            s.y > enemy.getY() && s.y < enemy.getY() + 60) {
                        s.isLive = false;
                        enemy.isLive = false;
                        enemies.remove(enemy);
                       // System.out.println(enemies.size());
                        //创建一个bomb对象 加入到bombs集合
                        Bomb bomb = new Bomb(enemy.getX(), enemy.getY());
                        bombs.add(bomb);
                    }
                    break;
                case 1:
                case 3:
                    if (s.x > enemy.getX() && s.x < enemy.getX() + 60 &&
                            s.y > enemy.getY() && s.y < enemy.getY() + 40) {
                        s.isLive = false;
                        enemy.isLive = false;
                        enemies.remove(enemy);
                        //System.out.println(enemies.size());
                        //创建一个bomb对象 加入到bombs集合
                        Bomb bomb = new Bomb(enemy.getX(), enemy.getY());
                        bombs.add(bomb);
                    }
                    break;
            }
        }
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            hero.setDirect(0);
            hero.moveUp();
        } else if (code == KeyEvent.VK_D) {
            hero.setDirect(1);
            hero.moveRight();
        } else if (code == KeyEvent.VK_S) {
            hero.setDirect(2);
            hero.moveDown();
        } else if (code == KeyEvent.VK_A) {
            hero.setDirect(3);
            hero.moveLeft();
        }
//        if (code == KeyEvent.VK_J) {
//            if (hero.shot == null || !(hero.shot.isLive)) {
//                hero.ShotEnemyTank();
//            }
//        }
        if (code == KeyEvent.VK_J) {
            if (hero.shots.size() <= 4) {
                hero.ShotEnemyTank();
            }
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
            //判断是否击中了坦克
            for (int j = 0; j < hero.shots.size(); j++) {
                Shot shot = hero.shots.get(j);
                if (shot != null && shot.isLive) {
                    for (int i = 0; i < enemies.size(); i++) {
                        hitEnemy(shot, enemies.get(i));
                    }
                }
            }
            for (int i = 0; i < enemies.size(); i++) {
                for (int j = 0; j < enemies.get(i).shots.size(); j++) {
                    Shot shot = enemies.get(i).shots.get(j);
                    if (shot != null && shot.isLive) {
                        hitEnemy(shot,hero);
                    }
                }
            }
            this.repaint();
        }
    }
}
