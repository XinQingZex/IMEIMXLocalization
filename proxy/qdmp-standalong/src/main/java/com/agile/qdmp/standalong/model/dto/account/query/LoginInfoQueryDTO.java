package com.agile.qdmp.standalong.model.dto.account.query;

import com.agile.tem.common.core.model.BaseQueryDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户登录信息表
 *
 * @author wenbinglei
 * @date 2021-03-23 18:00:31
 */
@Data
@ApiModel(value = "", description = "用户登录信息表")
public class LoginInfoQueryDTO extends BaseQueryDTO {
    private static final long serialVersionUID = 1L;

    /**
     * 关键词
     */
    @ApiModelProperty(value = "关键词")
    private String keyWord;
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
     * 状态, normal=正常,lock=锁定
     */
    @ApiModelProperty(value = "状态, normal=正常,lock=锁定")
    private String state;
}
