package com.agile.qdmp.standalong.model.dto.integrator.query;

import com.agile.tem.common.core.model.BaseQueryDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * COMPANY
 *
 * @author wenbinglei
 * @date 2022-10-17 14:25:59
 */
@Data
@ApiModel(value = "CompanyQueryDTO", description = "COMPANY")
public class CompanyQueryDTO extends BaseQueryDTO {
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
    private Long companyIdSys;
    /**
     * IM Server ID
     */
    @ApiModelProperty(value = "IM Server ID")
    private Long serverId;
    /**
     * AccountType
     */
    @ApiModelProperty(value = "AccountType")
    private Integer accountType;
    /**
     * ParentCompanyGUID
     */
    @ApiModelProperty(value = "ParentCompanyGUID")
    private String parentCompanyGuid;
    /**
     * GUID
     */
    @ApiModelProperty(value = "GUID")
    private String guid;
}
