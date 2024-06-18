package com.agile.qdmp.standalong.model.dto.integrator.query;

import com.agile.tem.common.core.model.BaseQueryDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * RESULT
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:42:06
 */
@Data
@ApiModel(value = "ResultQueryDTO", description = "RESULT")
public class ResultQueryDTO extends BaseQueryDTO {
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
     * _guid
     */
    @ApiModelProperty(value = "_guid")
    private String guid;

    /**
     * jobId
     */
    @ApiModelProperty(value = "jobId")
    private String jobId;
    /**
     * sampleId
     */
    @ApiModelProperty(value = "sampleId")
    private String sampleId;
    /**
     * lotId
     */
    @ApiModelProperty(value = "lotId")
    private String lotId;
    /**
     * Dim_guid
     */
    @ApiModelProperty(value = "Dim_guid")
    private String dimGuid;
    /**
     * GageGUID
     */
    @ApiModelProperty(value = "GageGUID")
    private String gageGuid;
    /**
     * Status
     */
    @ApiModelProperty(value = "Status")
    private Integer status;
    /**
     * MeasuredByGUID
     */
    @ApiModelProperty(value = "MeasuredByGUID")
    private String measuredByGuid;
    /**
     * NCRGUID
     */
    @ApiModelProperty(value = "NCRGUID")
    private String ncrGuid;

    /**
     * 是否获取Sample
     */
    @ApiModelProperty(value = "是否获取Sample")
    private Boolean withSample;

    /**
     * 是否获取Lot
     */
    @ApiModelProperty(value = "是否获取Lot")
    private Boolean withLot;


}
