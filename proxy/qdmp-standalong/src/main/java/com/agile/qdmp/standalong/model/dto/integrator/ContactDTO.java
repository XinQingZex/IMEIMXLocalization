package com.agile.qdmp.standalong.model.dto.integrator;

import com.agile.tem.common.core.model.BaseDTO;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Contacts
 *
 * @author hyzh code generator
 * @date 2022-12-02 11:15:44
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ContactDTO", description = "Contacts")
public class ContactDTO extends BaseDTO {
    private static final long serialVersionUID = 1L;
//    @FiledDesensitization(value = MaskingTypeEnum.FIXED_PHONE)
//    @FiledDesensitization(retainLeft=2, retainRight = 3, padStr = "@", separator = "#")
    /**
     * IM Server ID
     */
    @JsonSerialize(
            using = ToStringSerializer.class
    )
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
     * username
     */
    @ApiModelProperty(value = "username")
    private String username;
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
     * Email
     */
    @ApiModelProperty(value = "Email")
    private String email;
    /**
     * PhotoFileGUID
     */
    @ApiModelProperty(value = "PhotoFileGUID")
    private String photoFileGuid;
    /**
     * ADUserName
     */
    @ApiModelProperty(value = "ADUserName")
    private String adUserName;
    /**
     * EmployeeID
     */
    @ApiModelProperty(value = "EmployeeID")
    private String employeeId;
    /**
     * BarcodeID
     */
    @ApiModelProperty(value = "BarcodeID")
    private String barcodeId;
    /**
     * ERPID
     */
    @ApiModelProperty(value = "ERPID")
    private String erpId;
    /**
     * InspCenter GUID
     */
    @ApiModelProperty(value = "InspCenter GUID")
    private String inspCenterGuid;
    /**
     * InspCenter 名称
     */
    @ApiModelProperty(value = "InspCenter 名称")
    private String inspCenterName;
    /**
     * IM PassWord
     */
    @ApiModelProperty(value = "IM PassWord")
    private String password;
    /**
     * Face base64
     */
    @ApiModelProperty(value = "Face base64")
    private String face;
    /**
     * 特征值
     */
    @ApiModelProperty(value = "特征值")
    private String flag;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Integer updateTime;
}
