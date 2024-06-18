package com.agile.qdmp.standalong.model.dto.rbac;

import com.agile.tem.common.core.model.BaseDTO;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 角色表
 *
 * @author wenbinglei
 * @date 2021-03-23 18:01:22
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "RoleDTO", description = "角色表")
public class RoleDTO extends BaseDTO {
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


    /**
     * 是否已经授权-系统平台授权功能使用
     */
    @ApiModelProperty(value = "是否已经授权-系统平台授权功能使用")
    private Boolean isGrant;
    /**
     * 授权ID-系统平台取消授权功能使用
     */
    @ApiModelProperty(value = "授权ID-系统平台取消授权功能使用", example = "授权ID-系统平台取消授权功能使用")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long accountRoleId;
}
