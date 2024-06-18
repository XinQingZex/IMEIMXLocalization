package com.agile.qdmp.standalong.model.dto.integrator.query;

import com.agile.tem.common.core.model.BaseQueryDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * DIMENSION_SET
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:41:51
 */
@Data
@ApiModel(value = "DimensionSetQueryDTO", description = "DIMENSION_SET")
public class DimensionSetQueryDTO extends BaseQueryDTO {
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
     * _guid
     */
    @ApiModelProperty(value = "_guid")
    private String guid;
    /**
     * PartGUID
     */
    @ApiModelProperty(value = "PartGUID")
    private String partGuid;
    /**
     * Number
     */
    @ApiModelProperty(value = "Number")
    private Integer number;
    /**
     * Name
     */
    @ApiModelProperty(value = "Name")
    private String name;
    /**
     * Description
     */
    @ApiModelProperty(value = "Description")
    private String description;
    /**
     * Dims
     */
    @ApiModelProperty(value = "Dims")
    private String dims;

}
