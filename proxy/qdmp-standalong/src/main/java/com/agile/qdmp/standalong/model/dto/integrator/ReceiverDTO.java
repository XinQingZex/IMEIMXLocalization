package com.agile.qdmp.standalong.model.dto.integrator;

import com.agile.tem.common.core.model.BaseDTO;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Receivers
 *
 * @author hyzh code generator
 * @date 2022-11-22 18:41:33
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ReceiverDTO", description = "Receivers")
public class ReceiverDTO extends BaseDTO {
    private static final long serialVersionUID = 1L;
//    @FiledDesensitization(value = MaskingTypeEnum.FIXED_PHONE)
//    @FiledDesensitization(retainLeft=2, retainRight = 3, padStr = "@", separator = "#")

    /**
     * 企业id
     */
    @ApiModelProperty(value = "企业id")
    @JsonSerialize(using = ToStringSerializer.class)
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
    @JsonSerialize(using = ToStringSerializer.class)
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
     * Name
     */
    @ApiModelProperty(value = "Name")
    private String name;
    /**
     * number
     */
    @ApiModelProperty(value = "number")
    private String number;
    /**
     * ReceiverID
     */
    @ApiModelProperty(value = "ReceiverID")
    private String receiverId;
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
     * AttachmentType
     */
    @ApiModelProperty(value = "AttachmentType")
    private Integer attachmentType;
    /**
     * AttachmentTypeText
     */
    @ApiModelProperty(value = "AttachmentTypeText")
    private String attachmentTypeText;
    /**
     * Delimeter
     */
    @ApiModelProperty(value = "Delimeter")
    private String delimeter;
    /**
     * DecimalSeparator
     */
    @ApiModelProperty(value = "DecimalSeparator")
    private String decimalSeparator;
    /**
     * RemoveEnaMarkerDevice
     */
    @ApiModelProperty(value = "RemoveEnaMarkerDevice")
    private String removeEnaMarkerDevice;
    /**
     * RemoveEndMarkerValue
     */
    @ApiModelProperty(value = "RemoveEndMarkerValue")
    private String removeEndMarkerValue;
    /**
     * ChannelIndex
     */
    @ApiModelProperty(value = "ChannelIndex")
    private Integer channelIndex;
    /**
     * ValueIndex
     */
    @ApiModelProperty(value = "ValueIndex")
    private Integer valueIndex;
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
