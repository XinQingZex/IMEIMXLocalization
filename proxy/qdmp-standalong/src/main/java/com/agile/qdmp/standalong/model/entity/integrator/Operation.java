package com.agile.qdmp.standalong.model.entity.integrator;


import com.agile.tem.common.core.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * Operation
 *
 * @author wenbinglei
 * @date 2022-10-21 11:47:53
 */
@Data
@TableName("im_operation")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Operation", description = "Operation")
public class Operation extends BaseModel {
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
     * WorkCellGUID
     */
    @ApiModelProperty(value = "WorkCellGUID")
    private String workCellGuid;
    /**
     * MachineGUID
     */
    @ApiModelProperty(value = "MachineGUID")
    private String machineGuid;
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
     * Description
     */
    @ApiModelProperty(value = "Description")
    private String description;
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
