package com.agile.qdmp.standalong.model.dto.integrator.query;

import com.agile.tem.common.core.model.BaseQueryDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Receivers
 *
 * @author hyzh code generator
 * @date 2022-11-22 18:41:33
 */
@Data
@ApiModel(value = "ReceiverQueryDTO", description = "Receivers")
public class ReceiverQueryDTO extends BaseQueryDTO {
    private static final long serialVersionUID = 1L;
    /**
     * IM Server ID
     */
    @ApiModelProperty(value = "IM Server ID")
    private Long serverId;
    /**
     * GUID
     */
    @ApiModelProperty(value = "GUID")
    private String guid;
    /**
     * keyWord
     */
    @ApiModelProperty(value = "keyWord")
    private String keyWord;
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
     * AttachmentType
     */
    @ApiModelProperty(value = "AttachmentType")
    private Integer attachmentType;
    /**
     * 特征值
     */
    @ApiModelProperty(value = "特征值")
    private String flag;
}
