package com.agile.qdmp.standalong.model.dto.uaa;

import com.agile.tem.common.core.model.BaseQueryDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 客户端信息
 *
 * @author wenbinglei
 * @date 2021-02-01 15:04:38
 */
@Data
@ApiModel(value = "", description = "客户端信息")
public class ClientDetailQueryDTO extends BaseQueryDTO {
    private static final long serialVersionUID = 1L;
    /**
     * 客户端ID
     */
    @ApiModelProperty(value = "客户端ID")
    private String clientId;

    /**
     * 指定客户端申请的权限范围
     */
    @ApiModelProperty(value = "指定客户端申请的权限范围")
    private String scope;
}
