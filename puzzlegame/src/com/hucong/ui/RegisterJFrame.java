package com.hucong.ui;

import cn.hutool.core.io.FileUtil;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class RegisterJFrame extends JFrame implements MouseListener {
    ArrayList<User> allUsers;
    //提升三个输入框的变量的作用范围
    JTextField username = new JTextField();
    JTextField password = new JTextField();
    JTextField rePassword = new JTextField();

    //添加提交按钮和重置按钮 提升两个按钮的作用范围
    JButton submit  = new JButton();
    JButton reset  = new JButton();
    public RegisterJFrame(ArrayList<User> allUsers) {
        this.allUsers = allUsers;
        initFrame();
        initView();
        this.setVisible(true);// 默认界面不可见 需要设置
    }

    private void initView() {
        //添加用户名文字
        JLabel usernameText = new JLabel(new ImageIcon("D:\\javacode\\puzzlegame\\image\\register\\注册用户名.png"));
        usernameText.setBounds(106, 135, 79, 17);
        //把背景图片加载到页面当中
        this.getContentPane().add(usernameText);

        //添加用户名输入框

        username.setBounds(195,134,200,30);
        this.getContentPane().add(username);

        //添加密码文字
        JLabel passwordText = new JLabel(new ImageIcon("D:\\javacode\\puzzlegame\\image\\register\\注册密码.png"));
        passwordText.setBounds(120, 195, 64, 16);
        //把背景图片加载到页面当中
        this.getContentPane().add(passwordText);

        //密码输入框

        password.setBounds(195,195,200,30);
        this.getContentPane().add(password);
        //添加登入按钮
        submit.setBounds(123,310,128,47);
        submit.setIcon(new ImageIcon("image/register/注册按钮.png"));
        //去除按钮边框
        submit.setBorderPainted(false);
        //去除按钮的背景
        submit.setContentAreaFilled(false);
        this.getContentPane().add(submit);

        //验证码提示
        JLabel recodeText = new JLabel(new ImageIcon("image/register/再次输入密码.png"));
        recodeText.setBounds(93, 256, 96, 30);
        //把背景图片加载到页面当中
        this.getContentPane().add(recodeText);

        //验证码的输入框

        rePassword.setBounds(195,256,200,30);
        this.getContentPane().add(rePassword);

        //添加注册按钮
        reset.setBounds(256,310,128,47);
        reset.setIcon(new ImageIcon("image/register/重置按钮.png"));
        //去除按钮边框
        reset.setBorderPainted(false);
        //去除按钮的背景
        submit.setContentAreaFilled(false);
        this.getContentPane().add(reset);

        //添加背景图片
        JLabel background = new JLabel(new ImageIcon("image/login/background.png"));
        background.setBounds(0, 0, 470, 390);
        //把背景图片加载到页面当中
        this.getContentPane().add(background);

        //添加监听事件
        submit.addMouseListener(this);
        reset.addMouseListener(this);
    }

    private void initFrame() {
        this.setSize(488, 430);//设置界面的宽高
        //设置界面标题
        this.setTitle("拼图 注册");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式 有 0 1 2 3 四种关闭模式
        // 0 代表不做任何变动，程序运行 1 默认 程序运行 2 关闭所有的界面才停止重新运行(需要在所有界面都设置为2才行) 3 关闭一个就停止程序运行
        this.setDefaultCloseOperation(3);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == submit){
            //点击量注册按钮
            //1 用户名 密码不能为空
            if (username.getText().length() == 0 || password.getText().length() == 0 || rePassword.getText().length() == 0){
                showJDialog("用户名和密码不能为空");
                return;
            }
            //2判断两次密码输入是否一致
            if(!password.getText().equals(rePassword.getText())){
                showJDialog("两次输入的密码不一样");
                return;
            }
            //3 判断用户名和密码的格式是否正确 正则表达式
            if (!username.getText().matches("[a-zA-Z0-9]{4,6}")){
                showJDialog("用户名不合规则");
                return;
            }
            if (!password.getText().matches("\\S*(?=\\S{6,})(?=\\S*\\d)(?=\\S*[a-z])\\S*")){
                showJDialog("密码不合规则");
                return;
            }

            //4 判断用户名是否重复
            if (containsUsername(username.getText())){
                showJDialog("用户名重复");
                return;
            }
            //5 添加用户
            allUsers.add(new User(username.getText(),password.getText()));
            //6 写入文件
            FileUtil.writeLines(allUsers,"D:\\javacode\\puzzlegame\\userinfo.txt","UTF-8");
            //7 提示注册成功
            showJDialog("注册成功");
            //关闭注册页面 打开登入页面
            this.setVisible(false);
            new LoginJFrame();
        } else if (e.getSource() == reset) {
            //清空三个输入框
            username.setText("");
            password.setText("");
            rePassword.setText("");
        }
    }
    public boolean containsUsername(String username) {
        for (User u : allUsers) {
            if (u.getName().equals(username)) {
                return true;
            }
            System.out.println(u.getName());
        }
        return false;
    }
    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void showJDialog(String content) {
        //创建一个弹框对象
        JDialog jDialog = new JDialog();
        //给弹框设置大小
        jDialog.setSize(200,150);
        //给弹框置顶
        jDialog.setAlwaysOnTop(true);
        //让弹框居中
        jDialog.setLocationRelativeTo(null);
        //弹框不关闭永远无法操作下的界面
        jDialog.setModal(true);

        //创建JLable对象管理文字并添加到弹框当中
        JLabel warning = new JLabel(content);
        warning.setBounds(0,0,200,150);
        jDialog.getContentPane().add(warning);

        //让弹框显示出来
        jDialog.setVisible(true);
    }
}
