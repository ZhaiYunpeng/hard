package com.work.auth.pojo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import com.work.auth.base.BasePojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * user
 *
 * @author
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "用户实体类")
public class User extends BasePojo implements UserDetails, Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private String id;

    /**
     * 用户别名
     */
    @ApiModelProperty(value = "用户别名")
    private String userAlias;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户真实姓名")
    private String userName;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 证件号码
     */
    @ApiModelProperty(value = "证件号码")
    private String userIdNo;

    /**
     * 证件类型
     */
    @ApiModelProperty(value = "证件类型")
    private String idNoType;

    /**
     * 登录次数
     */
    @ApiModelProperty(value = "登录次数")
    private Integer loginCount;

    /**
     * 上次登录时间
     */
    @ApiModelProperty(value = "上次登录时间")
    private Date lastLoginTime;

    /**
     * 上次登录IP
     */
    @ApiModelProperty(value = "上次登录IP")
    private String lastLoginIp;

    /**
     * 头像地址
     */
    @ApiModelProperty(value = "头像地址")
    private String headerPictureUrl;

    /**
     * 用户类型
     */
    @ApiModelProperty(value = "用户类型")
    private String userType;

    /**
     * 用户状态
     */
    @ApiModelProperty(value = "用户状态")
    private String userStatus;

    /**
     * 创建人员ID
     */
    @ApiModelProperty(value = "创建人员ID")
    private String createUserId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 修改人员ID
     */
    @ApiModelProperty(value = "修改人员ID")
    private String updateUserId;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}