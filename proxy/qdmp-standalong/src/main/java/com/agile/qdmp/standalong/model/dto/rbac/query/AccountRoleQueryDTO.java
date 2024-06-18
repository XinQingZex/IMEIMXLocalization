package com.agile.qdmp.standalong.model.dto.rbac.query;

import com.agile.tem.common.core.model.BaseQueryDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户角色关联表
 *
 * @author wenbinglei
 * @date 2021-03-23 18:02:04
 */
@Data
@ApiModel(value = "", description = "用户角色关联表")
public class AccountRoleQueryDTO extends BaseQueryDTO {
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
     * 用户名-查询用户可用客户端使用
     */
    @ApiModelProperty(value="用户名-查询用户可用客户端使用")
    private String username;
    /**
     * 所属系统集合
     */
    @ApiModelProperty(value="所属系统集合")
    private String[] systemNames;
}
