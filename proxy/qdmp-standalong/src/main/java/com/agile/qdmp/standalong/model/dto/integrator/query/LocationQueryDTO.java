package com.agile.qdmp.standalong.model.dto.integrator.query;

import com.agile.tem.common.core.model.BaseQueryDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * LOCATION
 *
 * @author wenbinglei
 * @date 2022-10-21 11:48:01
 */
@Data
@ApiModel(value = "LocationQueryDTO", description = "LOCATION")
public class LocationQueryDTO extends BaseQueryDTO {
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
     * GUID
     */
    @ApiModelProperty(value = "GUID")
    private String guid;
    /**
     * CompanyGUID
     */
    @ApiModelProperty(value = "CompanyGUID")
    private String companyGuid;
    /**
     * Type
     */
    @ApiModelProperty(value = "Type")
    private Integer type;
    /**
     * Code
     */
    @ApiModelProperty(value = "Code")
    private String code;
    /**
     * ERPID
     */
    @ApiModelProperty(value = "ERPID")
    private String erpId;
    /**
     * barcodeID
     */
    @ApiModelProperty(value = "barcodeID")
    private String barcodeId;
    /**
     * 特征值
     */
    @ApiModelProperty(value = "特征值")
    private String flag;
}
