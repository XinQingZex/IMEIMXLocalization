package com.agile.qdmp.standalong.model.dto.integrator.query;

import com.agile.tem.common.core.model.BaseQueryDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * SAMPLE
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:42:00
 */
@Data
@ApiModel(value = "SampleQueryDTO", description = "SAMPLE")
public class SampleQueryDTO extends BaseQueryDTO {
    private static final long serialVersionUID = 1L;
    /**
     * keyWord
     */
    @ApiModelProperty(value = "keyWord")
    private String keyWord;
    /**
     * 企业id
     */
    @ApiModelProperty(value = "企业id")
    private Long companyId;
    /**
     * IM Server ID
     */
    @ApiModelProperty(value = "IM Server ID")
    private Long serverId;
    /**
     * sampleId
     */
    @ApiModelProperty(value = "sampleId")
    private String sampleId;
    /**
     * GUIDs
     */
    @ApiModelProperty(value = "GUIDs")
    private String[] guids;
    /**
     * JobGUID
     */
    @ApiModelProperty(value = "JobGUID")
    private String jobGuid;
    /**
     * lotId
     */
    @ApiModelProperty(value = "lotId")
    private String lotId;
    /**
     * MachineNumber
     */
    @ApiModelProperty(value = "MachineNumber")
    private String machineNumber;
    /**
     * partName
     */
    @ApiModelProperty(value = "partName")
    private String partName;
    /**
     * partNumber
     */
    @ApiModelProperty(value = "partNumber")
    private String partNumber;
    /**
     * partRevision
     */
    @ApiModelProperty(value = "partRevision")
    private String partRevision;
    /**
     * Status
     */
    @ApiModelProperty(value = "Status")
    private Integer status;

    /**
     * 是否获取所属Lot
     */
    @ApiModelProperty(value = "是否获取所属Lot")
    private Boolean withLot;
    /**
     * 是否获取所属Job
     */
    @ApiModelProperty(value = "是否获取所属Job")
    private Boolean withJob;
    /**
     * 是否获取所属Part
     */
    @ApiModelProperty(value = "是否获取所属Part")
    private Boolean withPart;
    /**
     * startDate
     */
    @ApiModelProperty(value = "startDate")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;
    /**
     * endDate
     */
    @ApiModelProperty(value = "endDate")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;
//    /**
//     * 是否获取尺寸数量
//     */
//    @ApiModelProperty(value = "是否获取尺寸数量")
//    private Boolean withDimCount;

//    /**
//     * 获取尺寸类型
//     * { value: 1, label: '过程检验', original: 'Production (In-Process)' },
//     * { value: 2, label: '最终检验', original: 'Final Inspection' }
//     */
//    @ApiModelProperty(value = "获取尺寸类型")
//    private Integer qualityStage;

}
