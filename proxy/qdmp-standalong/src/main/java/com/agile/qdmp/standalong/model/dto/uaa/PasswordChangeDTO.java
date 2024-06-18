package com.agile.qdmp.standalong.model.dto.uaa;

import com.agile.tem.common.core.model.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @Description: 修改密码
 * @Author: wenbinglei
 * @Date: 2020/10/22 18:10
 */
@ApiModel(value = "", description = "修改密码")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PasswordChangeDTO extends BaseDTO {
    @ApiModelProperty(value = "当前密码")
    private String currentPassword;
    @ApiModelProperty(value = "新密码")
    private String newPassword;
}
