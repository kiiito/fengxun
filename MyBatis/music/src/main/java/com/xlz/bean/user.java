package com.xlz.bean;

public class user {
    private int id;
    private String username;
    private String password;
    private int vip;
    private String sex;
    private String phone;

    public user() {
    }

    public user(int id, String username, String password, int vip, String sex, String phone) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.vip = vip;
        this.sex = sex;
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getVip() {
        return vip;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }

    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", vip=" + vip +
                '}';
    }
}
