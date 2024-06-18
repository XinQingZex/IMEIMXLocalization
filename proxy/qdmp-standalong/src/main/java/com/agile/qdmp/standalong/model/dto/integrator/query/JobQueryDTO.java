package com.agile.qdmp.standalong.model.dto.integrator.query;

import com.agile.tem.common.core.model.BaseQueryDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * JOB
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:41:43
 */
@Data
@ApiModel(value = "JobQueryDTO", description = "JOB")
public class JobQueryDTO extends BaseQueryDTO {
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
     * AQLMode
     */
    @ApiModelProperty(value = "AQLMode")
    private Integer aqlMode;
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
     * jobId
     */
    @ApiModelProperty(value = "jobId")
    private String jobId;
    /**
     * Status
     */
    @ApiModelProperty(value = "Status")
    private Integer status;
    /**
     * 是否获取所属Part
     */
    @ApiModelProperty(value = "是否获取所属Part")
    private Boolean withPart;

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
}
