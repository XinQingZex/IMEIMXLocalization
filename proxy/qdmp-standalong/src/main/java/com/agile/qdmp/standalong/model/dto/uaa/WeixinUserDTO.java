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
@ApiModel(description = "微信用户")
public class WeixinUserDTO extends BaseDTO {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "账户ID")
    private Long accountId;

    @ApiModelProperty(value = "unionId")
    private String unionId;
    @ApiModelProperty(value = "openId")
    private String openId;
    @ApiModelProperty(value = "昵称")
    private String nickName;
    @ApiModelProperty(value = "头像")
    private String avatarUrl;

    @ApiModelProperty(value = "来源")
    private String source;
}
