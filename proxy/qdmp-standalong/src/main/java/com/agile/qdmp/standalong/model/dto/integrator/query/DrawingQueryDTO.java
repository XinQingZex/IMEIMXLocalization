package com.agile.qdmp.standalong.model.dto.integrator.query;

import com.agile.tem.common.core.model.BaseQueryDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * DRAWING
 *
 * @author wenbinglei
 * @date 2022-10-22 15:03:30
 */
@Data
@ApiModel(value = "DrawingDQueryDTO", description = "DRAWING")
public class DrawingQueryDTO extends BaseQueryDTO {
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
     * 所属partGuid
     */
    @ApiModelProperty(value = "所属partGuid")
    private String partGuid;
    /**
     * DimsCount
     */
    @ApiModelProperty(value = "DimsCount")
    private Integer dimsCount;
    /**
     * DrawingFile
     */
    @ApiModelProperty(value = "DrawingFile")
    private String drawingFile;
    /**
     * GUID
     */
    @ApiModelProperty(value = "GUID")
    private String guid;
}
