package com.agile.qdmp.standalong.model.dto.integrator.query;

import com.agile.tem.common.core.model.BaseQueryDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * CharacterDesignator
 *
 * @author wenbinglei
 * @date 2022-10-21 14:06:08
 */
@Data
@ApiModel(value = "CharacterDesignatorQueryDTO", description = "CharacterDesignator")
public class CharacterDesignatorQueryDTO extends BaseQueryDTO {
    private static final long serialVersionUID = 1L;

    /**
     * keyWord
     */
    @ApiModelProperty(value = "keyWord")
    private String keyWord;
    /**
     * IM Server ID
     */
    @ApiModelProperty(value = "IM Server ID")
    private Long serverId;
    /**
     * partGuid
     */
    @ApiModelProperty(value = "partGuid")
    private String partGuid;

}
