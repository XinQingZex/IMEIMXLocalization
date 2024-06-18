package com.agile.qdmp.standalong.model.dto.integrator.query;

import com.agile.tem.common.core.model.BaseQueryDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * GAGE
 *
 * @author wenbinglei
 * @date 2022-10-17 14:25:55
 */
@Data
@ApiModel(value = "GageQueryDTO", description = "GAGE")
public class GageQueryDTO extends BaseQueryDTO {
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
     * GageID
     */
    @ApiModelProperty(value = "GageID")
    private String gageId;
    /**
     * categoryId
     */
    @ApiModelProperty(value="categoryId")
    private String categoryId;
    /**
     * Status
     */
    @ApiModelProperty(value="Status")
    private Integer status;

    /**
     * ExpStatus
     */
    @ApiModelProperty(value="ExpStatus")
    private Integer expStatus;
}
