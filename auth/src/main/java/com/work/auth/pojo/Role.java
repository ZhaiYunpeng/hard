package com.work.auth.pojo;

import java.io.Serializable;
import java.util.Date;

import com.work.auth.base.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * role
 * @author 
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Role extends BasePojo implements Serializable {
    /**
     * 主键ID
     */
    private String id;

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 角色描述
     */
    private String roleDes;

    /**
     * 角色状态
     */
    private String roleStatus;

    /**
     * 角色排序
     */
    private Integer roleSort;

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