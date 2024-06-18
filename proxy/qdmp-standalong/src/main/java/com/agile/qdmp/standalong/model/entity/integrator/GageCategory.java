package com.agile.qdmp.standalong.model.entity.integrator;

import com.agile.tem.common.core.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * GAGE_CATEGORY
 *
 * @author wenbinglei
 * @date 2022-10-21 14:06:08
 */
@Data
@TableName("im_gage_category")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "GageCategory", description = "GAGE_CATEGORY")
public class GageCategory extends BaseModel {
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
     * 类目Code
     */
    @ApiModelProperty(value = "类目Code")
    private String categoryCode;
    /**
     * 类目名称
     */
    @ApiModelProperty(value = "类目名称")
    private String name;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Integer updateTime;
}
