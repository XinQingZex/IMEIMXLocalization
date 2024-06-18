package com.agile.qdmp.standalong.model.dto.integrator;

import com.agile.tem.common.core.model.BaseDTO;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 企业质量管理大师信息表
 *
 * @author hyzh code generator
 * @date 2022-09-19 09:56:51
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ImServerDTO", description = "企业质量管理大师信息表")
public class ImServerDTO extends BaseDTO {
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
     * 编码
     */
    @ApiModelProperty(value = "编码")
    private String code;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;
    /**
     * 连接地址
     */
    @ApiModelProperty(value = "连接地址")
    private String url;
    /**
     * API 版本
     */
    @ApiModelProperty(value = "API 版本")
    private String apiVersion;
    /**
     * 审核状态 自动入库无公司名称 未审核 已审核
     */
    @ApiModelProperty(value = "审核状态 自动入库无公司名称 未审核 已审核")
    private String state;
    /**
     * 拒绝原因
     */
    @ApiModelProperty(value = "拒绝原因")
    private String refuseReason;
    /**
     * 创建者ID
     */
    @ApiModelProperty(value = "创建者ID")
    @JsonSerialize(using=ToStringSerializer.class)
    private Long creatorId;
    /**
     * 创建者名称
     */
    @ApiModelProperty(value = "创建者名称")
    private String creator;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Integer createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Integer updateTime;

    /**
     * 更新状态
     */
    @ApiModelProperty(value = "更新状态")
    private Boolean updateStatus;
}
