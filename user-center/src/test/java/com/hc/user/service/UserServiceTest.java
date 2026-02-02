//package com.hc.user.service;
//
//import com.hc.user.model.User;
//import jakarta.annotation.Resource;
//import org.junit.Assert;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//@SpringBootTest
//public class UserServiceTest {
//
//    @Resource
//    private UserService userService;
//    @Test
//    public void testSearchUsersByTags(){
//        List<String> tagNameList = Arrays.asList("java", "python");
//        List<User> userList = userService.searchUsersByTags(tagNameList);
//        Assert.assertNotNull(userList);
//    }
//
//}