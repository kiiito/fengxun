package com.xlz.service.impl;

import com.xlz.bean.Singer;
import com.xlz.bean.Song;
import com.xlz.bean.user;
import com.xlz.dao.MusicDao;
import com.xlz.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class MusicServiceImpl implements com.xlz.service.MusicService {
    @Override
    public boolean login(String username, String password) {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        try {
            MusicDao musicDao = sqlSession.getMapper(MusicDao.class);
            user user = musicDao.selectUser(username);
            return user != null && user.getPassword().equals(password);
        } finally {
            SqlSessionUtil.close(sqlSession);
        }
    }

    @Override
    public int register(String username, String password, String phoneNumber, String sex) {
        return addUser(username, password, phoneNumber, sex, 0);
    }

    @Override
    public List<Song> SongList() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        try {
            MusicDao musicDao = sqlSession.getMapper(MusicDao.class);
            return musicDao.GetSongLIST();
        } finally {
            SqlSessionUtil.close(sqlSession);
        }
    }

    public List<user> selectAllUser() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        try {
            MusicDao musicDao = sqlSession.getMapper(MusicDao.class);
            return musicDao.selectAllUser();
        } finally {
            SqlSessionUtil.close(sqlSession);
        }
    }

    @Override
    public List<Singer> selectAllSinger() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        try {
            MusicDao musicDao = sqlSession.getMapper(MusicDao.class);
            return musicDao.selectAllSinger();
        } finally {
            SqlSessionUtil.close(sqlSession);
        }
    }

    @Override
    public List<Song> selectVipSong() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        try {
            MusicDao musicDao = sqlSession.getMapper(MusicDao.class);
            return musicDao.GetVipSong();
        } finally {
            SqlSessionUtil.close(sqlSession);
        }
    }

    @Override
    public int addUser(String username, String password, String phoneNumber, String sex, int vip) {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        try {
            MusicDao musicDao = sqlSession.getMapper(MusicDao.class);
            user user = new user();
            user.setUsername(username);
            user.setPassword(password);
            user.setPhone(phoneNumber);
            user.setSex(sex);
            user.setVip(vip);
            int count = musicDao.addUser(user);
            if (count > 0) {
                sqlSession.commit();
            } else {
                sqlSession.rollback();
            }
            return count;
        } finally {
            SqlSessionUtil.close(sqlSession);
        }
    }
}