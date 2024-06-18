package com.agile.qdmp.standalong.model.dto.integrator.query;

import com.agile.tem.common.core.model.BaseQueryDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Procedure
 *
 * @author wenbinglei
 * @date 2022-10-17 14:25:46
 */
@Data
@ApiModel(value = "ProcedureQueryDTO", description = "Procedure")
public class ProcedureQueryDTO extends BaseQueryDTO {
    private static final long serialVersionUID = 1L;
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
     * GUID
     */
    @ApiModelProperty(value = "GUID")
    private String guid;
    /**
     * PartGUID
     */
    @ApiModelProperty(value = "PartGUID")
    private String partGuid;
    /**
     * InspCenterGUID
     */
    @ApiModelProperty(value = "InspCenterGUID")
    private String inspCenterGuid;

    /**
     * Status
     */
    @ApiModelProperty(value = "Status")
    private Integer status;
}
