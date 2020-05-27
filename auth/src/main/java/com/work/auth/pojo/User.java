package com.work.auth.pojo;

import java.io.Serializable;
import java.util.Date;

import com.work.auth.base.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * user
 * @author 
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BasePojo implements Serializable {
    /**
     * 主键ID
     */
    private String id;

    /**
     * 用户别名
     */
    private String userAlias;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 证件号码
     */
    private String userIdNo;

    /**
     * 证件类型
     */
    private String idNoType;

    /**
     * 登录次数
     */
    private Integer loginCount;

    /**
     * 上次登录时间
     */
    private Date lastLoginTime;

    /**
     * 上次登录IP
     */
    private String lastLoginIp;

    /**
     * 头像地址
     */
    private String headerPictureUrl;

    /**
     * 用户类型
     */
    private String userType;

    /**
     * 用户状态
     */
    private String userStatus;

    /**
     * 创建人员ID
     */
    private String createUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改人员ID
     */
    private String updateUserId;

    /**
     * 修改时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}