package com.work.auth.pojo;

import java.io.Serializable;

import com.work.auth.base.BasePojo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * role_menu_config
 * @author 
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RoleMenuConfig extends BasePojo implements Serializable {
    /**
     * 主键ID
     */
    private String id;

    /**
     * 角色ID
     */
    private String roleId;

    /**
     * 菜单ID
     */
    private String menuId;

    private static final long serialVersionUID = 1L;
}