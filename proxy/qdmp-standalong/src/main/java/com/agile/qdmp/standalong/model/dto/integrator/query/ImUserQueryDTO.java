package com.agile.qdmp.standalong.model.dto.integrator.query;

import com.agile.tem.common.core.model.BaseQueryDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 质量管理大师用户信息表
 *
 * @author hyzh code generator
 * @date 2022-09-19 09:56:46
 */
@Data
@ApiModel(value = "ImUserQueryDTO", description = "质量管理大师用户信息表")
public class ImUserQueryDTO extends BaseQueryDTO {
    private static final long serialVersionUID = 1L;
    /**
     * 企业id
     */
    @ApiModelProperty(value = "企业id")
    private Long companyId;
    /**
     * IM Server ID
     */
    @ApiModelProperty(value = "IM Server ID")
    private Long serverId;
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long accountId;
    /**
     * 职员id
     */
    @ApiModelProperty(value = "职员id")
    private Long staffId;
    /**
     * 审核状态 自动入库无公司名称 未审核 已审核
     */
    @ApiModelProperty(value = "审核状态 自动入库无公司名称 未审核 已审核")
    private String state;

}
