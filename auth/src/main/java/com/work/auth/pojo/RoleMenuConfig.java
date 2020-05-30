package com.work.auth.pojo;

import java.io.Serializable;

import com.work.auth.base.BasePojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * role_menu_config
 *
 * @author ZhaiYunpeng
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "角色菜单配置表")
public class RoleMenuConfig extends BasePojo implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private String id;

    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID")
    private String roleId;

    /**
     * 菜单ID
     */
    @ApiModelProperty(value = "菜单ID")
    private String menuId;

    private static final long serialVersionUID = 1L;
}