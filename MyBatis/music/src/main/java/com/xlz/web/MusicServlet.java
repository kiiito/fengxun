package com.xlz.web;

import com.xlz.bean.Singer;
import com.xlz.bean.Song;
import com.xlz.bean.user;
import com.xlz.service.MusicService;
import com.xlz.service.impl.MusicServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet({"/login","/register","/addUser"})
public class MusicServlet extends HttpServlet {
    private MusicService musicService = new MusicServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getServletPath();
        if ("/login".equals(servletPath)){
            doLogin(req,resp);
        } else if ("/register".equals(servletPath)) {
            doRegister(req,resp);
        } else if ("/addUser".equals(servletPath)) {
            doAddUser(req,resp);
        }


    }

    private void doAddUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String username = request.getParameter("addUserName");
        String password = request.getParameter("addPassWord");
        String phoneNumber = request.getParameter("addPhoneNumber");
        String sex = request.getParameter("addGender");
        String addVip = request.getParameter("addVip");
        int vip = 0;
        if ("addMale".equals(sex)){
            sex = "男";
        } else if ("addFemale".equals(sex)) {
            sex = "女";
        }
        if ("1".equals(addVip)){
            vip= 1;
        } else if ("0".equals(addVip)) {
            vip = 0;
        }
        int count = musicService.addUser(username, password, phoneNumber, sex, vip);
        if (count != 1){
            response.sendRedirect(request.getContextPath() +"/RegisterError.jsp");
        }
    }

    private void doRegister(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String phoneNumber = request.getParameter("phoneNumber");
        String sex = request.getParameter("gender");
        if ("male".equals(sex)){
            sex = "男";
        } else if ("female".equals(sex)) {
            sex = "女";
        }

        int count = musicService.register(username, password,phoneNumber,sex);
        if (count != 1){
            response.sendRedirect(request.getContextPath() +"/RegisterError.jsp");
        }else {
            response.sendRedirect(request.getContextPath() +"/login.jsp");
        }
    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean key = musicService.login(username, password);
        if (key){
            List<Song> MusicSongs = musicService.SongList();
            List<user> users = musicService.selectAllUser();
            List<Singer> singers = musicService.selectAllSinger();
            List<Song> VipSong = musicService.selectVipSong();
            MusicSongs.forEach(song -> {
                System.out.println(song.getSongName() +" " + song.getSingerName() );
            });
            request.setAttribute("MusicSongs", MusicSongs);
            request.setAttribute("users", users);
            request.setAttribute("singers", singers);
            request.setAttribute("VipSong", VipSong);
            request.getRequestDispatcher("/index02.jsp").forward(request, response);
        }else {
            response.sendRedirect(request.getContextPath() +"/loginError.jsp");
        }
    }

}
