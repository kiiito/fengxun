package com.hucong.ui;

import cn.hutool.core.io.FileUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;


public class LoginJFrame extends JFrame implements ActionListener, MouseListener {
    //创建一个集合存储正确的用户和密码
    ArrayList<User> allUsers= new ArrayList<>();
//    static ArrayList<User> list = new ArrayList<>();
//    static{
//        User hu =new User("胡聪","123");
//        User li = new User("list","123");
//        list.add(hu);
//        list.add(li);
//    }
    //添加登入按钮
    JButton login = new JButton();
    //添加注册按钮
    JButton register = new JButton();
    //添加用户名输入框
    JTextField username = new JTextField();
    //密码输入框
    JTextField password = new JTextField();

    JLabel rightCode = new JLabel();

    //验证码输入框
    JTextField code = new JTextField();
    String codeStr = CodeUtil.getCode();
    public LoginJFrame(){
        //读取本地文件的用户信息
        readUserInfo();

        //初始化页面
        initJFrame();

        //在这个界面添加内容
        initView();

        // 默认界面不可见 需要设置
        this.setVisible(true);
    }

    private void readUserInfo() {
        //读取数据
        List<String> userInfoStrList = FileUtil.readUtf8Lines("D:\\javacode\\puzzlegame\\userinfo.txt");
        //遍历集合获取用户信息并创建User对象
        for (String str : userInfoStrList) {
            //username=eula$password=1234
            String[] userInfoArr = str.split("&");
            // 0 username=eula  1 password=1234
            String[] arr1 = userInfoArr[0].split("=");
            String[] arr2 = userInfoArr[1].split("=");
            User u = new User(arr1[1], arr2[1]);
            allUsers.add(u);
        }
        System.out.println(allUsers);

    }

    private void initJFrame() {
        this.setSize(488,430);//设置界面的宽高
        //设置界面标题
        this.setTitle("拼图 登入");
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
//        this.addKeyListener(this);
        System.out.println(codeStr);
    }

    public void initView(){
        //添加用户名文字
        JLabel usernameText = new JLabel(new ImageIcon("image/login/用户名.png"));
        usernameText.setBounds(116, 135, 47, 17);
        //把背景图片加载到页面当中
        this.getContentPane().add(usernameText);

        //添加用户名输入框

        username.setBounds(195,134,200,30);
        this.getContentPane().add(username);

        //添加密码文字
        JLabel passwordText = new JLabel(new ImageIcon("image/login/密码.png"));
        passwordText.setBounds(130, 195, 32, 16);
        //把背景图片加载到页面当中
        this.getContentPane().add(passwordText);

        //密码输入框

         password.setBounds(195,195,200,30);
        this.getContentPane().add(password);

        //验证码提示
        JLabel codeText = new JLabel(new ImageIcon("image/login/验证码.png"));
        codeText.setBounds(133, 256, 50, 30);
        //把背景图片加载到页面当中
        this.getContentPane().add(codeText);

        //验证码的输入框

        code.setBounds(195,256,100,30);
        this.getContentPane().add(code);


        //设置内容
        rightCode.setText(codeStr);
        //位置和宽高
        rightCode.setBounds(300,256,50,30);
        //添加到界面
        this.getContentPane().add(rightCode);

        //添加登入按钮
        login.setBounds(123,310,128,47);
        login.setIcon(new ImageIcon("image/login/登录按钮.png"));
        //去除按钮边框
        login.setBorderPainted(false);
        //去除按钮的背景
        login.setContentAreaFilled(false);
        this.getContentPane().add(login);

        //添加注册按钮
        register.setBounds(256,310,128,47);
        register.setIcon(new ImageIcon("image/login/注册按钮.png"));
        //去除按钮边框
        register.setBorderPainted(false);
        //去除按钮的背景
        login.setContentAreaFilled(false);
        this.getContentPane().add(register);

        //添加背景图片
        JLabel background = new JLabel(new ImageIcon("image/login/background.png"));
        background.setBounds(0, 0, 470, 390);
        //把背景图片加载到页面当中
        this.getContentPane().add(background);

        //添加事件监听
        login.addActionListener(this);
        register.addActionListener(this);
        rightCode.addMouseListener(this);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        //获取当前被点击的条目对象
        Object obj = e.getSource();
        if(obj == login){
            System.out.println("登入");
            rightCodeTF();

//            if(code.getText().equals(codeStr)){
//                System.out.println("验证码正确");
//                nameTF();
//            } else if (code.getText().equals("")) {
//                showJDialog("验证码不能为空");
//                nameTF();
//            }else {
//                showJDialog("验证码错误");
//                rightCodeChanged();
////                rightCode.setText("");
//////                System.out.println(CodeUtil.getCode());
////                rightCode.setText(CodeUtil.getCode());
////                nameTF();
//            }
//            if(username.getText().equals(list.get(0).getName())){
//                System.out.println("正确");
//                passwordTF();
//            }else if(username.getText().equals("")) {
//                showJDialog("用户名不能为空");
//                passwordTF();
//            }else {
//                showJDialog("用户名错误");
//                passwordTF();
//            }
        }else if(obj == register){
            System.out.println("点击注册按钮");
            //关闭登入界面 打开注册界面
            this.setVisible(false);
            new RegisterJFrame(allUsers);
        }
    }
    public void rightCodeTF(){
        if(code.getText().equals(codeStr)){
            System.out.println("验证码正确");
            nameTF();
        } else if (code.getText().equals("")) {
            showJDialog("验证码不能为空");
            nameTF();
        }else {
            showJDialog("验证码错误");
            rightCodeChanged();
//                rightCode.setText("");
////                System.out.println(CodeUtil.getCode());
//                rightCode.setText(CodeUtil.getCode());
               // nameTF();
        }
    }
    public void rightCodeChanged(){
        rightCode.setText("");
        codeStr = CodeUtil.getCode();
        System.out.println(codeStr);
        rightCode.setText(codeStr);
    }

    public void nameTF() {
        if(username.getText().equals(allUsers.get(sumUsers()).getName())){
            System.out.println("正确");
            passwordTF();
        }else if(username.getText().equals("")) {
            showJDialog("用户名不能为空");
            passwordTF();
        }else {
            showJDialog("用户名错误");
            System.out.println(allUsers.get(sumUsers()).getName());
//            passwordTF();

        }
    }
//    public boolean nameTF() {
//        if(username.getText().equals(list.get(0).getName())){
//            System.out.println("正确");
//            passwordTF();
//        }else if(username.getText().equals("")) {
//            showJDialog("用户名不能为空");
//            passwordTF();
//        }else {
//            showJDialog("用户名错误");
//            passwordTF();
//            return false;
//        }
//        return true;
//    }
    public void passwordTF() {
        if(password.getText().equals(allUsers.get(sumUsers()).getPassword())){
            System.out.println("密码正确");
            this.setVisible(false);
                new GameJFrame();

//            new GameJFrame();
        } else if (password.getText().equals("")) {
            showJDialog("密码不能为空");
        } else {
            showJDialog("密码错误");
        }
    }
    public int sumUsers(){
        for (int i = 0; i < allUsers.size(); i++){
            if(username.getText().equals(allUsers.get(i).getName())){
                System.out.println("下标为" + i);
                return i;
            }
        }
        return 0;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //获取当前被点击的条目对象
        Object obj = e.getSource();
        if(obj == code){
            System.out.println("11");
            String codeStr = CodeUtil.getCode();
            //设置内容
            rightCode.setText(codeStr);
        }
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
}
