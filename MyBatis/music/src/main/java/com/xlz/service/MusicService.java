package com.xlz.service;

import com.xlz.bean.Singer;
import com.xlz.bean.Song;
import com.xlz.bean.user;

import java.util.List;

public interface MusicService {
        boolean login(String username,String password);
        int register(String username,String password,String phoneNumber,String sex);

        List<Song> SongList();
        List<user> selectAllUser();
        List<Singer> selectAllSinger();
        List<Song> selectVipSong();
        int addUser(String username,String password,String phoneNumber,String sex,int vip);
}
