package com.agile.qdmp.standalong.model.dto.integrator;

import com.agile.tem.common.core.model.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 搜索对象 包含Part和Job
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:42:11
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ItemDTO", description = "Item")
public class ItemDTO extends BaseDTO {
    private static final long serialVersionUID = 1L;
//    @FiledDesensitization(value = MaskingTypeEnum.FIXED_PHONE)
//    @FiledDesensitization(retainLeft=2, retainRight = 3, padStr = "@", separator = "#")

    /**
     * itemId
     */
    @ApiModelProperty(value = "itemId")
    private String itemId;
    /**
     * itemName
     */
    @ApiModelProperty(value = "itemName")
    private String itemName;
    /**
     * itemType
     */
    @ApiModelProperty(value = "itemType")
    private String itemType;
    /**
     * itemDescription
     */
    @ApiModelProperty(value = "itemDescription")
    private String itemDescription;
    /**
     * partNumber
     */
    @ApiModelProperty(value = "partNumber")
    private String partNumber;
    /**
     * jobNumber
     */
    @ApiModelProperty(value = "jobNumber")
    private String jobNumber;

    /**
     * partGuid
     */
    @ApiModelProperty(value = "partGuid")
    private String partGuid;
    /**
     * jobGuid
     */
    @ApiModelProperty(value = "jobGuid")
    private String jobGuid;
    /**
     * lotGuid
     */
    @ApiModelProperty(value = "lotGuid")
    private String lotGuid;
    /**
     * sampleGuid
     */
    @ApiModelProperty(value = "sampleGuid")
    private String sampleGuid;

    /**
     * partRevisionLevel
     */
    @ApiModelProperty(value = "partRevisionLevel")
    private String partRevisionLevel;
    /**
     * drawingRevisionLevel
     */
    @ApiModelProperty(value = "drawingRevisionLevel")
    private String drawingRevisionLevel;
}
