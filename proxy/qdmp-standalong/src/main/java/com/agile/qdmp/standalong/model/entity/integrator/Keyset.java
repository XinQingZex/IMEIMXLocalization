package com.agile.qdmp.standalong.model.entity.integrator;

import com.agile.tem.common.core.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Keyset
 *
 * @author wenbinglei
 * @date 2022-10-21 14:06:08
 */
@Data
@TableName("im_keyset")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Keyset", description = "Keyset")
public class Keyset extends BaseModel {
    private static final long serialVersionUID = 1L;

    /**
     * PartGuid
     */
    @ApiModelProperty(value = "PartGuid")
    private String partGuid;
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
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Integer updateTime;
}
