package com.agile.qdmp.standalong.model.entity.integrator;

import com.agile.tem.common.core.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 质量管理大师用户信息表
 *
 * @author hyzh code generator
 * @date 2022-09-19 09:56:46
 */
@Data
@TableName("sys_im_user")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ImUser", description = "质量管理大师用户信息表")
public class ImUser extends BaseModel {
    private static final long serialVersionUID = 1L;
    /**
     * 企业id
     */
    @ApiModelProperty(value = "企业id")
    private Long companyId;
    /**
     * 企业名称
     */
    @ApiModelProperty(value = "企业名称")
    private String companyName;
    /**
     * IM Server ID
     */
    @ApiModelProperty(value = "IM Server ID")
    private Long serverId;
    /**
     * IM Server 名称
     */
    @ApiModelProperty(value = "IM Server 名称")
    private String serverName;
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long accountId;
    /**
     * 职员ID
     */
    @ApiModelProperty(value = "职员 ID")
    private Long staffId;
    /**
     * 职员名称
     */
    @ApiModelProperty(value = "职员 名称")
    private String staffName;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String username;
    /**
     * 登录密码
     */
    @ApiModelProperty(value = "登录密码")
    private String password;
    /**
     * 审核状态 自动入库无公司名称 未审核 已审核
     */
    @ApiModelProperty(value = "审核状态 自动入库无公司名称 未审核 已审核")
    private String state;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Integer updateTime;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Integer createTime;
}
