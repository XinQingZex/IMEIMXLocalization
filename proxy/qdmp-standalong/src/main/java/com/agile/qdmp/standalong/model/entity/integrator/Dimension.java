package com.agile.qdmp.standalong.model.entity.integrator;

import com.agile.tem.common.core.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * DIMENSION
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:41:56
 */
@Data
@TableName("im_dimension")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Dimension", description = "DIMENSION")
public class Dimension extends BaseModel {
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
     * PartGUID
     */
    @ApiModelProperty(value = "PartGUID")
    private String partGuid;
    /**
     * DatMod1
     */
    @ApiModelProperty(value = "DatMod1")
    private String datMod1;
    /**
     * DatMod2
     */
    @ApiModelProperty(value = "DatMod2")
    private String datMod2;
    /**
     * DatMod3
     */
    @ApiModelProperty(value = "DatMod3")
    private String datMod3;
    /**
     * Datum1
     */
    @ApiModelProperty(value = "Datum1")
    private String datum1;
    /**
     * Datum2
     */
    @ApiModelProperty(value = "Datum2")
    private String datum2;
    /**
     * Datum3
     */
    @ApiModelProperty(value = "Datum3")
    private String datum3;
    /**
     * Designator
     */
    @ApiModelProperty(value = "Designator")
    private String designator;
    /**
     * DimNo
     */
    @ApiModelProperty(value = "DimNo")
    private String dimNo;
    /**
     * DimSort
     */
    @ApiModelProperty(value = "DimSort")
    private String dimSort;
    /**
     * DrawingGUID
     */
    @ApiModelProperty(value = "DrawingGUID")
    private String drawingGuid;
    /**
     * GUID
     */
    @ApiModelProperty(value = "GUID")
    private String guid;
    /**
     * LowerTol
     */
    @ApiModelProperty(value = "LowerTol")
    private BigDecimal lowerTol;
    /**
     * Multiplier
     */
    @ApiModelProperty(value = "Multiplier")
    private Integer multiplier;
    /**
     * Nominal
     */
    @ApiModelProperty(value = "Nominal")
    private BigDecimal nominal;
    /**
     * Note
     */
    @ApiModelProperty(value = "Note")
    private String note;
    /**
     * OperationGUID
     */
    @ApiModelProperty(value = "OperationGUID")
    private String operationGuid;
    /**
     * Requirement
     */
    @ApiModelProperty(value = "Requirement")
    private String requirement;
    /**
     * ShapeActive
     */
    @ApiModelProperty(value = "ShapeActive")
    private Integer shapeActive;
    /**
     * ShapeCenter
     */
    @ApiModelProperty(value = "ShapeCenter")
    private String shapeCenter;
    /**
     * ShapeLocation
     */
    @ApiModelProperty(value = "ShapeLocation")
    private String shapeLocation;
    /**
     * ShapeOffset
     */
    @ApiModelProperty(value = "ShapeOffset")
    private String shapeOffset;
    /**
     * ShapePoints
     */
    @ApiModelProperty(value = "ShapePoints")
    private String shapePoints;
    /**
     * ShapeRotateAngle
     */
    @ApiModelProperty(value = "ShapeRotateAngle")
    private Integer shapeRotateAngle;
    /**
     * ShapeTitle
     */
    @ApiModelProperty(value = "ShapeTitle")
    private String shapeTitle;
    /**
     * ShapeType
     */
    @ApiModelProperty(value = "ShapeType")
    private Integer shapeType;
    /**
     * TolCalc
     */
    @ApiModelProperty(value = "TolCalc")
    private Integer tolCalc;
    /**
     * TolClass
     */
    @ApiModelProperty(value = "TolClass")
    private String tolClass;
    /**
     * TolMod1
     */
    @ApiModelProperty(value = "TolMod1")
    private String tolMod1;
    /**
     * TolMod2
     */
    @ApiModelProperty(value = "TolMod2")
    private String tolMod2;
    /**
     * TolMod3
     */
    @ApiModelProperty(value = "TolMod3")
    private String tolMod3;
    /**
     * TolType
     */
    @ApiModelProperty(value = "TolType")
    private Integer tolType;
    /**
     * TolTypeText
     */
    @ApiModelProperty(value = "TolTypeText")
    private String tolTypeText;
    /**
     * Type
     */
    @ApiModelProperty(value = "Type")
    private Integer type;
    /**
     * TypeText
     */
    @ApiModelProperty(value = "TypeText")
    private String typeText;
    /**
     * Units
     */
    @ApiModelProperty(value = "Units")
    private Integer units;
    /**
     * UpperTol
     */
    @ApiModelProperty(value = "UpperTol")
    private BigDecimal upperTol;
    /**
     * procedureGuid
     */
    @ApiModelProperty(value = "procedureGuid")
    private String procedureGuid;
    /**
     * inspCenterGuid
     */
    @ApiModelProperty(value = "inspCenterGuid")
    private String inspCenterGuid;
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
