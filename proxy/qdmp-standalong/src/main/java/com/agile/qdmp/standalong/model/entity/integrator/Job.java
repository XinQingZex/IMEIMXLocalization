package com.agile.qdmp.standalong.model.entity.integrator;

import com.agile.tem.common.core.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * JOB
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:41:43
 */
@Data
@TableName("im_job")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Job", description = "JOB")
public class Job extends BaseModel {
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
     * AQLMode
     */
    @ApiModelProperty(value = "AQLMode")
    private Integer aqlMode;
    /**
     * AQLModeText
     */
    @ApiModelProperty(value = "AQLModeText")
    private String aqlModeText;
    /**
     * AQLTableGUID
     */
    @ApiModelProperty(value = "AQLTableGUID")
    private String aqlTableGuid;
    /**
     * ActivationDate
     */
    @ApiModelProperty(value = "ActivationDate")
    private LocalDateTime activationDate;
    /**
     * BarcodeID
     */
    @ApiModelProperty(value = "BarcodeID")
    private String barcodeId;
    /**
     * DeliveryDate
     */
    @ApiModelProperty(value = "DeliveryDate")
    private LocalDateTime deliveryDate;
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
     * InspectionLevelText
     */
    @ApiModelProperty(value = "InspectionLevelText")
    private String inspectionLevelText;
    /**
     * Number
     */
    @ApiModelProperty(value = "Number")
    private String number;
    /**
     * PartDeleted
     */
    @ApiModelProperty(value = "PartDeleted")
    private Integer partDeleted;
    /**
     * PartGUID
     */
    @ApiModelProperty(value = "PartGUID")
    private String partGuid;
    /**
     * Quantity
     */
    @ApiModelProperty(value = "Quantity")
    private Integer quantity;
    /**
     * Revision
     */
    @ApiModelProperty(value = "Revision")
    private String revision;
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
     * Title
     */
    @ApiModelProperty(value = "Title")
    private String title;
    /**
     * TotalFailedLots
     */
    @ApiModelProperty(value = "TotalFailedLots")
    private Integer totalFailedLots;
    /**
     * TotalFailedSamples
     */
    @ApiModelProperty(value = "TotalFailedSamples")
    private Integer totalFailedSamples;
    /**
     * TotalLots
     */
    @ApiModelProperty(value = "TotalLots")
    private Integer totalLots;
    /**
     * TotalSamples
     */
    @ApiModelProperty(value = "TotalSamples")
    private Integer totalSamples;
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
     * 所属企业GUID
     */
    @ApiModelProperty(value = "所属企业GUID")
    private String customerGuid;
    /**
     * 所属零件名称
     */
    @ApiModelProperty(value = "所属零件名称")
    private String partName;
    /**
     * 所属零件编号
     */
    @ApiModelProperty(value = "所属零件编号")
    private String partNumber;
    /**
     * PartRevision
     */
    @ApiModelProperty(value = "PartRevision")
    private String partRevision;

    /**
     * aql抽样方案Name
     */
    @ApiModelProperty(value = "aql抽样方案Name")
    private String aqlTableName;
}
