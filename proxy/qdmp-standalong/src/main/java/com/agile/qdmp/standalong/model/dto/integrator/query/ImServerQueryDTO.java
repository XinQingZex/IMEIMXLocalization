package com.agile.qdmp.standalong.model.dto.integrator.query;

import com.agile.tem.common.core.model.BaseQueryDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 企业质量管理大师信息表
 *
 * @author hyzh code generator
 * @date 2022-09-19 09:56:51
 */
@Data
@ApiModel(value = "ImServerQueryDTO", description = "企业质量管理大师信息表")
public class ImServerQueryDTO extends BaseQueryDTO {
    private static final long serialVersionUID = 1L;
    /**
     * 企业id
     */
    @ApiModelProperty(value = "企业id")
    private Long companyId;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;
    /**
     * API 版本
     */
    @ApiModelProperty(value = "API 版本")
    private String apiVersion;
    /**
     * 审核状态 自动入库无公司名称 未审核 已审核
     */
    @ApiModelProperty(value = "审核状态 自动入库无公司名称 未审核 已审核")
    private String state;

    /**
     * 创建者ID
     */
    @ApiModelProperty(value = "创建者ID")
    private Long creatorId;

}
