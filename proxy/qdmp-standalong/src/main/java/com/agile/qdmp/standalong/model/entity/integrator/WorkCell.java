package com.agile.qdmp.standalong.model.entity.integrator;


import com.agile.tem.common.core.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * WorkCell
 *
 * @author wenbinglei
 * @date 2022-10-21 11:48:07
 */
@Data
@TableName("im_work_cell")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "WorkCell", description = "WorkCell")
public class WorkCell extends BaseModel {
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
     * LocationGUID
     */
    @ApiModelProperty(value = "LocationGUID")
    private String locationGuid;
    /**
     * PlaceGUID
     */
    @ApiModelProperty(value = "PlaceGUID")
    private String placeGuid;
    /**
     * WorkCellID
     */
    @ApiModelProperty(value = "WorkCellID")
    private String workCellId;
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
