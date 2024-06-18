package com.agile.qdmp.standalong.model.entity.rbac;

import com.agile.tem.common.core.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 角色表
 *
 * @author wenbinglei
 * @date 2021-03-23 18:01:22
 */
@Data
@TableName("rbac_role")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Role", description = "角色表")
public class Role extends BaseModel {
    private static final long serialVersionUID = 1L;
    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String name;
    /**
     * 角色编码
     */
    @ApiModelProperty(value = "角色编码")
    private String code;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 所属系统
     */
    @ApiModelProperty(value = "所属系统")
    private String systemName;
    /**
     * 修改者ID
     */
    @ApiModelProperty(value = "修改者ID")
    private Long editor;
    /**
     * 是否默认system_name默认权限
     */
    @ApiModelProperty(value = "是否默认system_name默认权限")
    private Boolean isDefault;
    /**
     * 是否可直接授权
     */
    @ApiModelProperty(value = "是否可直接授权")
    private Boolean isAuthorize;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;
}
