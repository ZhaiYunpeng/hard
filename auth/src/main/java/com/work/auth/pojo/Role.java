package com.work.auth.pojo;

import java.io.Serializable;
import java.util.Date;

import com.work.auth.base.BasePojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * role
 *
 * @author ZhaiYunpeng
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "角色实体类")
public class Role extends BasePojo implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private String id;

    /**
     * 角色名
     */
    @ApiModelProperty(value = "角色名")
    private String roleName;

    /**
     * 角色描述
     */
    @ApiModelProperty(value = "角色描述")
    private String roleDes;

    /**
     * 角色状态
     */
    @ApiModelProperty(value = "角色状态")
    private String roleStatus;

    /**
     * 角色排序
     */
    @ApiModelProperty(value = "角色排序")
    private Integer roleSort;

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
}