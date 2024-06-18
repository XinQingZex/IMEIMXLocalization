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
 * DRAWING
 *
 * @author wenbinglei
 * @date 2022-10-22 15:03:30
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "DrawingDTO", description = "DRAWING")
public class DrawingDTO extends BaseDTO {
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
     * 所属partGuid
     */
    @ApiModelProperty(value = "所属partGuid")
    private String partGuid;
    /**
     * DimsCount
     */
    @ApiModelProperty(value = "DimsCount")
    private Integer dimsCount;
    /**
     * DrawingFile
     */
    @ApiModelProperty(value = "DrawingFile")
    private String drawingFile;
    /**
     * GUID
     */
    @ApiModelProperty(value = "GUID")
    private String guid;
    /**
     * Notes
     */
    @ApiModelProperty(value = "Notes")
    private String notes;
    /**
     * PdfPageNo
     */
    @ApiModelProperty(value = "PdfPageNo")
    private Integer pdfPageNo;
    /**
     * Revision
     */
    @ApiModelProperty(value = "Revision")
    private String revision;
    /**
     * SheetName
     */
    @ApiModelProperty(value = "SheetName")
    private String sheetName;
    /**
     * SheetNo
     */
    @ApiModelProperty(value = "SheetNo")
    private String sheetNo;
    /**
     * Title
     */
    @ApiModelProperty(value = "Title")
    private String title;
    /**
     * Version
     */
    @ApiModelProperty(value = "Version")
    private String version;
    /**
     * 文件地址
     */
    @ApiModelProperty(value = "文件地址")
    private String pdfUrl;
    /**
     * 图片地址
     */
    @ApiModelProperty(value = "图片地址")
    private String imageUrl;
    /**
     * 图片地址Dim
     */
    @ApiModelProperty(value = "图片地址Dim")
    private String imageDimUrl;
    /**
     * 图纸图片宽度
     */
    @ApiModelProperty(value = "图纸图片宽度")
    private Integer imageWidth;
    /**
     * 图纸图片高度
     */
    @ApiModelProperty(value = "图纸图片高度")
    private Integer imageHeight;
    /**
     * 图纸宽度
     */
    @ApiModelProperty(value = "图纸宽度")
    private Integer drawingWidth;
    /**
     * 图纸高度
     */
    @ApiModelProperty(value = "图纸高度")
    private Integer drawingHeight;

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
