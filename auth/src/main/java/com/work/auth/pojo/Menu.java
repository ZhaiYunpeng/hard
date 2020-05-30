package com.work.auth.pojo;

import java.io.Serializable;
import java.util.Date;

import com.work.auth.base.BasePojo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * menu
 *
 * @author ZhaiYunpeng
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "菜单实体类")
public class Menu extends BasePojo implements Serializable {
    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    private String id;

    /**
     * 菜单名
     */
    @ApiModelProperty(value = "菜单名")
    private String menuName;

    /**
     * 菜单地址
     */
    @ApiModelProperty(value = "菜单地址")
    private String menuUrl;

    /**
     * 菜单描述
     */
    @ApiModelProperty(value = "菜单描述")
    private String menuDes;

    /**
     * 父级ID
     */
    @ApiModelProperty(value = "父级ID")
    private String parentId;

    /**
     * 菜单类型0：元素菜单；1：数据菜单
     */
    @ApiModelProperty(value = "菜单类型0：元素菜单；1：数据菜单")
    private String menuType;

    /**
     * 菜单状态0：可用；1：不可用
     */
    @ApiModelProperty(value = "菜单状态0：可用；1：不可用")
    private String menuStatus;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer menuSort;

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