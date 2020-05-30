package com.work.auth.pojo;

import java.io.Serializable;

import com.work.auth.base.BasePojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * user_role_config
 *
 * @author ZhaiYunpeng
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "用户角色配置表")
public class UserRoleConfig extends BasePojo implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private String id;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private String userId;

    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID")
    private String roleId;

    private static final long serialVersionUID = 1L;
}