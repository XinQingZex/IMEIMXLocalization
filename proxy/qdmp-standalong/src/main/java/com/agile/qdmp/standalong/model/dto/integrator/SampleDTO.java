package com.agile.qdmp.standalong.model.dto.integrator;

import com.agile.tem.common.core.model.BaseDTO;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * SAMPLE
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:42:00
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "SampleDTO", description = "SAMPLE")
public class SampleDTO extends BaseDTO {
    private static final long serialVersionUID = 1L;
//    @FiledDesensitization(value = MaskingTypeEnum.FIXED_PHONE)
//    @FiledDesensitization(retainLeft=2, retainRight = 3, padStr = "@", separator = "#")


    /**
     * 企业id
     */
    @ApiModelProperty(value = "企业id")
    @JsonSerialize(using= ToStringSerializer.class)
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
    @JsonSerialize(using=ToStringSerializer.class)
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

    /**
     * LotDTO
     */
    @ApiModelProperty(value = "LotDTO")
    private LotDTO lot;
    /**
     * JobDTO
     */
    @ApiModelProperty(value = "JobDTO")
    private JobDTO job;
    /**
     * PartDTO
     */
    @ApiModelProperty(value = "PartDTO")
    private PartDTO part;
    /**
     * 特征值
     */
    @ApiModelProperty(value = "特征值")
    private String flag;
    /**
     * 是否需要同步子数据 false待同步 true已同步
     */
    @ApiModelProperty(value = "false待同步 true已同步")
    private String handleState;
//    /**
//     * 工序序列字段
//     */
//    @ApiModelProperty(value = "工序序列字段")
//    private String operations;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Integer updateTime;

    /**
     * 尺寸总数
     */
    @ApiModelProperty(value = "尺寸总数")
    private Integer dimTotal;

    /**
     * 结果统计
     */
    private List<ResultCount> resultCount;
}
