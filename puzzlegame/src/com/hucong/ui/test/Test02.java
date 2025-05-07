package com.hucong.ui.test;
import javax.swing.*;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class Test02 extends JFrame implements ActionListener{

    JButton jtb1 =  new JButton("点1");
    JButton jtb2 =  new JButton("点2");
    JButton jtb3 =  new JButton("点3");
    public Test02(){
        initJFrame();

        //给按钮设置宽高
        jtb1.setBounds(0,0,100,50);
        //给按钮添加事件
        //采用匿名内部类
        jtb1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("1");
            }
        });
        //采用继承接口的方式
        jtb2.setBounds(100,0,100,50);
        jtb2.addActionListener(this);

        jtb3.setBounds(200,0,100,50);
        jtb3.addActionListener(this);

        //将按钮添加到整个页面当中
        this.getContentPane().add(jtb1);
        this.getContentPane().add(jtb2);
        this.getContentPane().add(jtb3);

        this.setVisible(true);// 默认界面不可见 需要设置
    }

    //快捷整理代码成一个方法的快捷键 Ctrl + Alt + m
    private void initJFrame() {
        this.setSize(603,680);//设置界面的宽高
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //获取当前被操作的按钮
        Object source = e.getSource();
        if (source == jtb3){
            jtb3.setSize(200,200);
        } else if (source == jtb2) {
            Random r = new Random();
            jtb2.setLocation(r.nextInt(),r.nextInt());
        }
    }
}
