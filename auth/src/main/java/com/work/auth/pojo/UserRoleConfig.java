package com.work.auth.pojo;

import java.io.Serializable;

import com.work.auth.base.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * user_role_config
 * @author 
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserRoleConfig extends BasePojo implements Serializable {
    /**
     * 主键ID
     */
    private byte[] id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 角色ID
     */
    private String roleId;

    private static final long serialVersionUID = 1L;
}