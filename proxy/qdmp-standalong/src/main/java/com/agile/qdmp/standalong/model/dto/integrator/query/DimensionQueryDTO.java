package com.agile.qdmp.standalong.model.dto.integrator.query;

import com.agile.tem.common.core.model.BaseQueryDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * DIMENSION
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:41:56
 */
@Data
@ApiModel(value = "DimensionQueryDTO", description = "DIMENSION")
public class DimensionQueryDTO extends BaseQueryDTO {
    private static final long serialVersionUID = 1L;
    /**
     * 企业id
     */
    @ApiModelProperty(value = "企业id")
    private Long companyId;
    /**
     * 企业名称
     */
    @ApiModelProperty(value = "企业名称")
    private String companyName;
    /**
     * IM Server ID
     */
    @ApiModelProperty(value = "IM Server ID")
    private Long serverId;
    /**
     * IM Server 名称
     */
    @ApiModelProperty(value = "IM Server 名称")
    private String serverName;
    /**
     * PartGUID
     */
    @ApiModelProperty(value = "PartGUID")
    private String partGuid;
    /**
     * Designator
     */
    @ApiModelProperty(value = "Designator")
    private String designator;
    /**
     * DimNo
     */
    @ApiModelProperty(value = "DimNo")
    private String dimNo;
    /**
     * DrawingGUID
     */
    @ApiModelProperty(value = "DrawingGUID")
    private String drawingGuid;
    /**
     * GUID
     */
    @ApiModelProperty(value = "GUID")
    private String guid;
    /**
     * Type
     */
    @ApiModelProperty(value = "Type")
    private Integer type;

    /**
     * operationId
     */
    @ApiModelProperty(value = "operationId")
    private String operationId;
    /**
     * procedureId
     */
    @ApiModelProperty(value = "procedureId")
    private String procedureId;

    /**
     * sampleId
     */
    @ApiModelProperty(value = "sampleId")
    private String sampleId;

    /**
     * sampleIds
     */
    @ApiModelProperty(value = "sampleIds")
    private String[] sampleIds;
}
