package com.agile.qdmp.standalong.model.dto.integrator.query;

import com.agile.tem.common.core.model.BaseQueryDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * WorkCell
 *
 * @author wenbinglei
 * @date 2022-10-21 11:48:07
 */
@Data
@ApiModel(value = "WorkCellQueryDTO", description = "WorkCell")
public class WorkCellQueryDTO extends BaseQueryDTO {
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
     * CompanyGUID
     */
    @ApiModelProperty(value = "CompanyGUID")
    private String companyGuid;
    /**
     * LocationGUID
     */
    @ApiModelProperty(value = "LocationGUID")
    private String locationGuid;
    /**
     * PlaceGUID
     */
    @ApiModelProperty(value = "PlaceGUID")
    private String placeGuid;
    /**
     * WorkCellID
     */
    @ApiModelProperty(value = "WorkCellID")
    private String workCellId;
}
