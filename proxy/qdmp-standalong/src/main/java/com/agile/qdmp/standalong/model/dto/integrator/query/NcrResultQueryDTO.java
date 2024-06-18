package com.agile.qdmp.standalong.model.dto.integrator.query;

import com.agile.tem.common.core.model.BaseQueryDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * NCR_RESULT
 *
 * @author hyzh code generator
 * @date 2022-12-18 14:33:57
 */
@Data
@ApiModel(value = "NcrResultQueryDTO", description = "NCR_RESULT")
public class NcrResultQueryDTO extends BaseQueryDTO {
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
     * ncrGuid
     */
    @ApiModelProperty(value = "ncrGuid")
    private String ncrGuid;
    /**
     * ResultGUID
     */
    @ApiModelProperty(value = "ResultGUID")
    private String resultGuid;
    /**
     * GageGUID
     */
    @ApiModelProperty(value = "GageGUID")
    private String gageGuid;
    /**
     * DimNo
     */
    @ApiModelProperty(value = "DimNo")
    private String dimNo;
    /**
     * DimType
     */
    @ApiModelProperty(value = "DimType")
    private String dimType;

}
