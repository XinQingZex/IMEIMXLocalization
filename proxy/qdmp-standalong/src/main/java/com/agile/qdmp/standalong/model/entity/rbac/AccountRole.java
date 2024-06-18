package com.agile.qdmp.standalong.model.entity.rbac;

import com.agile.tem.common.core.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 用户角色关联表
 *
 * @author wenbinglei
 * @date 2021-03-23 18:02:04
 */
@Data
@TableName("rbac_account_role")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "AccountRole", description = "用户角色关联表")
public class AccountRole extends BaseModel {
    private static final long serialVersionUID = 1L;
    /**
     * 账户ID
     */
    @ApiModelProperty(value = "账户ID")
    private Long accountId;
    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID")
    private Long roleId;
    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String roleName;
    /**
     * 角色编码
     */
    @ApiModelProperty(value = "角色编码")
    private String roleCode;
    /**
     * 所属系统
     */
    @ApiModelProperty(value = "所属系统")
    private String systemName;
    /**
     * 所属服务商ID
     */
    @ApiModelProperty(value = "所属服务商ID")
    private Long supplierId;
    /**
     * 所属服务商名称
     */
    @ApiModelProperty(value = "所属服务商名称")
    private String supplierName;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
}
