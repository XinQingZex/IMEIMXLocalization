package com.agile.qdmp.standalong.model.entity.integrator;

import com.agile.tem.common.core.model.BaseModel;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * GAGE
 *
 * @author wenbinglei
 * @date 2022-10-17 14:25:55
 */
@Data
@TableName("im_gage")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "Gage", description = "GAGE")
public class Gage extends BaseModel {
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
     * GUID
     */
    @ApiModelProperty(value = "GUID")
    private String guid;
    /**
     * CategoryCode
     */
    @ApiModelProperty(value = "CategoryCode")
    private String categoryCode;
    /**
     * GageID
     */
    @ApiModelProperty(value = "GageID")
    private String gageId;
    /**
     * Name
     */
    @ApiModelProperty(value = "Name")
    private String name;
    /**
     * manufacturer
     */
    @ApiModelProperty(value = "manufacturer")
    private String manufacturer;
    /**
     * Description
     */
    @ApiModelProperty(value = "Description")
    private String description;
    /**
     * SerialNumber
     */
    @ApiModelProperty(value = "SerialNumber")
    private String serialNumber;
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
     * PurchaseDate
     */
    @ApiModelProperty(value = "PurchaseDate")
    private String purchaseDate;
    /**
     * RetirementDate
     */
    @ApiModelProperty(value = "RetirementDate")
    private String retirementDate;
    /**
     * OwnerCompanyGUID
     */
    @ApiModelProperty(value = "OwnerCompanyGUID")
    private String ownerCompanyGuid;
    /**
     * LocationGUID
     */
    @ApiModelProperty(value = "LocationGUID")
    private String locationGuid;
    /**
     * StoragePlaceGUID
     */
    @ApiModelProperty(value = "StoragePlaceGUID")
    private String storagePlaceGuid;
    /**
     * CurrentPlaceGUID
     */
    @ApiModelProperty(value = "CurrentPlaceGUID")
    private String currentPlaceGuid;
    /**
     * CurrentUserGUID
     */
    @ApiModelProperty(value = "CurrentUserGUID")
    private String currentUserGuid;
    /**
     * TrackingStatus
     */
    @ApiModelProperty(value = "TrackingStatus")
    private Integer trackingStatus;
    /**
     * TrackingStatusText
     */
    @ApiModelProperty(value = "TrackingStatusText")
    private String trackingStatusText;
    /**
     * SupplierGUID
     */
    @ApiModelProperty(value = "SupplierGUID")
    private String supplierGuid;
    /**
     * MaintenanceNotes
     */
    @ApiModelProperty(value = "MaintenanceNotes")
    private String maintenanceNotes;
    /**
     * LastServiceDate
     */
    @ApiModelProperty(value = "LastServiceDate")
    private String lastServiceDate;
    /**
     * ZeroedDate
     */
    @ApiModelProperty(value = "ZeroedDate")
    private String zeroedDate;
    /**
     * CalibrationVendorGUID
     */
    @ApiModelProperty(value = "CalibrationVendorGUID")
    private String calibrationVendorGuid;
    /**
     * LastCalibratedByGUID
     */
    @ApiModelProperty(value = "LastCalibratedByGUID")
    private String lastCalibratedByGuid;
    /**
     * NextDueDate
     */
    @ApiModelProperty(value = "NextDueDate")
    private String nextDueDate;
    /**
     * LastCalibrationDate
     */
    @ApiModelProperty(value = "LastCalibrationDate")
    private String lastCalibrationDate;
    /**
     * LastCalibrationResult
     */
    @ApiModelProperty(value = "LastCalibrationResult")
    private Integer lastCalibrationResult;
    /**
     * LastCalibrationResultText
     */
    @ApiModelProperty(value = "LastCalibrationResultText")
    private String lastCalibrationResultText;
    /**
     * CalibrationFrequency
     */
    @ApiModelProperty(value = "CalibrationFrequency")
    private Integer calibrationFrequency;
    /**
     * CalibrationInterval
     */
    @ApiModelProperty(value = "CalibrationInterval")
    private Integer calibrationInterval;
    /**
     * CalibrationIntervalText
     */
    @ApiModelProperty(value = "CalibrationIntervalText")
    private String calibrationIntervalText;
    /**
     * UsageCount
     */
    @ApiModelProperty(value = "UsageCount")
    private Integer usageCount;
    /**
     * CertNumber
     */
    @ApiModelProperty(value = "CertNumber")
    private String certNumber;
    /**
     * CertExpDate
     */
    @ApiModelProperty(value = "CertExpDate")
    private String certExpDate;
    /**
     * Units
     */
    @ApiModelProperty(value = "Units")
    private Integer units;
    /**
     * UnitsText
     */
    @ApiModelProperty(value = "UnitsText")
    private String unitsText;
    /**
     * Resolution
     */
    @ApiModelProperty(value = "Resolution")
    private String resolution;
    /**
     * Uncertainty
     */
    @ApiModelProperty(value = "Uncertainty")
    private String uncertainty;
    /**
     * OpRange
     */
    @ApiModelProperty(value = "OpRange")
    private String opRange;
    /**
     * UpperTol
     */
    @ApiModelProperty(value = "UpperTol")
    private String upperTol;
    /**
     * LowerTol
     */
    @ApiModelProperty(value = "LowerTol")
    private String lowerTol;
    /**
     * Notes
     */
    @ApiModelProperty(value = "Notes")
    private String notes;
    /**
     * ReceiverGUID
     */
    @ApiModelProperty(value = "ReceiverGUID")
    private String receiverGuid;
    /**
     * DeviceID
     */
    @ApiModelProperty(value = "DeviceID")
    private String deviceId;
    /**
     * ERPID
     */
    @ApiModelProperty(value = "ERPID")
    private String erpId;
    /**
     * BarcodeID
     */
    @ApiModelProperty(value = "BarcodeID")
    private String barcodeId;
    /**
     * ExpStatus
     */
    @ApiModelProperty(value = "ExpStatus")
    private Integer expStatus;
    /**
     * exp_status_text
     */
    @ApiModelProperty(value = "exp_status_text")
    private String expStatusText;
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
