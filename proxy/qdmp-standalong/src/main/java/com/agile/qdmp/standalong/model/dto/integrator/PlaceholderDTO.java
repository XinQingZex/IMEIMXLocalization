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

/**
 * Placeholder
 *
 * @author wenbinglei
 * @date 2022-10-21 11:48:14
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PlaceholderDTO", description = "Placeholder")
public class PlaceholderDTO extends BaseDTO {
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
     * ResNo
     */
    @ApiModelProperty(value = "ResNo")
    private String resNo;
    /**
     * Nominal
     */
    @ApiModelProperty(value = "Nominal")
    private BigDecimal nominal;
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
     * 特征值
     */
    @ApiModelProperty(value = "特征值")
    private String flag;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Integer updateTime;
}
