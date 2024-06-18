package com.agile.qdmp.standalong.model.entity.integrator;

import com.agile.tem.common.core.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * IM_API_RECORD
 *
 * @author hyzh code generator
 * @date 2022-09-19 09:56:55
 */
@Data
//@TableName("sys_im_api")
@TableName(value = "sys_im_api_record", autoResultMap = true)
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ImApiRecord", description = "IM_API_RECORD")
public class ImApiRecord extends BaseModel {
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
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long accountId;

    /**
     * 职员id
     */
    @ApiModelProperty(value = "职员id")
    private Long staffId;
    /**
     * 职员名称
     */
    @ApiModelProperty(value = "职员名称")
    private String staffName;

    /**
     * IM Server id
     */
    @ApiModelProperty(value = "IM Server id")
    private Long serverId;
    /**
     * IM Server 名称
     */
    @ApiModelProperty(value = "IM Server 名称")
    private String serverName;
    /**
     * IM Server 连接地址
     */
    @ApiModelProperty(value = "IM Server 连接地址")
    private String serverUrl;

    /**
     * 操作内容
     */
    @ApiModelProperty(value = "操作内容")
    private String content;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Integer updateTime;
}
