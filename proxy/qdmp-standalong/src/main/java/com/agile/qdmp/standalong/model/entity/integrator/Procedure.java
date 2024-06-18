package com.agile.qdmp.standalong.model.entity.integrator;

import com.agile.tem.common.core.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Procedure
 *
 * @author wenbinglei
 * @date 2022-10-17 14:25:46
 */
@Data
@TableName("im_procedure")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Procedure", description = "Procedure")
public class Procedure extends BaseModel {
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
     * PartGUID
     */
    @ApiModelProperty(value = "PartGUID")
    private String partGuid;
    /**
     * Number
     */
    @ApiModelProperty(value = "Number")
    private Integer number;
    /**
     * Code
     */
    @ApiModelProperty(value = "Code")
    private String code;
    /**
     * Title
     */
    @ApiModelProperty(value = "Title")
    private String title;
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
     * Status
     */
    @ApiModelProperty(value = "Status")
    private Integer status;
    /**
     * StatusText
     */
    @ApiModelProperty(value = "StatusText")
    private String statusText;
    /**
     * inspCenterGuid
     */
    @ApiModelProperty(value = "inspCenterGuid")
    private String inspCenterGuid;
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
     * 是否需要同步子数据 false待同步 true已同步
     */
    @ApiModelProperty(value = "false待同步 true已同步")
    private Boolean handleState;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Integer updateTime;
}
