package com.agile.qdmp.standalong.model.entity.integrator;

import com.agile.tem.common.core.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * NCR
 *
 * @author hyzh code generator
 * @date 2022-11-28 14:18:46
 */
@Data
@TableName("im_ncr")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Ncr", description = "NCR")
public class Ncr extends BaseModel {
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
}
