package com.agile.qdmp.standalong.model.dto.integrator;

import com.agile.tem.common.core.model.BaseDTO;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * LOT
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:42:16
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "LotDTO", description = "LOT")
public class LotDTO extends BaseDTO {
    private static final long serialVersionUID = 1L;
//    @FiledDesensitization(value = MaskingTypeEnum.FIXED_PHONE)
//    @FiledDesensitization(retainLeft=2, retainRight = 3, padStr = "@", separator = "#")


    /**
     * 企业id
     */
    @ApiModelProperty(value = "企业id")
    @JsonSerialize(using= ToStringSerializer.class)
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
    @JsonSerialize(using=ToStringSerializer.class)
    private Long serverId;
    /**
     * IM Server 名称
     */
    @ApiModelProperty(value = "IM Server 名称")
    private String serverName;
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
     * CodeLetter
     */
    @ApiModelProperty(value = "CodeLetter")
    private String codeLetter;
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
     * HoursPerShift
     */
    @ApiModelProperty(value = "HoursPerShift")
    private Integer hoursPerShift;
    /**
     * InspectionLevel
     */
    @ApiModelProperty(value = "InspectionLevel")
    private Integer inspectionLevel;
    /**
     * InspectionLevelText
     */
    @ApiModelProperty(value = "InspectionLevelText")
    private String inspectionLevelText;
    /**
     * JobGUID
     */
    @ApiModelProperty(value = "JobGUID")
    private String jobGuid;
    /**
     * Number
     */
    @ApiModelProperty(value = "Number")
    private String number;
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
     * QualityStageText
     */
    @ApiModelProperty(value = "QualityStageText")
    private String qualityStageText;
    /**
     * SampleSize
     */
    @ApiModelProperty(value = "SampleSize")
    private Integer sampleSize;
    /**
     * SamplesPerHour
     */
    @ApiModelProperty(value = "SamplesPerHour")
    private Integer samplesPerHour;
    /**
     * Size
     */
    @ApiModelProperty(value = "Size")
    private Integer size;
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
     * StatusText
     */
    @ApiModelProperty(value = "StatusText")
    private String statusText;
    /**
     * TotalFailedSamples
     */
    @ApiModelProperty(value = "TotalFailedSamples")
    private Integer totalFailedSamples;
    /**
     * TotalSamples
     */
    @ApiModelProperty(value = "TotalSamples")
    private Integer totalSamples;
    /**
     * JobDTO
     */
    @ApiModelProperty(value = "JobDTO")
    private JobDTO job;
    /**
     * PartDTO
     */
    @ApiModelProperty(value = "PartDTO")
    private PartDTO part;
    /**
     * 特征值
     */
    @ApiModelProperty(value = "特征值")
    private String flag;
    /**
     * 是否需要同步子数据 false待同步 true已同步
     */
    @ApiModelProperty(value = "false待同步 true已同步")
    private String handleState;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Integer updateTime;
    /**
     * aql抽样方案Name
     */
    @ApiModelProperty(value = "aql抽样方案Name")
    private String aqlTableName;
}
