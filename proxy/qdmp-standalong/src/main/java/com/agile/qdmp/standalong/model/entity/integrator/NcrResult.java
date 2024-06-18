package com.agile.qdmp.standalong.model.entity.integrator;

import com.agile.tem.common.core.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * NCR_RESULT
 *
 * @author hyzh code generator
 * @date 2022-12-18 14:33:57
 */
@Data
@TableName("im_ncr_result")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "NcrResult", description = "NCR_RESULT")
public class NcrResult extends BaseModel {
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
     * DefectCode
     */
    @ApiModelProperty(value = "DefectCode")
    private String defectCode;
    /**
     * DefectValue
     */
    @ApiModelProperty(value = "DefectValue")
    private Integer defectValue;
    /**
     * DefectLocation
     */
    @ApiModelProperty(value = "DefectLocation")
    private String defectLocation;
    /**
     * GageGUID
     */
    @ApiModelProperty(value = "GageGUID")
    private String gageGuid;
    /**
     * GageTitle
     */
    @ApiModelProperty(value = "GageTitle")
    private String gageTitle;
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
    /**
     * TolType
     */
    @ApiModelProperty(value = "TolType")
    private String tolType;
    /**
     * TolClass
     */
    @ApiModelProperty(value = "TolClass")
    private String tolClass;
    /**
     * Nominal
     */
    @ApiModelProperty(value = "Nominal")
    private BigDecimal nominal;
    /**
     * LowerTol
     */
    @ApiModelProperty(value = "LowerTol")
    private BigDecimal lowerTol;
    /**
     * UpperTol
     */
    @ApiModelProperty(value = "UpperTol")
    private BigDecimal upperTol;
    /**
     * Note
     */
    @ApiModelProperty(value = "Note")
    private String note;
    /**
     * Feature1
     */
    @ApiModelProperty(value = "Feature1")
    private String feature1;
    /**
     * Feature2
     */
    @ApiModelProperty(value = "Feature2")
    private String feature2;
    /**
     * BalloonLocation
     */
    @ApiModelProperty(value = "BalloonLocation")
    private String balloonLocation;
    /**
     * Designator
     */
    @ApiModelProperty(value = "Designator")
    private String designator;
    /**
     * SamplingMode
     */
    @ApiModelProperty(value = "SamplingMode")
    private String samplingMode;
    /**
     * ToolCategory
     */
    @ApiModelProperty(value = "ToolCategory")
    private String toolCategory;
    /**
     * ProcIndex
     */
    @ApiModelProperty(value = "ProcIndex")
    private Integer procIndex;
    /**
     * ProcCode
     */
    @ApiModelProperty(value = "ProcCode")
    private String procCode;
    /**
     * ProcName
     */
    @ApiModelProperty(value = "ProcName")
    private String procName;
    /**
     * ProcComments
     */
    @ApiModelProperty(value = "ProcComments")
    private String procComments;
    /**
     * ProcExtID
     */
    @ApiModelProperty(value = "ProcExtID")
    private String procExtId;
    /**
     * ProcBarcode
     */
    @ApiModelProperty(value = "ProcBarcode")
    private String procBarcode;
    /**
     * ICNumber
     */
    @ApiModelProperty(value = "ICNumber")
    private String icNumber;
    /**
     * ICName
     */
    @ApiModelProperty(value = "ICName")
    private String icName;
    /**
     * ICComments
     */
    @ApiModelProperty(value = "ICComments")
    private String icComments;
    /**
     * ICExtID
     */
    @ApiModelProperty(value = "ICExtID")
    private String icExtId;
    /**
     * ICBarcode
     */
    @ApiModelProperty(value = "ICBarcode")
    private String icBarcode;
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
