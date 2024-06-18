package com.agile.qdmp.standalong.model.dto.integrator;

import com.agile.tem.common.core.model.BaseDTO;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * NCR
 *
 * @author hyzh code generator
 * @date 2022-11-28 14:18:46
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "NcrDTO", description = "NCR")
public class NcrDTO extends BaseDTO {
    private static final long serialVersionUID = 1L;
//    @FiledDesensitization(value = MaskingTypeEnum.FIXED_PHONE)
//    @FiledDesensitization(retainLeft=2, retainRight = 3, padStr = "@", separator = "#")

    /**
     * 企业id
     */
    @ApiModelProperty(value = "企业id")
    @JsonSerialize(
            using = ToStringSerializer.class
    )
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
    @JsonSerialize(
            using = ToStringSerializer.class
    )
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
     * JobGUID
     */
    @ApiModelProperty(value = "JobGUID")
    private String jobGuid;
    /**
     * JobNumber
     */
    @ApiModelProperty(value = "JobNumber")
    private String jobNumber;
    /**
     * LotGUID
     */
    @ApiModelProperty(value = "LotGUID")
    private String lotGuid;
    /**
     * LotNumber
     */
    @ApiModelProperty(value = "LotNumber")
    private String lotNumber;
    /**
     * Number
     */
    @ApiModelProperty(value = "Number")
    private String number;
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
     * CreationDate
     */
    @ApiModelProperty(value = "CreationDate")
    private LocalDateTime creationDate;
    /**
     * CreatedByGUID
     */
    @ApiModelProperty(value = "CreatedByGUID")
    private String createdByGuid;
    /**
     * CreatedByName
     */
    @ApiModelProperty(value = "CreatedByName")
    private String createdByName;
    /**
     * ResponseDate
     */
    @ApiModelProperty(value = "ResponseDate")
    private LocalDateTime responseDate;
    /**
     * AssignedToGUID
     */
    @ApiModelProperty(value = "AssignedToGUID")
    private String assignedToGuid;
    /**
     * AssignedToName
     */
    @ApiModelProperty(value = "AssignedToName")
    private String assignedToName;
    /**
     * WorkCellGUID
     */
    @ApiModelProperty(value = "WorkCellGUID")
    private String workCellGuid;
    /**
     * WorkCellName
     */
    @ApiModelProperty(value = "WorkCellName")
    private String workCellName;
    /**
     * InspCenterGUID
     */
    @ApiModelProperty(value = "InspCenterGUID")
    private String inspCenterGuid;
    /**
     * InspCenterName
     */
    @ApiModelProperty(value = "InspCenterName")
    private String inspCenterName;
    /**
     * BarcodeID
     */
    @ApiModelProperty(value = "BarcodeID")
    private String barcodeId;
    /**
     * ERPID
     */
    @ApiModelProperty(value = "ERPID")
    private String erpId;
    /**
     * Comments
     */
    @ApiModelProperty(value = "Comments")
    private String comments;
    /**
     * DefectCodesCount
     */
    @ApiModelProperty(value = "DefectCodesCount")
    private String defectCodesCount;
    /**
     * ResultsCount
     */
    @ApiModelProperty(value = "ResultsCount")
    private String resultsCount;
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
     * results
     */
    private List<NcrResultDTO> results;
}
