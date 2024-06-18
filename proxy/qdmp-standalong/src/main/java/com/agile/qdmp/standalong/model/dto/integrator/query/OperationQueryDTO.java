package com.agile.qdmp.standalong.model.dto.integrator.query;

import com.agile.tem.common.core.model.BaseQueryDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Operation
 *
 * @author wenbinglei
 * @date 2022-10-21 11:47:53
 */
@Data
@ApiModel(value = "OperationDQueryDTO", description = "Operation")
public class OperationQueryDTO extends BaseQueryDTO {
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
     * GUID
     */
    @ApiModelProperty(value = "GUID")
    private String guid;
    /**
     * partId
     */
    @ApiModelProperty(value = "partId")
    private String partId;
    /**
     * WorkCellGUID
     */
    @ApiModelProperty(value = "WorkCellGUID")
    private String workCellGuid;
    /**
     * MachineGUID
     */
    @ApiModelProperty(value = "MachineGUID")
    private String machineGuid;
}
