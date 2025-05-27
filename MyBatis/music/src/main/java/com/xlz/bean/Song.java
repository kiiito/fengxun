package com.xlz.bean;

public class Song {
    private int id;
    private String SingerName;
    private String SongName;
    private int vip;

    private int SingerId;

    public Song() {
    }

    public Song(int id, String singerName, String songName, int vip, int singerId) {
        this.id = id;
        SingerName = singerName;
        SongName = songName;
        this.vip = vip;
        SingerId = singerId;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", SingerName='" + SingerName + '\'' +
                ", SongName='" + SongName + '\'' +
                ", vip=" + vip +
                '}';
    }

    public int getSingerId() {
        return SingerId;
    }

    public void setSingerId(int singerId) {
        SingerId = singerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSingerName() {
        return SingerName;
    }

    public void setSingerName(String singerName) {
        SingerName = singerName;
    }

    public String getSongName() {
        return SongName;
    }

    public void setSongName(String songName) {
        SongName = songName;
    }

    public int getVip() {
        return vip;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }
}
