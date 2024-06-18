package com.agile.qdmp.standalong.model.entity.integrator;

import com.agile.tem.common.core.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * DIMENSION_SET
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:41:51
 */
@Data
@TableName("im_dimension_set")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "DimensionSet", description = "DIMENSION_SET")
public class DimensionSet extends BaseModel {
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
