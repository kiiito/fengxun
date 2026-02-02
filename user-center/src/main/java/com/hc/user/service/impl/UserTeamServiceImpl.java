package com.hc.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.hc.user.mapper.UserTeamMapper;
import com.hc.user.model.UserTeam;
import com.hc.user.service.UserTeamService;
import org.springframework.stereotype.Service;

/**
* @author 风寻
* @description 针对表【user_team(用户队伍关系)】的数据库操作Service实现
* @createDate 2025-10-13 14:40:19
*/
@Service
public class UserTeamServiceImpl extends ServiceImpl<UserTeamMapper, UserTeam>
    implements UserTeamService {

}




