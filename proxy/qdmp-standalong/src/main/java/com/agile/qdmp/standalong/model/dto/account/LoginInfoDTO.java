package com.agile.qdmp.standalong.model.dto.account;

import com.agile.qdmp.standalong.model.dto.rbac.AccountRoleDTO;
import com.agile.tem.common.core.model.BaseDTO;
import com.agile.tem.common.desensitize.annotation.FiledDesensitization;
import com.agile.tem.common.desensitize.type.MaskingTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户登录信息表
 *
 * @author wenbinglei
 * @date 2021-03-23 18:00:31
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "LoginInfoDTO", description = "用户登录信息表")
public class LoginInfoDTO extends BaseDTO {
    private static final long serialVersionUID = 1L;

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
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickname;
    /**
     * 用户头像
     */
    @ApiModelProperty(value = "用户头像")
    private String avatar;
    /**
     * 用户性别
     */
    @ApiModelProperty(value = "用户性别")
    private String gender;
    /**
     * 邮件地址
     */
    @ApiModelProperty(value = "邮件地址")
    private String email;
    /**
     * 手机号
     */
    @FiledDesensitization(value = MaskingTypeEnum.MOBILE_PHONE)
    @ApiModelProperty(value = "手机号")
    private String mobile;
    /**
     * 用户来源
     */
    @ApiModelProperty(value = "用户来源")
    private String userSource;
    /**
     * 是否激活
     */
    @ApiModelProperty(value = "是否激活")
    private Boolean activated;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String creator;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdTime;
    /**
     * 状态, normal=正常,lock=锁定
     */
    @ApiModelProperty(value = "状态, normal=正常,lock=锁定")
    private String state;

    /**
     * 登录后默认第一组权限
     */
    @ApiModelProperty(value = "登录后默认第一组权限")
    private List<AccountRoleDTO> roles;
}
