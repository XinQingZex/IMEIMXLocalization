package com.agile.qdmp.standalong.model.dto.integrator.query;

import com.agile.tem.common.core.model.BaseQueryDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * LOT
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:42:16
 */
@Data
@ApiModel(value = "LotQueryDTO", description = "LOT")
public class LotQueryDTO extends BaseQueryDTO {
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
     * AQLTableGUID
     */
    @ApiModelProperty(value = "AQLTableGUID")
    private String aqlTableGuid;
    /**
     * AcceptanceStatus
     */
    @ApiModelProperty(value = "AcceptanceStatus")
    private String acceptanceStatus;
    /**
     * BarcodeID
     */
    @ApiModelProperty(value = "BarcodeID")
    private String barcodeId;
    /**
     * DueDate
     */
    @ApiModelProperty(value = "DueDate")
    private LocalDateTime dueDate;
    /**
     * ERPID
     */
    @ApiModelProperty(value = "ERPID")
    private String erpId;
    /**
     * GUID
     */
    @ApiModelProperty(value = "GUID")
    private String guid;
    /**
     * InspectionLevel
     */
    @ApiModelProperty(value = "InspectionLevel")
    private Integer inspectionLevel;
    /**
     * lotId
     */
    @ApiModelProperty(value = "lotId")
    private String lotId;
    /**
     * contactId
     */
    @ApiModelProperty(value = "contactId")
    private String contactId;
    /**
     * PartGUID
     */
    @ApiModelProperty(value = "PartGUID")
    private String partGuid;
    /**
     * QualityLevel
     */
    @ApiModelProperty(value = "QualityLevel")
    private String qualityLevel;
    /**
     * QualityStage
     */
    @ApiModelProperty(value = "QualityStage")
    private Integer qualityStage;
    /**
     * StartDate
     */
    @ApiModelProperty(value = "StartDate")
    private LocalDateTime startDate;
    /**
     * Status
     */
    @ApiModelProperty(value = "Status")
    private Integer status;
    /**
     * 是否获取所属Job
     */
    @ApiModelProperty(value = "是否获取所属Job")
    private Boolean withJob;
    /**
     * 是否获取所属Part
     */
    @ApiModelProperty(value = "是否获取所属Part")
    private Boolean withPart;

    /**
     * JobId
     */
    @ApiModelProperty(value = "JobId")
    private String JobId;
}
