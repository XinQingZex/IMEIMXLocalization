package com.agile.qdmp.standalong.model.entity.integrator;

import com.agile.tem.common.core.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * SAMPLE
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:42:00
 */
@Data
@TableName("im_sample")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Sample", description = "SAMPLE")
public class Sample extends BaseModel {
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
     * BarcodeID
     */
    @ApiModelProperty(value = "BarcodeID")
    private String barcodeId;
    /**
     * CavityNumber
     */
    @ApiModelProperty(value = "CavityNumber")
    private Integer cavityNumber;
    /**
     * CreationDate
     */
    @ApiModelProperty(value = "CreationDate")
    private LocalDateTime creationDate;
    /**
     * ERPID
     */
    @ApiModelProperty(value = "ERPID")
    private String erpId;
    /**
     * FixtureNumber
     */
    @ApiModelProperty(value = "FixtureNumber")
    private String fixtureNumber;
    /**
     * GUID
     */
    @ApiModelProperty(value = "GUID")
    private String guid;
    /**
     * JobGUID
     */
    @ApiModelProperty(value = "JobGUID")
    private String jobGuid;
    /**
     * LotGUID
     */
    @ApiModelProperty(value = "LotGUID")
    private String lotGuid;
    /**
     * MachineNumber
     */
    @ApiModelProperty(value = "MachineNumber")
    private String machineNumber;
    /**
     * PartGUID
     */
    @ApiModelProperty(value = "PartGUID")
    private String partGuid;
    /**
     * Results
     */
    @ApiModelProperty(value = "Results")
    private Integer results;
    /**
     * SerialNumber
     */
    @ApiModelProperty(value = "SerialNumber")
    private String serialNumber;
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
//    /**
//     * 工序序列字段
//     */
//    @ApiModelProperty(value = "工序序列字段")
//    private String operations;
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
