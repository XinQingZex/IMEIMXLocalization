package com.agile.qdmp.standalong.model.dto.integrator.query;

import com.agile.tem.common.core.model.BaseQueryDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Contacts
 *
 * @author hyzh code generator
 * @date 2022-12-02 11:15:44
 */
@Data
@ApiModel(value = "ContactQueryDTO", description = "Contacts")
public class ContactQueryDTO extends BaseQueryDTO {
    private static final long serialVersionUID = 1L;

    /**
     * IM Server ID
     */
    @ApiModelProperty(value = "IM Server ID")
    private Long serverId;
    /**
     * ID
     */
    @ApiModelProperty(value = "ID")
    private String guid;
    /**
     * CompanyGUID
     */
    @ApiModelProperty(value = "CompanyGUID")
    private String companyGuid;
    /**
     * FirstName
     */
    @ApiModelProperty(value = "FirstName")
    private String firstName;
    /**
     * LastName
     */
    @ApiModelProperty(value = "LastName")
    private String lastName;
    /**
     * keyWord
     */
    @ApiModelProperty(value = "keyWord")
    private String keyWord;
    /**
     * InspCenter GUID
     */
    @ApiModelProperty(value = "InspCenter GUID")
    private String inspCenterGuid;
}
