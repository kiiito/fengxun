package com.xlz.bean;

public class collectMusic {
    private int userId;
    private int songId;
    private String songName;
    private String singerName;
    private int vip;

    public collectMusic(int userId, int songId, String songName, String singerName, int vip) {
        this.userId = userId;
        this.songId = songId;
        this.songName = songName;
        this.singerName = singerName;
        this.vip = vip;
    }

    @Override
    public String toString() {
        return "collectMusic{" +
                "userId=" + userId +
                ", songId=" + songId +
                ", songName='" + songName + '\'' +
                ", singerName='" + singerName + '\'' +
                ", vip=" + vip +
                '}';
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public int getVip() {
        return vip;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }
}
