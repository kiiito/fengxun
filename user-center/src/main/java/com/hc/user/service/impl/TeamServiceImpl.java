package com.hc.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.hc.user.common.ErrorCode;
import com.hc.user.exception.BusinessException;
import com.hc.user.mapper.TeamMapper;
import com.hc.user.model.Team;
import com.hc.user.model.User;
import com.hc.user.model.UserTeam;
import com.hc.user.model.dto.TeamQuery;
import com.hc.user.model.enums.TeamStatusEnum;
import com.hc.user.model.request.TeamJoinRequest;
import com.hc.user.model.request.TeamQuitRequest;
import com.hc.user.model.request.TeamUpdateRequest;
import com.hc.user.model.vo.TeamUserVO;
import com.hc.user.model.vo.UserVO;
import com.hc.user.service.TeamService;
import com.hc.user.service.UserService;
import com.hc.user.service.UserTeamService;
import jakarta.annotation.Resource;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * @author 风寻
 * @description 针对表【team(队伍)】的数据库操作Service实现
 * @createDate 2025-10-13 14:38:50
 */
@Service
public class TeamServiceImpl extends ServiceImpl<TeamMapper, Team>
        implements TeamService {

    @Resource
    private UserTeamService userTeamService;

    @Resource
    private UserService userService;

    @Resource
    private RedissonClient redissonClient;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public long addTeam(Team team, User loginUser) {
        //1. 请求参数是否为空？
        if (team == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //2. 是否登录，未登录不允许创建
        if (loginUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN);
        }
        final long userId = loginUser.getId();
        //3. 校验信息
        //  a. 队伍人数 > 1 且 <= 20
        // 使用Optional.ofNullable安全处理可能为null的team.getMaxNum()值，若为null则返回默认值0
        int maxNum = Optional.ofNullable(team.getMaxNum()).orElse(0);
        if (maxNum < 1 || maxNum > 20) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "队伍人数不符合要求");
        }
        //  b. 队伍标题 <= 20
        String name = team.getName();
        if (StringUtils.isBlank(name) || name.length() > 20) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "队伍标题不符合要求");
        }
        //  c. 描述 <= 512
        String description = team.getDescription();
        if (StringUtils.isNotBlank(description) && description.length() > 512) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "队伍描述不符合要求");
        }
        //  d. status 是否公开（int）不传默认为 0（公开）
        int status = Optional.ofNullable(team.getStatus()).orElse(0);
        TeamStatusEnum statusEnum = TeamStatusEnum.getEnumByValue(status);
        if (statusEnum == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "队伍状态不符合要求");
        }
        //  e. 如果 status 是加密状态，一定要有密码，且密码 <= 32
        String password = team.getPassword();
        if (TeamStatusEnum.SECRET.equals(statusEnum) && (StringUtils.isBlank(password) || password.length() > 32)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "队伍密码设置不符合要求");
        }
        //  f. 超时时间 > 当前时间
        Date expireTime = team.getExpireTime();
        if (new Date().after(expireTime)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "队伍超时时间设置不符合要求");
        }
        //  g. 校验用户最多创建 5 个队伍
        //todo  可能存在bug 用户疯狂点击可能会创建超过5个队伍
        QueryWrapper<Team> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId", userId);
        long hasTeamNum = this.count(queryWrapper);
        if (hasTeamNum >= 5) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户最多创建 5 个队伍");
        }
        //4. 插入队伍信息到队伍表
        team.setId(null);
        team.setUserId(userId);
        boolean result = this.save(team);
        Long teamId = team.getId();
        if (!result || teamId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "队伍创建失败");
        }
        //5. 插入用户 => 队伍关系到关系表
        UserTeam userTeam = new UserTeam();
        userTeam.setUserId(userId);
        userTeam.setTeamId(teamId);
        userTeam.setJoinTime(new Date());
        result = userTeamService.save(userTeam);
        if (!result) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "队伍创建失败");
        }
        return teamId;
    }

    @Override
    public List<TeamUserVO> listTeams(TeamQuery teamQuery, boolean isAdmin) {
        QueryWrapper<Team> queryWrapper = new QueryWrapper<>();
        /**
         * 组合查询条件
         */
        if (teamQuery != null) {
            Long id = teamQuery.getId();
            if (id != null && id > 0) {
                queryWrapper.eq("id", id);
            }
            //根据id列表查询用户列表
            List<Long> idList = teamQuery.getIdList();
            if (CollectionUtils.isNotEmpty(idList)) {
                queryWrapper.in("id", idList);
            }
            // 从查询参数对象中获取搜索文本
            String searchText = teamQuery.getSearchText();
            // 检查搜索文本是否非空（既不是null也不是空字符串或纯空格）
            if (StringUtils.isNotBlank(searchText)) {
                // 构建一个AND条件组，在其中包含两个OR连接的LIKE条件
                queryWrapper.and(qw ->
                        qw.like("name", searchText)    // 在name字段中模糊匹配searchText
                                .or()                        // 或者
                                .like("description", searchText) // 在description字段中模糊匹配searchText
                );
            }
            String name = teamQuery.getName();
            if (StringUtils.isNotBlank(name)) {
                queryWrapper.like("name", name);
            }
            String description = teamQuery.getDescription();
            if (StringUtils.isNotBlank(description)) {
                queryWrapper.like("description", description);
            }
            Integer status = teamQuery.getStatus();
            TeamStatusEnum statusEnum = TeamStatusEnum.getEnumByValue(status);
            if (statusEnum == null) {
                statusEnum = TeamStatusEnum.PUBLIC;
            }
            if (!isAdmin && statusEnum.equals(TeamStatusEnum.PRIVATE)) {

                throw new BusinessException(ErrorCode.NO_AUTH, "非管理员不能查看私有队伍");
            }
            queryWrapper.eq("status", statusEnum.getValue());
            Integer maxNum = teamQuery.getMaxNum();
            if (maxNum != null && maxNum > 0) {
                queryWrapper.eq("maxNum", maxNum);
            }
            Long userId = teamQuery.getUserId();
            if (userId != null && userId > 0) {
                queryWrapper.eq("userId", userId);
            }
        }

        //不展示已过期的队伍
        queryWrapper.and(qw -> qw.gt("expireTime", new Date()).or().isNull("expireTime"));
        List<Team> teamList = this.list(queryWrapper);
        if (CollectionUtils.isEmpty(teamList)) {
            return new ArrayList<>();
        }
        List<TeamUserVO> teamUserVOList = new ArrayList<>();
        //关联查询创建人信息
        for (Team team : teamList) {
            Long userId = team.getUserId();
            if (userId == null) {
                continue;
            }
            User user = userService.getById(userId);
            TeamUserVO teamUserVO = new TeamUserVO();
            BeanUtils.copyProperties(team, teamUserVO);
            //脱敏用户信息
            if (user != null) {
                UserVO userVO = new UserVO();
                BeanUtils.copyProperties(user, userVO);
                teamUserVO.setCreateUser(userVO);
            }
            teamUserVOList.add(teamUserVO);
        }
        return teamUserVOList;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateTeam(TeamUpdateRequest teamUpdateRequest, User loginUser) {
        if (teamUpdateRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "队伍更新参数不能为空");
        }
        //查询用户队伍表id
        Long id = teamUpdateRequest.getId();
        if (id == null || id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "队伍id不能为空");
        }
        //根据传来的id查询队伍
        Team oldTeam = this.getById(id);
        if (oldTeam == null) {
            throw new BusinessException(ErrorCode.PARAMS_NULL_ERROR, "队伍不存在");
        }
        //判断该登录用户是否是队伍创建者或者管理员
        if (oldTeam.getUserId() != loginUser.getId() && !userService.isAdmin(loginUser)) {
            throw new BusinessException(ErrorCode.NO_AUTH, "没有权限修改队伍");
        }
        //如果队伍需要改成加密队伍 就必须传入密码
        TeamStatusEnum statusEnum = TeamStatusEnum.getEnumByValue(teamUpdateRequest.getStatus());
        if (statusEnum.equals(TeamStatusEnum.SECRET)) {
            if (StringUtils.isBlank(teamUpdateRequest.getPassword())) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "加密房间必须传入密码");
            }
        }
        //todo 如果新用户传入的新值与老值一致 如果有的参数不传值  则不更新
        //拷贝传入的对象
        Team updateTeam = new Team();
        BeanUtils.copyProperties(teamUpdateRequest, updateTeam);
        //更新队伍
        return this.updateById(updateTeam);
    }

    //todo 重复加入队伍问题 如果用户在一秒内疯狂点击（需要加锁进行处理）
    @Override
    public boolean joinTeam(TeamJoinRequest teamJoinRequest, User loginUser) {
        if (teamJoinRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空");
        }
        /**
         * 队伍必须存在 只能加入未加满 为过期的队伍
         */
        Long teamId = teamJoinRequest.getTeamId();
        Team team = getTeamById(teamId);
        /**
         * 禁止加入私有的队伍
         */
        Integer status = team.getStatus();
        TeamStatusEnum statusEnum = TeamStatusEnum.getEnumByValue(status);
        if (statusEnum.equals(TeamStatusEnum.PRIVATE)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "不能加入私有队伍");
        }
        //如果加入的队伍是加密的 必须匹配密码
        String password = teamJoinRequest.getPassword();
        if (statusEnum.equals(TeamStatusEnum.SECRET)) {
            if (StringUtils.isBlank(password) || !password.equals(team.getPassword())) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "密码错误");
            }
        }

        /**
         * 用户只能加入五个队伍
         */
        long userId = loginUser.getId();
        //只有一个线程能获取到锁
        RLock lock = redissonClient.getLock("yupao:join_team");
        try {
            while (true) {
                if (lock.tryLock(0, -1, TimeUnit.MILLISECONDS)) {
                    System.out.println("getLock:" + Thread.currentThread().getId());
                    //创建UserTeam 的查询条件
                    QueryWrapper<UserTeam> userTeamQueryWrapper = new QueryWrapper<>();
                    userTeamQueryWrapper.eq("userId", userId);
                    //查询该用户加入队伍的数量
                    long hasJoinNum = userTeamService.count(userTeamQueryWrapper);
                    if (hasJoinNum > 5) {
                        throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户最多创建和加入5个队伍");
                    }
                    //不能重复加入队伍
                    userTeamQueryWrapper = new QueryWrapper<>();
                    //查询user_team表是否有该用户加入该队伍
                    userTeamQueryWrapper.eq("userId", userId);
                    userTeamQueryWrapper.eq("teamId", teamId);
                    long hasUserJoinTeam = userTeamService.count(userTeamQueryWrapper);
                    //如果查询到了 说明该用户已经加入了该队伍
                    if (hasUserJoinTeam > 0) {
                        throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户已加入该队伍");
                    }

                    //已加入队伍的人数
                    long teamHasJoinNum = this.countTeamByTeamId(teamId);
                    if (teamHasJoinNum >= team.getMaxNum()) {
                        throw new BusinessException(ErrorCode.PARAMS_ERROR, "队伍已满");
                    }
                    //修改队伍信息
                    UserTeam userTeam = new UserTeam();
                    userTeam.setUserId(userId);
                    userTeam.setTeamId(teamId);
                    userTeam.setJoinTime(new Date());
                    return userTeamService.save(userTeam);
                }
            }
        } catch (InterruptedException e) {
            log.error("doCacheRecommendUser error", e);
            return false;
        } finally {
            //只能释放自己的锁
            if (lock.isHeldByCurrentThread()) {
                System.out.println("getLock:" + Thread.currentThread().getId());
                lock.unlock();
            }
        }


    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean quitTeam(TeamQuitRequest teamQuitRequest, User loginUser) {
        if (teamQuitRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请求参数为空");
        }
        Long teamId = teamQuitRequest.getTeamId();
        Team team = getTeamById(teamId);
        //判断该用户是否已加入队伍
     /*   QueryWrapper<UserTeam> userTeamQueryWrapper = new QueryWrapper<>();
        userTeamQueryWrapper.eq("userId", loginUser.getId());
        userTeamQueryWrapper.eq("teamId",teamQuitRequest.getTeamId());
        long hasUserJoinTeam = userTeamService.count(userTeamQueryWrapper);
        if (hasUserJoinTeam == 0){
            throw  new BusinessException(ErrorCode.PARAMS_ERROR, "用户未加入该队伍");
        }*/
        long userId = loginUser.getId();
        UserTeam queryUserTeam = new UserTeam();
        queryUserTeam.setUserId(userId);
        queryUserTeam.setTeamId(teamId);
        QueryWrapper<UserTeam> queryWrapper = new QueryWrapper<>(queryUserTeam);
        long count = userTeamService.count(queryWrapper);
        if (count == 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户未加入该队伍");
        }
        long teamHasJoinNum = this.countTeamByTeamId(teamId);
        //如果队伍中只剩下队长一人 则解散该队伍
        if (teamHasJoinNum == 1) {
            //删除队伍和所有加入的队伍的关系
            this.removeById(teamId);
//            QueryWrapper<UserTeam> userTeamQueryWrapper = new QueryWrapper<>();
//            userTeamQueryWrapper.eq("teamId", teamId);
//            return userTeamService.remove(userTeamQueryWrapper);
        } else {
            //是队长
            if (team.getUserId() == userId) {
                //把队伍转移给最早加入的用户
                QueryWrapper<UserTeam> userTeamQueryWrapper = new QueryWrapper<>();
                userTeamQueryWrapper.eq("teamId", teamId);
                userTeamQueryWrapper.last("order by id asc limit 2");
                //查询出队长和第二个入队队员的列表（根据加入队伍的id排序）
                List<UserTeam> userTeamList = userTeamService.list(userTeamQueryWrapper);
                if (CollectionUtils.isEmpty(userTeamList) || userTeamList.size() <= 1) {
                    throw new BusinessException(ErrorCode.SYSTEM_ERROR);
                }
                //获取到第二个入队用户的信息
                UserTeam nextUserTeam = userTeamList.get(1);
                Long nextUserTeamUserId = nextUserTeam.getUserId();
                //更新当前队伍的队长
                Team updateTame = new Team();
                updateTame.setId(teamId);
                updateTame.setUserId(nextUserTeamUserId);
                boolean result = this.updateById(updateTame);
                if (!result) {
                    throw new BusinessException(ErrorCode.SYSTEM_ERROR, "更新队伍队长失败");
                }
                //移除退出队长的关系
//                return userTeamService.remove(queryWrapper);
//            }else {
//                //不是队长
//                return userTeamService.remove(queryWrapper);
//            }
            }
        }
        //移除关系
        return userTeamService.remove(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteTeam(long id, User loginUser) {
        //校验队伍是否存在
        Team team = getTeamById(id);
        long teamId = team.getId();
        //校验是否为队长
        if (team.getUserId() != loginUser.getId()) {
            throw new BusinessException(ErrorCode.NO_AUTH, "没有权限删除队伍");
        }
        //移除所有加入队伍关联的信息
        QueryWrapper<UserTeam> userTeamQueryWrapper = new QueryWrapper<>();
        userTeamQueryWrapper.eq("teamId", teamId);
        boolean result = userTeamService.remove(userTeamQueryWrapper);
        if (!result) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "删除队伍失败");
        }
        //删除队伍
        return this.removeById(teamId);
    }


    /**
     * 根据id查询队伍
     *
     * @param teamId 队伍id
     * @return 返回队伍
     */
    private Team getTeamById(Long teamId) {
        if (teamId == null || teamId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "队伍id不能为空");
        }
        //根据传来的id查询队伍
        Team team = this.getById(teamId);
        if (team == null) {
            throw new BusinessException(ErrorCode.PARAMS_NULL_ERROR, "队伍不存在");
        }
        Date expireTime = team.getExpireTime();
        if (expireTime != null && expireTime.before(new Date())) {
            throw new BusinessException(ErrorCode.PARAMS_NULL_ERROR, "队伍已过期");
        }
        return team;
    }

    /**
     * 获取某队当前人数
     *
     * @param teamId 队伍id
     * @return 返回队伍人数
     */
    private long countTeamByTeamId(long teamId) {
        //创建UserTeam 的查询条件
        QueryWrapper<UserTeam> userTeamQueryWrapper = new QueryWrapper<>();
        userTeamQueryWrapper.eq("teamId", teamId);
        //查询该用户加入队伍的数量
        return userTeamService.count(userTeamQueryWrapper);
    }
}




