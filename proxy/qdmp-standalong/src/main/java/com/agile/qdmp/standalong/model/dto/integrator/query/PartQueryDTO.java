package com.agile.qdmp.standalong.model.dto.integrator.query;

import com.agile.tem.common.core.model.BaseQueryDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * PART
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:42:11
 */
@Data
@ApiModel(value = "PartQueryDTO", description = "PART")
public class PartQueryDTO extends BaseQueryDTO {
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
     * customerId
     */
    @ApiModelProperty(value = "customerId")
    private String customerId;

    /**
     * projectId
     */
    @ApiModelProperty(value = "projectId")
    private String projectId;

    /**
     * GUID
     */
    @ApiModelProperty(value = "GUID")
    private String guid;

    /**
     * 是否获取企业
     */
    @ApiModelProperty(value = "是否获取企业")
    private Boolean withCompany;

    /**
     * 是否获取图纸
     */
    @ApiModelProperty(value = "是否获取图纸")
    private Boolean withDrawing;

    /**
     * inspCenterGuid
     */
    @ApiModelProperty(value = "inspCenterGuid")
    private String inspCenterGuid;
}
