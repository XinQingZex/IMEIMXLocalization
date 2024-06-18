package com.agile.qdmp.standalong.model.entity.integrator;

import com.agile.tem.common.core.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * LOCATION
 *
 * @author wenbinglei
 * @date 2022-10-21 11:48:01
 */
@Data
@TableName("im_location")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Location", description = "LOCATION")
public class Location extends BaseModel {
    private static final long serialVersionUID = 1L;
    /**
     * 企业id
     */
    @ApiModelProperty(value = "企业id")
    private Long companyId;
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
     * TypeText
     */
    @ApiModelProperty(value = "TypeText")
    private String typeText;
    /**
     * Code
     */
    @ApiModelProperty(value = "Code")
    private String code;
    /**
     * Name
     */
    @ApiModelProperty(value = "Name")
    private String name;
    /**
     * Phone
     */
    @ApiModelProperty(value = "Phone")
    private String phone;
    /**
     * Email
     */
    @ApiModelProperty(value = "Email")
    private String email;
    /**
     * Website
     */
    @ApiModelProperty(value = "Website")
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
     * barcodeID
     */
    @ApiModelProperty(value = "barcodeID")
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
