package com.hucong.tankgame05;

public class Shot implements Runnable {
    int x;
    int y;
    //子弹的方向 必须与坦克的方向一致
    int direct;
    //子弹的移动速度
    int speed = 5;
    //子弹是否存在
    boolean isLive = true;

    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    @Override
    public void run() {
        //0 向上 1 向右 2 向下 3 向左
        while (true){
            switch (direct){
                case 0:
                    y -= speed;
                    break;
                case 1:
                    x += speed;
                    break;
                case 2:
                    y += speed;
                    break;
                case 3:
                    x-= speed;
                    break;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //System.out.println("子弹 x " + x + " y " + y);
            //当我们的子弹到达边界时或者击打到敌方坦克线程结束
            if(!(x >= 0 && x <= 1000 && y >= 0 && y <= 700 && isLive)){
                //System.out.println("子弹消亡");
                isLive = false;
                break;
            }
        }
    }
}
