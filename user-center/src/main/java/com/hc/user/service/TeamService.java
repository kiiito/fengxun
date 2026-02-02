package com.hc.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hc.user.model.Team;
import com.hc.user.model.User;
import com.hc.user.model.dto.TeamQuery;
import com.hc.user.model.request.TeamJoinRequest;
import com.hc.user.model.request.TeamQuitRequest;
import com.hc.user.model.request.TeamUpdateRequest;
import com.hc.user.model.vo.TeamUserVO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;


/**
* @author 风寻
* @description 针对表【team(队伍)】的数据库操作Service
* @createDate 2025-10-13 14:38:50
*/
public interface TeamService extends IService<Team> {

    /**
     * 创建队伍
     */
    long addTeam(Team team, User loginUser);

    /**
     * 搜索队伍
     * @param teamQuery 队伍查询封装类
     * @return 返回队伍列表
     */
    List<TeamUserVO> listTeams(TeamQuery teamQuery,boolean isAdmin);

    /**
     * 更新队伍
     * @param teamUpdateRequest 队伍更新请求封装类
     * @return 返回更新结果
     */
    boolean updateTeam(TeamUpdateRequest teamUpdateRequest,User loginUser);

    /**
     * 加入队伍
     * @param teamJoinRequest 队伍加入请求封装类
     * @param loginUser  当前登录用户
     * @return   返回加入结果
     */
    boolean joinTeam(TeamJoinRequest teamJoinRequest, User loginUser);

    /**
     * 退出队伍
     * @param teamQuitRequest 队伍退出请求封装类
     * @param loginUser 当前登录用户
     * @return 返回退出结果
     */
    boolean quitTeam(TeamQuitRequest teamQuitRequest, User loginUser);

    /**
     * 删除解散队伍
     * @param id 队伍id
     * @return 返回删除结果
     */
    boolean deleteTeam(long id, User loginUser);
}
