package com.agile.qdmp.standalong.model.entity.integrator;

import com.agile.tem.common.core.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * COMPANY
 *
 * @author wenbinglei
 * @date 2022-10-17 14:25:59
 */
@Data
@TableName("im_company")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Company", description = "COMPANY")
public class Company extends BaseModel {
    private static final long serialVersionUID = 1L;

    /**
     * 企业id
     */
    @ApiModelProperty(value = "企业id")
    private Long companyIdSys;
    /**
     * 企业名称
     */
    @ApiModelProperty(value = "企业名称")
    private String companyName;
    /**
     * IM Server ID
     */
    @ApiModelProperty(value = "IM Server ID")
    private Long serverId;
    /**
     * IM Server 名称
     */
    @ApiModelProperty(value = "IM Server 名称")
    private String serverName;
    /**
     * GUID
     */
    @ApiModelProperty(value = "GUID")
    private String guid;
    /**
     * AccountType
     */
    @ApiModelProperty(value = "AccountType")
    private Integer accountType;
    /**
     * AccountTypeText
     */
    @ApiModelProperty(value = "AccountTypeText")
    private String accountTypeText;
    /**
     * Name
     */
    @ApiModelProperty(value = "Name")
    private String name;
    /**
     * ParentCompanyGUID
     */
    @ApiModelProperty(value = "ParentCompanyGUID")
    private String parentCompanyGuid;
    /**
     * CompanyID
     */
    @ApiModelProperty(value = "CompanyID")
    private String companyId;
    /**
     * Fax
     */
    @ApiModelProperty(value = "Fax")
    private String fax;
    /**
     * Email
     */
    @ApiModelProperty(value = "Email")
    private String email;
    /**
     * WebSite
     */
    @ApiModelProperty(value = "WebSite")
    private String website;
    /**
     * Description
     */
    @ApiModelProperty(value = "Description")
    private String description;
    /**
     * ERPID
     */
    @ApiModelProperty(value = "ERPID")
    private String erpId;
    /**
     * BarcodeID
     */
    @ApiModelProperty(value = "BarcodeID")
    private String barcodeId;
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
