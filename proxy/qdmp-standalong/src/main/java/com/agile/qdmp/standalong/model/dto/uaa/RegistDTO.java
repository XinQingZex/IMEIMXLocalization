package com.agile.qdmp.standalong.model.dto.uaa;

import com.agile.tem.common.core.model.BaseDTO;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Author: wenbinglei
 * @Date: 2019/1/8 08:30
 * @Description:
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "RegistDTO", description = "用户注册对象")
public class RegistDTO extends BaseDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "头像")
    private String avatar;
    /**
     * 用户性别
     */
    @ApiModelProperty(value = "用户性别")
    private String gender;
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String mobile;
    /**
     * 是否激活
     */
    @ApiModelProperty(value = "是否激活")
    private Boolean activated;

    /**
     * 是否赋予默认角色
     */
    @ApiModelProperty(value = "是否赋予默认角色")
    private Boolean grantDefaultRole;

    @ApiModelProperty(value = "来源")
    private String source;

    /**
     * 手机验证码-手机注册使用
     */
    @ApiModelProperty(value = "手机验证码")
    private String verifyCode;
    /**
     * 所属服务商ID-添加用户辅助使用
     */
    @ApiModelProperty(value = "所属服务商ID-添加用户辅助使用")
    @JsonSerialize(using= ToStringSerializer.class)
    private Long supplierId;
    /**
     * 所属服务商名称-添加用户辅助使用
     */
    @ApiModelProperty(value = "所属服务商名称-添加用户辅助使用")
    private String supplierName;
}
