package com.agile.qdmp.standalong.model.dto.integrator.query;

import com.agile.tem.common.core.model.BaseQueryDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * GAGE_CATEGORY
 *
 * @author wenbinglei
 * @date 2022-10-21 14:06:08
 */
@Data
@ApiModel(value = "GageCategoryQueryDTO", description = "GAGE_CATEGORY")
public class GageCategoryQueryDTO extends BaseQueryDTO {
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
     * 类目Code
     */
    @ApiModelProperty(value = "类目Code")
    private String categoryCode;

}
