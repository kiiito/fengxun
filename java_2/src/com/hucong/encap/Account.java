package com.hucong.encap;

public class Account {
    public String name;
    private double balance;
    private String password;

    public Account() {
    }

    public Account(String name, double balance, String password) {
        setName(name);
        setBalance(balance);
        setPassword(password);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name.length() >= 2 && name.length() <= 4){
            this.name = name;
        }else{
            System.out.println("您输入的名字不合法，请输入2到4个字符");
        }

    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        if(balance > 20){
            this.balance = balance;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(password.length() == 6){
            this.password = password;
        }else{
            System.out.println("您输入的密码必须满足6位，给您默认密码");
            this.password = "123456";
        }

    }
    public String info() {
        return "name=" + name + " balance=" + balance + " 密码=" + password;
    }
}
