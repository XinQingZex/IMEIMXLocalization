package com.agile.qdmp.standalong.model.dto.integrator;

import com.agile.tem.common.core.model.BaseDTO;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * RESULT
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:42:06
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ResultDTO", description = "RESULT")
public class ResultDTO extends BaseDTO {
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
     * guid
     */
    @ApiModelProperty(value = "guid")
    private String guid;
    /**
     * partGuid
     */
    @ApiModelProperty(value = "partGuid")
    private String partGuid;
    /**
     * PartName
     */
    @ApiModelProperty(value="PartName")
    private String partName;
    /**
     * PartNumber
     */
    @ApiModelProperty(value="PartNumber")
    private String partNumber;
    /**
     * PartRevisionLevel
     */
    @ApiModelProperty(value="PartRevisionLevel")
    private String partRevisionLevel;
    /**
     * JobGUID
     */
    @ApiModelProperty(value="JobGUID")
    private String jobGuid;
    /**
     * JobNumber
     */
    @ApiModelProperty(value="JobNumber")
    private String jobNumber;
    /**
     * JobTitle
     */
    @ApiModelProperty(value="JobTitle")
    private String jobTitle;
    /**
     * JobRevision
     */
    @ApiModelProperty(value="JobRevision")
    private String jobRevision;
    /**
     * LotGUID
     */
    @ApiModelProperty(value="LotGUID")
    private String lotGuid;
    /**
     * LotNumber
     */
    @ApiModelProperty(value="LotNumber")
    private String lotNumber;
    /**
     * SampleGUID
     */
    @ApiModelProperty(value="SampleGUID")
    private String sampleGuid;
    /**
     * SampleSerialNumber
     */
    @ApiModelProperty(value="SampleSerialNumber")
    private String sampleSerialNumber;
    /**
     * Dim_guid
     */
    @ApiModelProperty(value="Dim_guid")
    private String dimGuid;
    /**
     * DimNo
     */
    @ApiModelProperty(value="DimNo")
    private String dimNo;
    /**
     * GageGUID
     */
    @ApiModelProperty(value = "GageGUID")
    private String gageGuid;
    /**
     * GageName
     */
    @ApiModelProperty(value="GageName")
    private String gageName;
    /**
     * MeasuredByGUID
     */
    @ApiModelProperty(value="MeasuredByGUID")
    private String measuredByGuid;
    /**
     * MeasuredByName
     */
    @ApiModelProperty(value="MeasuredByName")
    private String measuredByName;
    /**
     * ResNo
     */
    @ApiModelProperty(value = "ResNo")
    private String resNo;
    /**
     * Nominal
     */
    @ApiModelProperty(value="Nominal")
    private BigDecimal nominal;
    /**
     * Data
     */
    @ApiModelProperty(value = "Data")
    private BigDecimal data;
    /**
     * UpperTol
     */
    @ApiModelProperty(value = "UpperTol")
    private BigDecimal upperTol;
    /**
     * LowerTol
     */
    @ApiModelProperty(value = "LowerTol")
    private BigDecimal lowerTol;
    /**
     * Deviation
     */
    @ApiModelProperty(value = "Deviation")
    private BigDecimal deviation;
    /**
     * Error
     */
    @ApiModelProperty(value = "Error")
    private BigDecimal error;
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
     * InspectedDate
     */
    @ApiModelProperty(value = "InspectedDate")
    private LocalDateTime inspectedDate;
    /**
     * Comment
     */
    @ApiModelProperty(value = "Comment")
    private String comment;
    /**
     * NCRGUID
     */
    @ApiModelProperty(value = "NCRGUID")
    private String ncrGuid;
    /**
     * 特征值
     */
    @ApiModelProperty(value = "特征值")
    private String flag;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Integer updateTime;

    /**
     * Gage
     */
    @ApiModelProperty(value = "Gage")
    private GageDTO gage;

    /**
     * sample
     */
    @ApiModelProperty(value = "sample")
    private SampleDTO sample;
}
