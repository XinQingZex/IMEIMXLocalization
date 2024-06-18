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
import java.util.List;

/**
 * PART
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:42:11
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "PartDTO", description = "PART")
public class PartDTO extends BaseDTO {
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
     * barcodeID
     */
    @ApiModelProperty(value = "barcodeID")
    private String barcodeId;
    /**
     * CustomerGUID
     */
    @ApiModelProperty(value = "CustomerGUID")
    private String customerGuid;
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
     * IsArchived
     */
    @ApiModelProperty(value = "IsArchived")
    private Integer isArchived;
    /**
     * IsDeleted
     */
    @ApiModelProperty(value = "IsDeleted")
    private Integer isDeleted;
    /**
     * LastModificationDate
     */
    @ApiModelProperty(value = "LastModificationDate")
    private LocalDateTime lastModificationDate;
    /**
     * PartComments
     */
    @ApiModelProperty(value = "PartComments")
    private String partComments;
    /**
     * PartName
     */
    @ApiModelProperty(value = "PartName")
    private String partName;
    /**
     * PartNumber
     */
    @ApiModelProperty(value = "PartNumber")
    private String partNumber;
    /**
     * PartRevisionLevel
     */
    @ApiModelProperty(value = "PartRevisionLevel")
    private String partRevisionLevel;
    /**
     * 特征值
     */
    @ApiModelProperty(value = "特征值")
    private String flag;
    /**
     * 是否需要同步子数据 false待同步 true已同步
     */
    @ApiModelProperty(value = "false待同步 true已同步")
    private Boolean handleState;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Integer updateTime;

    /**
     * JOB数量
     */
    @ApiModelProperty(value = "JOB数量")
    private Integer jobTotal;
    /**
     * Lot数量
     */
    @ApiModelProperty(value = "Lot数量")
    private Integer lotTotal;
    /**
     * Sample数量
     */
    @ApiModelProperty(value = "Sample数量")
    private Integer sampleTotal;
    /**
     * 企业名称
     */
    @ApiModelProperty(value = "企业名称")
    private String customerName;

    /**
     * 企业信息
     */
    @ApiModelProperty(value = "企业信息")
    private CompanyDTO company;

    /**
     * 图纸信息
     */
    @ApiModelProperty(value = "图纸信息")
    private List<DrawingDTO> drawings;
}
