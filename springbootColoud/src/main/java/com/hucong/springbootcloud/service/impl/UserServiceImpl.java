package com.hucong.springbootcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hucong.springbootcloud.domain.User;
import com.hucong.springbootcloud.mapper.UserMapper;
import com.hucong.springbootcloud.service.UserService;

import org.springframework.stereotype.Service;

/**
* @author 风寻
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2025-11-04 11:37:42
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

}




