package com.agile.qdmp.standalong.model.dto.integrator.query;

import com.agile.tem.common.core.model.BaseQueryDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * IM_API_RECORD
 *
 * @author hyzh code generator
 * @date 2022-09-19 09:56:55
 */
@Data
@ApiModel(value = "ImApiRecordQueryDTO", description = "IM_API_RECORD")
public class ImApiRecordQueryDTO extends BaseQueryDTO {
    private static final long serialVersionUID = 1L;
    /**
     * 企业id
     */
    @ApiModelProperty(value = "企业id")
    private Long companyId;
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long accountId;

    /**
     * 职员id
     */
    @ApiModelProperty(value = "职员id")
    private Long staffId;

    /**
     * IM Server id
     */
    @ApiModelProperty(value = "IM Server id")
    private Long serverId;

    /**
     * 关键词
     */
    @ApiModelProperty(value = "关键词")
    private String keyWord;
}
