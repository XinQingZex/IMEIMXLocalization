package com.agile.qdmp.standalong.model.dto.uaa;

import com.agile.tem.common.core.model.BaseDTO;
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
@ApiModel(value = "MobileLoginDTO", description = "手机号码登录对象")
public class MobileLoginDTO extends BaseDTO {
    private static final long serialVersionUID = 1L;
    /**
     * 手机验证码
     */
    @ApiModelProperty(value = "手机验证码")
    private String code;
    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "来源")
    private String from;
}
