package com.agile.qdmp.standalong.model.entity.integrator;

import com.agile.tem.common.core.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Contacts
 *
 * @author hyzh code generator
 * @date 2022-12-02 11:15:44
 */
@Data
@TableName("im_contact")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Contact", description = "Contacts")
public class Contact extends BaseModel {
    private static final long serialVersionUID = 1L;
    /**
     * IM Server ID
     */
    @ApiModelProperty(value="IM Server ID")
    private Long serverId;
    /**
     * ID
     */
    @ApiModelProperty(value="ID")
    private String guid;
    /**
     * CompanyGUID
     */
    @ApiModelProperty(value="CompanyGUID")
    private String companyGuid;
    /**
     * username
     */
    @ApiModelProperty(value="username")
    private String username;
    /**
     * FirstName
     */
    @ApiModelProperty(value="FirstName")
    private String firstName;
    /**
     * LastName
     */
    @ApiModelProperty(value="LastName")
    private String lastName;
    /**
     * Email
     */
    @ApiModelProperty(value="Email")
    private String email;
    /**
     * PhotoFileGUID
     */
    @ApiModelProperty(value="PhotoFileGUID")
    private String photoFileGuid;
    /**
     * ADUserName
     */
    @ApiModelProperty(value="ADUserName")
    private String adUserName;
    /**
     * EmployeeID
     */
    @ApiModelProperty(value="EmployeeID")
    private String employeeId;
    /**
     * BarcodeID
     */
    @ApiModelProperty(value="BarcodeID")
    private String barcodeId;
    /**
     * ERPID
     */
    @ApiModelProperty(value="ERPID")
    private String erpId;
    /**
     * InspCenter GUID
     */
    @ApiModelProperty(value="InspCenter GUID")
    private String inspCenterGuid;
    /**
     * InspCenter 名称
     */
    @ApiModelProperty(value="InspCenter 名称")
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
    @ApiModelProperty(value="特征值")
    private String flag;
    /**
     * 更新时间
     */
    @ApiModelProperty(value="更新时间")
    private Integer updateTime;
    }
