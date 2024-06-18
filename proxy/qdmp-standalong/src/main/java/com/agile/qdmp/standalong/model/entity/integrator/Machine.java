package com.agile.qdmp.standalong.model.entity.integrator;


import com.agile.tem.common.core.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Machine
 *
 * @author wenbinglei
 * @date 2022-10-21 11:47:57
 */
@Data
@TableName("im_machine")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Machine", description = "Machine")
public class Machine extends BaseModel {
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
     * WorkCellGUID
     */
    @ApiModelProperty(value = "WorkCellGUID")
    private String workCellGuid;
    /**
     * PlaceGUID
     */
    @ApiModelProperty(value = "PlaceGUID")
    private String machineId;
    /**
     * Title
     */
    @ApiModelProperty(value = "Title")
    private String title;
    /**
     * Brand
     */
    @ApiModelProperty(value = "Brand")
    private String brand;
    /**
     * SerialNo
     */
    @ApiModelProperty(value = "SerialNo")
    private String serialNo;
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
     * Notes
     */
    @ApiModelProperty(value = "Notes")
    private String notes;
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
