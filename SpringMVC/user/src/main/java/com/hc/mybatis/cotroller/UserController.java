package com.hc.mybatis.cotroller;


import com.hc.mybatis.pojo.User;
import com.hc.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user", method = {RequestMethod.GET})
    public String list(Model model) {
        List<User> users = userService.selectAll();
        users.forEach(user -> {
            System.out.println(user);
        });
        model.addAttribute("users", users);
        return "user_list";
    }

    @RequestMapping(value = "/user", method = {RequestMethod.POST})
    public String insert(User user) {
        int count = userService.insert(user);
        if (count <= 0) {
            throw new RuntimeException("添加失败");
        }
        return "redirect:/user";
    }

    @RequestMapping(value = "/user/{id}", method = {RequestMethod.DELETE})
    public String del(@PathVariable("id") Integer id) {
        int count = userService.delById(id);
        if (count <= 0) {
            throw new RuntimeException("删除失败");
        }
        return "redirect:/user";
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable("id") Integer id, Model model) {
        User user = userService.selectById(id);
        model.addAttribute("user", user);
        return "user_edit";
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public String update(User user) {
        int count = userService.update(user);
        if (count <= 0) {
            throw new RuntimeException("修改失败");
        }
        return "redirect:/user";
    }
}