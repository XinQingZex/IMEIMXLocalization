package com.agile.qdmp.standalong.model.dto.integrator.query;

import com.agile.tem.common.core.model.BaseQueryDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Machine
 *
 * @author wenbinglei
 * @date 2022-10-21 11:47:57
 */
@Data
@ApiModel(value = "MachineQueryDTO", description = "Machine")
public class MachineQueryDTO extends BaseQueryDTO {
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
     * WorkCellGUID
     */
    @ApiModelProperty(value = "WorkCellGUID")
    private String workCellGuid;
    /**
     * PlaceGUID
     */
    @ApiModelProperty(value = "PlaceGUID")
    private String machineId;

    /**
     * Status
     */
    @ApiModelProperty(value = "Status")
    private Integer status;

    /**
     * ERPID
     */
    @ApiModelProperty(value = "ERPID")
    private String erpId;
    /**
     * barcodeID
     */
    @ApiModelProperty(value = "barcodeID")
    private String barcodeId;
}
