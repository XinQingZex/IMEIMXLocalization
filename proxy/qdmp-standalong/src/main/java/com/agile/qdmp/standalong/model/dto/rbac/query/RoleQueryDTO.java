package com.agile.qdmp.standalong.model.dto.rbac.query;

import com.agile.tem.common.core.model.BaseQueryDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 角色表
 *
 * @author wenbinglei
 * @date 2021-03-23 18:01:22
 */
@Data
@ApiModel(value = "", description = "角色表")
public class RoleQueryDTO extends BaseQueryDTO {
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
     * 所属系统
     */
    @ApiModelProperty(value = "所属系统")
    private String systemName;

    /**
     * 所属系统In操作
     */
    @ApiModelProperty(value="所属系统In操作")
    private String[] systemNames;

    /**
     * 查询用户已经获得的权限时使用
     */
    @ApiModelProperty(value="查询用户已经获得的权限时使用")
    private Long accountId;
}
