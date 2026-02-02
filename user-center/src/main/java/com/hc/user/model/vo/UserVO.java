package com.hc.user.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.Date;

@Data
public class UserVO {
    /**
     *
     */
    private long id;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 用户头像
     */
    private String avatarUrl;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 电话
     */
    private String phone;

    /**
     * 标签列表json
     */
    private String tags;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态
     */
    private Integer userStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 数据更新时间
     */
    private Date updateTime;

    /**
     * 用户角色 普通用户0 管理员1
     */
    private Integer userRole;
    /**
     * 星球编号
     */
    private String plantCode;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
