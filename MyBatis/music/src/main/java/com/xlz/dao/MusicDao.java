package com.xlz.dao;

import com.xlz.bean.Singer;
import com.xlz.bean.Song;
import com.xlz.bean.user;

import java.util.List;

public interface MusicDao {

    user selectUser(String username);
    int insertUser(user user);
    List<Song> GetSongLIST();
    List<user> selectAllUser();
    List<Singer>selectAllSinger();
    List<Song> GetVipSong();
    int addUser(user user);
}
