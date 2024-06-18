package com.agile.qdmp.standalong.model.dto.integrator.query;

import com.agile.tem.common.core.model.BaseQueryDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * NCR
 *
 * @author hyzh code generator
 * @date 2022-11-28 14:18:46
 */
@Data
@ApiModel(value = "NcrQueryDTO", description = "NCR")
public class NcrQueryDTO extends BaseQueryDTO {
    private static final long serialVersionUID = 1L;
    /**
     * IM Server ID
     */
    @ApiModelProperty(value = "IM Server ID")
    private Long serverId;
    /**
     * GUID
     */
    @ApiModelProperty(value = "GUID")
    private String guid;
    /**
     * jobId
     */
    @ApiModelProperty(value = "jobId")
    private String jobId;
    /**
     * LotId
     */
    @ApiModelProperty(value = "LotId")
    private String LotId;
    /**
     * keyWord
     */
    @ApiModelProperty(value = "keyWord")
    private String keyWord;
    /**
     * Status
     */
    @ApiModelProperty(value = "Status")
    private Integer status;
    /**
     * CreatedByGUID
     */
    @ApiModelProperty(value = "CreatedByGUID")
    private String createdByGuid;
    /**
     * AssignedToGUID
     */
    @ApiModelProperty(value = "AssignedToGUID")
    private String assignedToGuid;
    /**
     * WorkCellGUID
     */
    @ApiModelProperty(value = "WorkCellGUID")
    private String workCellGuid;
    /**
     * InspCenterGUID
     */
    @ApiModelProperty(value = "InspCenterGUID")
    private String inspCenterGuid;
}
