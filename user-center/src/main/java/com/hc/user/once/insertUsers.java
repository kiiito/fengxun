package com.hc.user.once;
import java.util.Date;

import com.hc.user.mapper.UserMapper;
import com.hc.user.model.User;
import jakarta.annotation.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
public class insertUsers {
    @Resource
    private UserMapper userMapper;
    /**
     * 批量插入用户
     */
//    @Scheduled(initialDelay = 5000,fixedRate = Long.MAX_VALUE)
    public void doInsertUser(){
        //计时工具
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        final int INSERT_NUM = 1000000;
        for (int i = 0; i < INSERT_NUM; i++) {
            User user = new User();
            user.setUsername("假用户");
            user.setUserAccount("fakeUser");
            user.setAvatarUrl("https://pic.code-nav.cn/user_avatar/1659139613182193665/thumbnail/J6wYaVYo-%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20230518181932.jpg");
            user.setGender(0);
            user.setUserPassword("12345678");
            user.setPhone("123456789");
            user.setTags("[]");
            user.setEmail("123@qq.com");
            user.setUserStatus(0);
            user.setUserRole(0);
            user.setPlantCode("111111");
            userMapper.insert(user);
        }
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }
}
