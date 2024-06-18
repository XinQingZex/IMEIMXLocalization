package com.agile.qdmp.standalong.model.dto.integrator.query;

import com.agile.tem.common.core.model.BaseQueryDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ITEM
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:42:11
 */
@Data
@ApiModel(value = "ItemQueryDTO", description = "ITEM")
public class ItemQueryDTO extends BaseQueryDTO {
    private static final long serialVersionUID = 1L;

    /**
     * keyWord
     */
    @ApiModelProperty(value = "keyWord")
    private String keyWord;

    /**
     * withPartial
     */
    @ApiModelProperty(value = "withPartial")
    private Boolean withPartial;

    /**
     * IM Server ID
     */
    @ApiModelProperty(value = "IM Server ID")
    private Long serverId;
}
