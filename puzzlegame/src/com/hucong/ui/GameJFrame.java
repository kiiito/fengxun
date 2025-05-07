package com.hucong.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {

    //给二维数组添加元素
    int[][] data = new int[4][4];
    //定义 0 的坐标位置
    int x = 0;
    int y = 0;
    //定义一个变量 记录当前展示图片的路径
    String path = "D:\\javacode\\puzzlegame\\image\\girl\\girl1\\";
    //定义一个二维数组 存储正确的数据
    int[][] win = {
            {1, 2, 3, 4},
            {5, 6, 7, 8,},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };
    //定义变量用来统计步数
        int step = 0;

    //创建选项下面的条目的对象
    JMenu replaceItem = new JMenu("更换图片");
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLoinItem = new JMenuItem("重新登入");
    JMenuItem closeItem = new JMenuItem("关闭游戏");

    JMenuItem beauty = new JMenuItem("美女");
    JMenuItem animal = new JMenuItem("动物");
    JMenuItem motion = new JMenuItem("运动");

//    int num = 2;
     Random r = new Random();
    JMenuItem accountItem = new JMenuItem("赞赏码");
    //构建构造器 让其界面初始化
    public GameJFrame() {
        //初始化界面
        initJFrame();

        //初始化菜单
        initJMnuBar();
        //初始化数据(打乱)
        initData();

        //初始化图片
        initImage();

        this.setVisible(true);// 默认界面不可见 需要设置
    }

    //初始化数据
    private void initData() {
        //要求把一个数组的数据 0-15 打乱顺序 然后再按四个一组的顺序添加到二维数组当中
        int[] tampArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        Random r = new Random();//获取的随机对象
        for (int i = 0; i < tampArr.length; i++) {
            int index = r.nextInt(tampArr.length);//获取随机索引
            int temp = tampArr[i];
            tampArr[i] = tampArr[index];
            tampArr[index] = temp;
        }
//        for (Object o : tampArr) {
//            System.out.print(o +" ");
//        }
        System.out.println();


        // 第一种遍历一威数组
        for (int i = 0; i < tampArr.length; i++) {
            if (tampArr[i] == 0) {
                x = i / 4;
                y = i % 4;
            }
                data[i / 4][i % 4] = tampArr[i];
        }
        //第二中遍历 二维数组
//        int index = 0;
//        for (int i = 0; i < data.length && index < tampArr.length; i++) {
//            for (int j = 0; j < data[i].length && index < tampArr.length; j++){
//                data[i][j] = tampArr[index];
//                index++;
//            }
//        }
        //遍历二维数组
//        for (int i = 0; i < data.length; i++) {
//            for (int j = 0; j < data[i].length;j++){
//                System.out.print(data[i][j] + " ");
//            }
//            System.out.println();
//        }
    }

    //初始化图片
    //添加图片的时候 就需要按照二维数组中管理数据添加图片
    private void initImage() {

        //清空已经出现的图片
        this.getContentPane().removeAll();
        if (victory()) {
            //显示胜利的图标
            JLabel winJLabel = new JLabel(new ImageIcon("D:\\javacode\\puzzlegame\\image\\win.png"));
            winJLabel.setBounds(203, 283, 197, 73);
            this.getContentPane().add(winJLabel);
        }
        JLabel stepCount = new JLabel("步数" + step);
        stepCount.setBounds(50,30,100,20);
        this.getContentPane().add(stepCount);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                //创建一个图片ImageIcon 的对象
                //获取当前要加载的图片的序号
                int num = data[i][j];
                //创建一个JLabel 的对象 (管理容器)
                JLabel jLabel1 = new JLabel(new ImageIcon(path + num + ".jpg"));
                //指定图片的位置
                jLabel1.setBounds(105 * j + 83, 105 * i + 134, 105, 105);
                //给图片添加边框
                jLabel1.setBorder(new BevelBorder(BevelBorder.LOWERED));
                //把管理容器添加到界面当中
                this.getContentPane().add(jLabel1);
            }
        }
        //添加背景图片
        JLabel background = new JLabel(new ImageIcon("D:\\javacode\\puzzlegame\\image\\background.png"));
        background.setBounds(40, 30, 508, 560);
        //把背景图片加载到页面当中
        this.getContentPane().add(background);
        //刷新界面
        this.getContentPane().repaint();
    }


    private void initJMnuBar() {
        //创建整个菜单对象
        JMenuBar jMenuBar = new JMenuBar();

        //创建菜单下面的两个选项对象 功能 关于我们
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");



        //将每个选项的条目添加到选项当中
        functionJMenu.add(replaceItem);
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoinItem);
        functionJMenu.add(closeItem);

        aboutJMenu.add(accountItem);

        replaceItem.add(beauty);
        replaceItem.add(animal);
        replaceItem.add(motion);

        //给条目绑定事件
        replaceItem.addActionListener(this);
        replayItem.addActionListener(this);
        reLoinItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);
        beauty.addActionListener(this);
        motion.addActionListener(this);
        animal.addActionListener(this);

        //将菜单里面的两个选项添加到菜单当中
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        //给整个界面设置菜单
        this.setJMenuBar(jMenuBar);
    }

    //快捷整理代码成一个方法的快捷键 Ctrl + Alt + m
    private void initJFrame() {
        this.setSize(603, 680);//设置界面的宽高
        //设置界面标题
        this.setTitle("拼图单机版 v1.0");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式 有 0 1 2 3 四种关闭模式
        // 0 代表不做任何变动，程序运行 1 默认 程序运行 2 关闭所有的界面才停止重新运行(需要在所有界面都设置为2才行) 3 关闭一个就停止程序运行
        this.setDefaultCloseOperation(3);

        //取消默认的居中放置 只有取消了才会按照xy轴的形式添加组件
        this.setLayout(null);
        //给整个界面添加键盘监听事件
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == 65) {
            //把界面所以图片都删除
            this.getContentPane().removeAll();
            //加载一张完整的图片
            JLabel all = new JLabel(new ImageIcon(path + "all.jpg"));
            all.setBounds(83, 134, 420, 420);
            this.getContentPane().add(all);
            //添加背景图片
            JLabel background = new JLabel(new ImageIcon("D:\\javacode\\puzzlegame\\image\\background.png"));
            background.setBounds(40, 30, 508, 560);
            //把背景图片加载到页面当中
            this.getContentPane().add(background);
            //刷新界面
            this.getContentPane().repaint();

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (victory()) {
            return;
        }
        //左 37 上38 右 39 下 40
        int code = e.getKeyCode();
        if (code == 37) {
            if (y == 3) {
                return;
            }
            data[x][y] = data[x][y + 1];
            data[x][y + 1] = 0;
            y++;
            //每移动一次 步数加一
            step++;
            initImage();
        } else if (code == 38) {
            //把空白方块下方的往上移动
            // x y 表示 空白方块
            // x + 1 y 表示空白方块下面的方块
            //把空白方块的数字赋值给空白方块
            if (x == 3) {
                return;
            }
            data[x][y] = data[x + 1][y];
            data[x + 1][y] = 0;
            x++;
            //每移动一次 步数加一
            step++;
            //调用方法按照最新的数字加载图片
            initImage();
        } else if (code == 39) {
            if (y == 0) {
                return;
            }
            data[x][y] = data[x][y - 1];
            data[x][y - 1] = 0;
            y--;
            //每移动一次 步数加一
            step++;
            initImage();
        } else if (code == 40) {
            if (x == 0) {
                return;
            }
            data[x][y] = data[x - 1][y];
            data[x - 1][y] = 0;
            x--;
            //每移动一次 步数加一
            step++;
            initImage();
        } else if (code == 65) {
            initImage();
        } else if (code == 87) {
            data = new int[][]{
                    {1, 2, 3, 4},
                    {5, 6, 7, 8,},
                    {9, 10, 11, 12},
                    {13, 14, 15, 0}
            };
            initImage();
        }else if (code == 90) {
            path = "D:\\javacode\\puzzlegame\\image\\girl\\girl"+ (r.nextInt(13)+1) +"\\";
            //计布数清零
            step = 0;
            //重新打乱数据
            initData();
            //重新加载图片
            initImage();
        } else if (code == 88) {
            path = "D:\\javacode\\puzzlegame\\image\\animal\\animal"+(r.nextInt(8)+1) +"\\";
            //计布数清零
            step = 0;
            //重新打乱数据
            initData();
            //重新加载图片
            initImage();
        } else if (code == 67) {
            path = "D:\\javacode\\puzzlegame\\image\\sport\\sport"+ (r.nextInt(10)+1) +"\\";
            //计布数清零
            step = 0;
            //重新打乱数据
            initData();
            //重新加载图片
            initImage();
        } else if (code == 86) {
            //计布数清零
            step = 0;
            //重新打乱数据
            initData();
            //重新加载图片
            initImage();
        } else if (code == 66) {
            //关闭当前的游戏界面
            this.setVisible(false);
            //创建一个新的登入界面
            new LoginJFrame();
        } else if (code == 78) {
            //直接关闭虚拟机就行
            System.exit(0);
        }
    }

    //判断data数组中的数据是否win数组相同
    //如果相同 返回ture 否则返回false
    public boolean victory() {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != win[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //获取当前被点击的条目对象
        Object obj = e.getSource();
        //判断
        if(obj == replayItem){
            System.out.println("重新进入游戏");
            //计布数清零
            step = 0;
            //重新打乱数据
            initData();
            //重新加载图片
            initImage();
        } else if (obj == reLoinItem) {
            //关闭当前的游戏界面
            this.setVisible(false);
            //创建一个新的登入界面
            new LoginJFrame();
        } else if (obj == closeItem) {
            //直接关闭虚拟机就行
            System.exit(0);
        } else if (obj == accountItem) {
            //创建一个弹框对象
            JDialog jDialog = new JDialog();
            //创建一个管理图片的容器对象
            //JLabel jLabel = new JLabel(new ImageIcon("image/about.png"));
            JLabel jLabel = new JLabel(new ImageIcon("image/reward.png"));
            //设置位置和宽高
//            jLabel.setBounds(0,0,258,258);
            jLabel.setBounds(0,0,540,536);
            //把图片添加到弹框当中
            jDialog.getContentPane().add(jLabel);
            //设置弹框大小
//            jDialog.setSize(344,344);
            jDialog.setSize(720,720);
            //让弹框置顶
            jDialog.setAlwaysOnTop(true);
            //让弹框居中
            jDialog.setLocationRelativeTo(null);
            //弹框不关闭无法操作下面的界面
            jDialog.setModal(true);
            //让弹框显示出来
            jDialog.setVisible(true);
        } else if (obj == beauty) {
            path = "D:\\javacode\\puzzlegame\\image\\girl\\girl"+ (r.nextInt(13)+1) +"\\";
            //计布数清零
            step = 0;
            //重新打乱数据
            initData();
            //重新加载图片
            initImage();
        }else if (obj == animal) {
            path = "D:\\javacode\\puzzlegame\\image\\animal\\animal"+(r.nextInt(8)+1) +"\\";
            //计布数清零
            step = 0;
            //重新打乱数据
            initData();
            //重新加载图片
            initImage();
//            num++;
//            if (num > 8){
//                num = 1;
//            }
        }else if (obj == motion) {
            path = "D:\\javacode\\puzzlegame\\image\\sport\\sport"+ (r.nextInt(10)+1) +"\\";
            //计布数清零
            step = 0;
            //重新打乱数据
            initData();
            //重新加载图片
            initImage();
        }
    }
}
