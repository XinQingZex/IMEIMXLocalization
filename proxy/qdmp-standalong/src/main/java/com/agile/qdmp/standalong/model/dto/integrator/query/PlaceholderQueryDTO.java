package com.agile.qdmp.standalong.model.dto.integrator.query;

import com.agile.tem.common.core.model.BaseQueryDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * Placeholder
 *
 * @author wenbinglei
 * @date 2022-10-21 11:48:14
 */
@Data
@ApiModel(value = "PlaceholderQueryDTO", description = "Placeholder")
public class PlaceholderQueryDTO extends BaseQueryDTO {
    private static final long serialVersionUID = 1L;
    /**
     * keyWord
     */
    @ApiModelProperty(value = "keyWord")
    private String keyWord;
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
     * GUID
     */
    @ApiModelProperty(value = "GUID")
    private String guid;
    /**
     * SampleGUID
     */
    @ApiModelProperty(value = "SampleGUID")
    private String sampleGuid;
    /**
     * DimGUID
     */
    @ApiModelProperty(value = "DimGUID")
    private String dimGuid;

    /**
     * Status
     */
    @ApiModelProperty(value = "Status")
    private Integer status;
}
