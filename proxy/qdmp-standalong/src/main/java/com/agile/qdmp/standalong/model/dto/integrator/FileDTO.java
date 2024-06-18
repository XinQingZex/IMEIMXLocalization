package com.agile.qdmp.standalong.model.dto.integrator;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: wenbinglei
 * @Date: 2021/2/18 11:51
 * @Description: 文件
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "FileDTO", description = "文件")
public class FileDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 文件名称
     */
    @ApiModelProperty(value = "文件名称")
    private String name;
    /**
     * 存储路径
     */
    @ApiModelProperty(value = "存储路径")
    private String path;
    /**
     * 文件Key
     */
    @ApiModelProperty(value = "文件Key")
    private String key;
    /**
     * 文件访问URL
     */
    @ApiModelProperty(value = "文件访问URL")
    private String url;
}
