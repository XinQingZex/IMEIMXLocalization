package com.agile.qdmp.standalong.model.dto.integrator;

import com.agile.tem.common.core.model.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 带PART、JOB的LOT对象
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:42:11
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "FullLotDTO", description = "FullLotDTO")
public class FullLotDTO extends BaseDTO {
    private static final long serialVersionUID = 1L;
//    @FiledDesensitization(value = MaskingTypeEnum.FIXED_PHONE)
//    @FiledDesensitization(retainLeft=2, retainRight = 3, padStr = "@", separator = "#")


    /**
     * partGuid
     */
    @ApiModelProperty(value = "partGuid")
    private String partGuid;
    /**
     * partName
     */
    @ApiModelProperty(value = "partName")
    private String partName;

    /**
     * partNumber
     */
    @ApiModelProperty(value = "partNumber")
    private String partNumber;

    /**
     * partRevisionLevel
     */
    @ApiModelProperty(value = "partRevisionLevel")
    private String partRevisionLevel;

    /**
     * jobGuid
     */
    @ApiModelProperty(value = "jobGuid")
    private String jobGuid;
    /**
     * jobNumber
     */
    @ApiModelProperty(value = "jobNumber")
    private String jobNumber;

    /**
     * lotGuid
     */
    @ApiModelProperty(value = "lotGuid")
    private String lotGuid;

    /**
     * lotNumber
     */
    @ApiModelProperty(value = "lotNumber")
    private String lotNumber;

}
