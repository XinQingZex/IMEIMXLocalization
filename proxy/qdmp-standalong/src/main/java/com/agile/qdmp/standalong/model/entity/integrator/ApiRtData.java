package com.agile.qdmp.standalong.model.entity.integrator;

import com.agile.tem.common.core.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 接口数据
 *
 * @author hyzh code generator
 * @date 2022-12-09 09:37:49
 */
@Data
@TableName("im_api_rt_data")
@ApiModel(value = "ApiData", description = "接口数据")
public class ApiRtData extends BaseModel {
    private static final long serialVersionUID = 1L;
    /**
     * guid
     */
    @ApiModelProperty(value = "guid")
    private String guid;
    /**
     * 增量更新Key值
     */
    @ApiModelProperty(value = "增量更新Key值")
    private String source;
    /**
     * GUID
     */
    @ApiModelProperty(value = "GUID")
    private String flag;
    /**
     * 数据类型
     */
    @ApiModelProperty(value = "数据类型")
    private String type;
    /**
     * 接口返回的数据
     */
    @ApiModelProperty(value = "接口返回的数据")
    private String content;
}
