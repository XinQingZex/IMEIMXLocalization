package com.agile.qdmp.standalong.model.entity.uaa;

import com.agile.tem.common.core.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 客户端信息
 *
 * @author wenbinglei
 * @date 2021-02-01 15:04:38
 */
@Data
@TableName("oauth_client_details")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "客户端信息")
public class ClientDetail extends BaseModel {
    private static final long serialVersionUID = 1L;
    /**
     * 客户端ID
     */
    @ApiModelProperty(value = "客户端ID")
    private String clientId;
    /**
     * 客户端名称
     */
    @ApiModelProperty(value = "客户端名称")
    private String clientName;
    /**
     * 客户端所能访问的资源id集合
     */
    @ApiModelProperty(value = "客户端所能访问的资源id集合")
    private String resourceIds;
    /**
     * 用于指定客户端(client)的访问密匙
     */
    @ApiModelProperty(value = "用于指定客户端(client)的访问密匙")
    private String clientSecret;
    /**
     * 客户端(client)的访问密匙明文
     */
    @ApiModelProperty(value = "客户端(client)的访问密匙明文")
    private String clientSecretPlain;
    /**
     * 指定客户端申请的权限范围
     */
    @ApiModelProperty(value = "指定客户端申请的权限范围")
    private String scope;
    /**
     * 指定客户端支持的grant_type
     */
    @ApiModelProperty(value = "指定客户端支持的grant_type")
    private String authorizedGrantTypes;
    /**
     * 客户端的重定向URI,可为空
     */
    @ApiModelProperty(value = "客户端的重定向URI,可为空")
    private String webServerRedirectUri;
    /**
     * 指定客户端所拥有的Spring Security的权限值
     */
    @ApiModelProperty(value = "指定客户端所拥有的Spring Security的权限值")
    private String authorities;
    /**
     * 设定客户端的access_token的有效时间值(单位:秒)
     */
    @ApiModelProperty(value = "设定客户端的access_token的有效时间值(单位:秒)")
    private Integer accessTokenValidity;
    /**
     * 设定客户端的refresh_token的有效时间值(单位:秒)
     */
    @ApiModelProperty(value = "设定客户端的refresh_token的有效时间值(单位:秒)")
    private Integer refreshTokenValidity;
    /**
     * 预留字段
     */
    @ApiModelProperty(value = "预留字段")
    private String additionalInformation;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
    /**
     * 用于标识客户端是否已存档(即实现逻辑删除),默认值为"0"(即未存档)
     */
    @ApiModelProperty(value = "用于标识客户端是否已存档(即实现逻辑删除),默认值为\"0\"(即未存档)")
    private Integer archived;
    /**
     * 设置客户端是否为受信任的,默认为"0"(即不受信任的,1为受信任的).
     */
    @ApiModelProperty(value = "设置客户端是否为受信任的,默认为\"0\"(即不受信任的,1为受信任的).")
    private Integer trusted;
    /**
     * 设置用户是否自动Approval操作, 默认值为 "false", 可选值包括 "true","false", "read","write".
     */
    @ApiModelProperty(value = "设置用户是否自动Approval操作, 默认值为 \"false\", 可选值包括 \"true\",\"false\", \"read\",\"write\".")
    private String autoapprove;
}
