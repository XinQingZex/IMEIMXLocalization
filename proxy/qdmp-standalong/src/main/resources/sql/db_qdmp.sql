-- ----------------------------
-- Table structure for account_base_info
-- ----------------------------
-- DROP TABLE IF EXISTS `account_base_info`;
CREATE TABLE IF NOT EXISTS `account_base_info`  (
  `id` bigint NOT NULL,
  `account_id` bigint NULL DEFAULT 0 COMMENT '账户id',
  `name` varchar(15)  DEFAULT '' COMMENT '真实姓名',
  `photo` varchar(200)  DEFAULT '' COMMENT '免冠照片',
  `nation` varchar(15)  DEFAULT '' COMMENT '民族',
  `birthday` varchar(20)  DEFAULT '' COMMENT '出生日期',
  `marital_state` varchar(5)  DEFAULT '' COMMENT '婚姻状况 未婚 已婚',
  `education_state` varchar(20)  DEFAULT '' COMMENT '学历',
  `politics_state` varchar(20)  DEFAULT '' COMMENT '政治面貌',
  `census_place` varchar(50)  DEFAULT '' COMMENT '户籍所在地',
  `census_type` varchar(20)  DEFAULT '' COMMENT '户籍类型',
  `province` varchar(20)  DEFAULT '' COMMENT '现住-省',
  `city` varchar(50)  DEFAULT '' COMMENT '现住-城市',
  `area` varchar(50)  DEFAULT '' COMMENT '现住-区',
  `address` varchar(100)  DEFAULT '' COMMENT '现住-地址',
  `deleted` tinyint NULL DEFAULT 0 COMMENT '是否删除 0否 1是',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for account_im_info
-- ----------------------------
-- DROP TABLE IF EXISTS `account_im_info`;
CREATE TABLE IF NOT EXISTS `account_im_info`  (
  `id` bigint NOT NULL,
  `account_id` bigint NULL DEFAULT 0 COMMENT '账户id',
  `username` varchar(50)  DEFAULT '' COMMENT '用户名',
  `password` varchar(80)  DEFAULT '' COMMENT '登录密码',
  `server_id` bigint NULL DEFAULT 0 COMMENT 'IM_SERVER_ID',
  `token` varchar(200)  DEFAULT '' COMMENT '最后一次token',
  `ip` varchar(16)  DEFAULT '' COMMENT '最后一次IP',
  `create_time` int NULL DEFAULT 0 COMMENT '创建时间',
  `update_time` int NULL DEFAULT 0 COMMENT '更新时间',
  `deleted` tinyint NULL DEFAULT 0 COMMENT '是否删除 0否 1是',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for account_login_info
-- ----------------------------
-- DROP TABLE IF EXISTS `account_login_info`;
CREATE TABLE IF NOT EXISTS `account_login_info`  (
  `id` bigint NOT NULL COMMENT 'ID',
  `username` varchar(50)  DEFAULT '' COMMENT '用户名',
  `password` varchar(80)  DEFAULT '' COMMENT '登录密码',
  `nickname` varchar(50)  DEFAULT '' COMMENT '昵称',
  `avatar` varchar(300)  DEFAULT '' COMMENT '用户头像',
  `gender` varchar(10)  DEFAULT '' COMMENT '用户性别',
  `email` varchar(50)  DEFAULT '' COMMENT '邮件地址',
  `mobile` varchar(15)  DEFAULT '' COMMENT '手机号',
  `user_source` varchar(20)  DEFAULT '' COMMENT '用户来源',
  `activated` BOOLEAN NOT NULL DEFAULT TRUE COMMENT '是否激活',
  `creator` varchar(20)  DEFAULT '' COMMENT '创建人',
  `created_time` datetime NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `state` varchar(8)  DEFAULT 'normal' COMMENT '状态, normal=正常,lock=锁定',
  `deleted` tinyint NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for account_social_info
-- ----------------------------
-- DROP TABLE IF EXISTS `account_social_info`;
CREATE TABLE IF NOT EXISTS `account_social_info`  (
  `id` bigint NOT NULL COMMENT 'ID',
  `account_id` bigint NOT NULL COMMENT '账号ID',
  `uuid` varchar(50)  DEFAULT '' COMMENT '第三方系统的唯一ID',
  `source` varchar(20)  DEFAULT '' COMMENT '第三方用户来源',
  `access_token` varchar(100)  DEFAULT '' COMMENT '用户的授权令牌',
  `refresh_token` varchar(100)  DEFAULT '' COMMENT '刷新令牌',
  `expire_in` int NULL DEFAULT 0 COMMENT '第三方用户的授权令牌的有效期',
  `open_id` varchar(32)  DEFAULT '' COMMENT '第三方用户的 open id',
  `uid` varchar(32)  DEFAULT '' COMMENT '第三方用户的 ID',
  `access_code` varchar(32)  DEFAULT '' COMMENT '个别平台的授权信息',
  `union_id` varchar(32)  DEFAULT '' COMMENT '第三方用户的 union id',
  `scope` varchar(32)  DEFAULT '' COMMENT '第三方用户授予的权限',
  `token_type` varchar(32)  DEFAULT '' COMMENT '个别平台的授权信息',
  `id_token` varchar(32)  DEFAULT '' COMMENT 'id token',
  `mac_algorithm` varchar(32)  DEFAULT '' COMMENT '小米平台用户的附带属性',
  `mac_key` varchar(32)  DEFAULT '' COMMENT '小米平台用户的附带属性',
  `code` varchar(32)  DEFAULT '' COMMENT '用户的授权code',
  `state` varchar(8)  DEFAULT 'normal' COMMENT '状态, normal=正常,lock=锁定',
  `deleted` tinyint NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for erp_company
-- ----------------------------
-- DROP TABLE IF EXISTS `erp_company`;
CREATE TABLE IF NOT EXISTS `erp_company`  (
  `id` bigint NOT NULL COMMENT 'id',
  `account_id` bigint NULL DEFAULT 0 COMMENT '用户id',
  `logo` varchar(200)  DEFAULT '' COMMENT '图标',
  `code` varchar(20)  DEFAULT '' COMMENT '编码',
  `name` varchar(100)  DEFAULT '' COMMENT '名称',
  `contact_name` varchar(30)  DEFAULT '' COMMENT '用户名',
  `contact_phone` varchar(20)  DEFAULT '' COMMENT '电话',
  `contact_mobile` varchar(20)  DEFAULT '' COMMENT '手机号',
  `province` varchar(20)  DEFAULT '' COMMENT '省',
  `city` varchar(50)  DEFAULT '' COMMENT '市',
  `district` varchar(50)  DEFAULT '' COMMENT '区',
  `address` varchar(200)  DEFAULT '' COMMENT '地址',
  `latitude` DOUBLE NULL DEFAULT 0.000000 COMMENT '纬度',
  `longitude` DOUBLE NULL DEFAULT 0.000000 COMMENT '经度',
  `state` varchar(20)  DEFAULT '' COMMENT '审核状态 自动入库无公司名称 未审核 已审核',
  `refuse_reason` varchar(100)  DEFAULT '' COMMENT '拒绝原因',
  `creator_id` bigint NULL DEFAULT 0 COMMENT '创建者ID',
  `creator` varchar(20)  DEFAULT '' COMMENT '创建者名称',
  `create_time` int NULL DEFAULT 0 COMMENT '创建时间',
  `deleted` tinyint NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for erp_department
-- ----------------------------
-- DROP TABLE IF EXISTS `erp_department`;
CREATE TABLE IF NOT EXISTS `erp_department`  (
  `id` bigint NOT NULL COMMENT 'id',
  `company_id` bigint NOT NULL COMMENT '企业id',
  `parent_id` bigint NULL DEFAULT 0 COMMENT '上级ID',
  `parent_name` varchar(100)  DEFAULT '' COMMENT '上级名称',
  `name` varchar(100)  DEFAULT '' COMMENT '名称',
  `create_time` int NOT NULL DEFAULT 0 COMMENT '创建时间',
  `state` BOOLEAN NULL DEFAULT FALSE COMMENT '状态 有效 无效',
  `deleted` tinyint NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for erp_staff
-- ----------------------------
-- DROP TABLE IF EXISTS `erp_staff`;
CREATE TABLE IF NOT EXISTS `erp_staff`  (
  `id` bigint NOT NULL COMMENT 'id',
  `company_id` bigint NOT NULL COMMENT '企业id',
  `company_name` varchar(100)  DEFAULT '' COMMENT '企业名称',
  `department_id` bigint NULL DEFAULT 0 COMMENT '部门ID',
  `department_name` varchar(100)  DEFAULT '' COMMENT '部门名称',
  `account_id` bigint NULL DEFAULT 0 COMMENT '用户id',
  `code` varchar(20)  DEFAULT '' COMMENT '编码',
  `name` varchar(100)  DEFAULT '' COMMENT '姓名',
  `contact_mobile` varchar(20)  DEFAULT '' COMMENT '手机号',
  `gender` varchar(10)  DEFAULT '' COMMENT '用户性别',
  `create_time` int NOT NULL DEFAULT 0 COMMENT '创建时间',
  `state` BOOLEAN NULL DEFAULT FALSE COMMENT '状态 有效 无效',
  `deleted` tinyint NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for im_company
-- ----------------------------
-- DROP TABLE IF EXISTS `im_company`;
CREATE TABLE IF NOT EXISTS `im_company`  (
  `id` bigint NOT NULL COMMENT 'id',
  `company_id_sys` bigint NULL DEFAULT 0 COMMENT '企业id',
  `company_name` varchar(100)  DEFAULT '' COMMENT '企业名称',
  `server_id` bigint NULL DEFAULT 0 COMMENT 'IM Server ID',
  `server_name` varchar(100)  DEFAULT '' COMMENT 'IM Server 名称',
  `guid` varchar(36)  DEFAULT '' COMMENT 'GUID',
  `account_type` int NOT NULL DEFAULT 0 COMMENT 'AccountType',
  `account_type_text` varchar(36)  DEFAULT '' COMMENT 'AccountTypeText',
  `name` varchar(200)  DEFAULT '' COMMENT 'Name',
  `parent_company_guid` varchar(36)  DEFAULT '' COMMENT 'ParentCompanyGUID',
  `company_id` varchar(36)  DEFAULT '' COMMENT 'CompanyID',
  `fax` varchar(200)  DEFAULT '' COMMENT 'Fax',
  `email` varchar(200)  DEFAULT '' COMMENT 'Email',
  `website` varchar(200)  DEFAULT '' COMMENT 'WebSite',
  `description` varchar(200)  DEFAULT '' COMMENT 'Description',
  `erp_id` varchar(36)  DEFAULT '' COMMENT 'ERPID',
  `barcode_id` varchar(36)  DEFAULT '' COMMENT 'BarcodeID',
  `flag` varchar(32)  DEFAULT '' COMMENT '特征值',
  `update_time` int NULL DEFAULT 0 COMMENT '更新时间',
  `deleted` tinyint NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `idx_company_flag` (`flag`),
  KEY `idx_company_guid` (`guid`)
);

-- ----------------------------
-- Table structure for im_dimension
-- ----------------------------
-- DROP TABLE IF EXISTS `im_dimension`;
CREATE TABLE IF NOT EXISTS `im_dimension`  (
  `id` bigint NOT NULL COMMENT 'id',
  `company_id` bigint NULL DEFAULT 0 COMMENT '企业id',
  `company_name` varchar(100)  DEFAULT '' COMMENT '企业名称',
  `server_id` bigint NULL DEFAULT 0 COMMENT 'IM Server ID',
  `server_name` varchar(100)  DEFAULT '' COMMENT 'IM Server 名称',
  `part_guid` varchar(36)  DEFAULT '' COMMENT '所属零件GUID',
  `dat_mod1` varchar(50)  DEFAULT '' COMMENT 'DatMod1',
  `dat_mod2` varchar(50)  DEFAULT '' COMMENT 'DatMod2',
  `dat_mod3` varchar(50)  DEFAULT '' COMMENT 'DatMod3',
  `datum1` varchar(50)  DEFAULT '' COMMENT 'Datum1',
  `datum2` varchar(50)  DEFAULT '' COMMENT 'Datum2',
  `datum3` varchar(50)  DEFAULT '' COMMENT 'Datum3',
  `designator_guid` varchar(36)  DEFAULT '' COMMENT 'DesignatorGUID',
  `designator` varchar(100)  DEFAULT '' COMMENT 'Designator',
  `dim_no` varchar(50)  DEFAULT '' COMMENT 'DimNo',
  `dim_sort` varchar(50)  DEFAULT '' COMMENT 'DimSort',
  `drawing_guid` varchar(36)  DEFAULT '' COMMENT 'DrawingGUID',
  `guid` varchar(36)  DEFAULT '' COMMENT 'GUID',
  `lower_tol` DECIMAL(11, 5) NOT NULL DEFAULT 0.000000 COMMENT 'LowerTol',
  `multiplier` int NOT NULL DEFAULT 0 COMMENT 'Multiplier',
  `nominal` DECIMAL(11, 5) NOT NULL DEFAULT 0.00000 COMMENT 'Nominal',
  `note` varchar(500)  DEFAULT '' COMMENT 'Note',
  `operation_guid` varchar(36)  DEFAULT '' COMMENT 'OperationGUID',
  `requirement` varchar(500)  DEFAULT '' COMMENT 'Requirement',
  `shape_active` int NOT NULL DEFAULT 0 COMMENT 'ShapeActive',
  `shape_center` varchar(50)  DEFAULT '' COMMENT 'ShapeCenter',
  `shape_location` varchar(50)  DEFAULT '' COMMENT 'ShapeLocation',
  `shape_offset` varchar(50)  DEFAULT '' COMMENT 'ShapeOffset',
  `shape_points` varchar(50)  DEFAULT '' COMMENT 'ShapePoints',
  `shape_rotate_angle` int NOT NULL DEFAULT 0 COMMENT 'ShapeRotateAngle',
  `shape_title` varchar(50)  DEFAULT '' COMMENT 'ShapeTitle',
  `shape_type` int NOT NULL DEFAULT 0 COMMENT 'ShapeType',
  `tol_calc` int NOT NULL DEFAULT 0 COMMENT 'TolCalc',
  `tol_class` varchar(50)  DEFAULT '' COMMENT 'TolClass',
  `tol_mod1` varchar(50)  DEFAULT '' COMMENT 'TolMod1',
  `tol_mod2` varchar(50)  DEFAULT '' COMMENT 'TolMod2',
  `tol_mod3` varchar(50)  DEFAULT '' COMMENT 'TolMod3',
  `tol_type` int NOT NULL DEFAULT 0 COMMENT 'TolType',
  `tol_type_text` varchar(50)  DEFAULT '' COMMENT 'TolTypeText',
  `type` int NOT NULL DEFAULT 0 COMMENT 'Type',
  `type_text` varchar(50)  DEFAULT '' COMMENT 'TypeText',
  `units` int NOT NULL DEFAULT 0 COMMENT 'Units',
  `upper_tol` decimal(11, 5) NOT NULL DEFAULT 0.0000 COMMENT 'UpperTol',
	`procedure_guid` varchar(36) DEFAULT '' COMMENT '整理Procedure GUID',
	`insp_center_guid` varchar(36) DEFAULT '' COMMENT '整理InspCenter GUID',
  `flag` varchar(32)  DEFAULT '' COMMENT '特征值',
  `update_time` int NULL DEFAULT 0 COMMENT '更新时间',
  `deleted` tinyint NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `idx_dimension_flag` (`flag`),
  KEY `idx_dimension_guid` (`guid`)
);

-- ----------------------------
-- Table structure for im_dimension_set
-- ----------------------------
-- DROP TABLE IF EXISTS `im_dimension_set`;
CREATE TABLE IF NOT EXISTS `im_dimension_set`  (
  `id` bigint NOT NULL COMMENT 'id',
  `company_id` bigint NULL DEFAULT 0 COMMENT '企业id',
  `company_name` varchar(100)  DEFAULT '' COMMENT '企业名称',
  `server_id` bigint NULL DEFAULT 0 COMMENT 'IM Server ID',
  `server_name` varchar(100)  DEFAULT '' COMMENT 'IM Server 名称',
  `guid` varchar(36)  DEFAULT '' COMMENT '_guid',
  `part_guid` varchar(36)  DEFAULT '' COMMENT 'PartGUID',
  `number` int NOT NULL DEFAULT 0 COMMENT 'Number',
  `name` varchar(50)  DEFAULT '' COMMENT 'Name',
  `description` varchar(200)  DEFAULT '' COMMENT 'Description',
  `dims` varchar(200)  DEFAULT '' COMMENT 'Dims',
  `deleted` tinyint NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for im_drawing
-- ----------------------------
-- DROP TABLE IF EXISTS `im_drawing`;
CREATE TABLE IF NOT EXISTS `im_drawing`  (
  `id` bigint NOT NULL COMMENT 'id',
  `company_id` bigint NULL DEFAULT 0 COMMENT '企业id',
  `company_name` varchar(100)  DEFAULT '' COMMENT '企业名称',
  `server_id` bigint NULL DEFAULT 0 COMMENT 'IM Server ID',
  `server_name` varchar(100)  DEFAULT '' COMMENT 'IM Server 名称',
  `part_guid` varchar(36)  DEFAULT '' COMMENT '所属partGuid',
  `dims_count` int NOT NULL DEFAULT 0 COMMENT 'DimsCount',
  `drawing_file` varchar(36)  DEFAULT '' COMMENT 'DrawingFile',
  `guid` varchar(36)  DEFAULT '' COMMENT 'GUID',
  `notes` varchar(500)  DEFAULT '' COMMENT 'Notes',
  `pdf_page_no` tinyint NULL DEFAULT 0 COMMENT 'PdfPageNo',
  `revision` varchar(5)  DEFAULT '' COMMENT 'Revision',
  `sheet_name` varchar(100)  DEFAULT '' COMMENT 'SheetName',
  `sheet_no` varchar(10)  DEFAULT '' COMMENT 'SheetNo',
  `title` varchar(100)  DEFAULT '' COMMENT 'Title',
  `version` varchar(20)  DEFAULT '' COMMENT 'Version',
  `pdf_url` varchar(500)  DEFAULT '' COMMENT '文件地址',
  `image_url` varchar(500)  DEFAULT '' COMMENT '图片地址',
  `image_dim_url` varchar(500)  DEFAULT '' COMMENT '图片地址',
  `image_width` int NULL DEFAULT 0 COMMENT '图纸图片宽度',
  `image_height` int NULL DEFAULT 0 COMMENT '图纸图片高度',
  `drawing_width` int NULL DEFAULT 0 COMMENT '图纸宽度',
  `drawing_height` int NULL DEFAULT 0 COMMENT '图纸高度',
  `flag` varchar(32)  DEFAULT '' COMMENT '标识符',
  `update_time` int NULL DEFAULT 0 COMMENT '更新时间',
  `deleted` tinyint NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `idx_drawing_flag` (`flag`),
  KEY `idx_drawing_guid` (`guid`)
);

-- ----------------------------
-- Table structure for im_gage
-- ----------------------------
-- DROP TABLE IF EXISTS `im_gage`;
CREATE TABLE IF NOT EXISTS `im_gage`  (
  `id` bigint NOT NULL COMMENT 'id',
  `company_id` bigint NULL DEFAULT 0 COMMENT '企业id',
  `company_name` varchar(100)  DEFAULT '' COMMENT '企业名称',
  `server_id` bigint NULL DEFAULT 0 COMMENT 'IM Server ID',
  `server_name` varchar(100)  DEFAULT '' COMMENT 'IM Server 名称',
  `guid` varchar(36)  DEFAULT '' COMMENT 'GUID',
  `category_code` varchar(36)  DEFAULT '' COMMENT 'CategoryCode',
  `gage_id` varchar(36)  DEFAULT '' COMMENT 'GageID',
  `name` varchar(200)  DEFAULT '' COMMENT 'Name',
  `manufacturer` varchar(200)  DEFAULT '' COMMENT 'manufacturer',
  `description` varchar(200)  DEFAULT '' COMMENT 'Description',
  `serial_number` varchar(200)  DEFAULT '' COMMENT 'SerialNumber',
  `status` tinyint NULL DEFAULT 0 COMMENT 'Status',
  `status_text` varchar(100)  DEFAULT '' COMMENT 'StatusText',
  `purchase_date` varchar(50)  DEFAULT '' COMMENT 'PurchaseDate',
  `retirement_date` varchar(50)  DEFAULT '' COMMENT 'RetirementDate',
  `owner_company_guid` varchar(36)  DEFAULT '' COMMENT 'OwnerCompanyGUID',
  `location_guid` varchar(36)  DEFAULT '' COMMENT 'LocationGUID',
  `storage_place_guid` varchar(36)  DEFAULT '' COMMENT 'StoragePlaceGUID',
  `current_place_guid` varchar(36)  DEFAULT '' COMMENT 'CurrentPlaceGUID',
  `current_user_guid` varchar(36)  DEFAULT '' COMMENT 'CurrentUserGUID',
  `tracking_status` tinyint NULL DEFAULT 0 COMMENT 'TrackingStatus',
  `tracking_status_text` varchar(50)  DEFAULT '' COMMENT 'TrackingStatusText',
  `supplier_guid` varchar(36)  DEFAULT '' COMMENT 'SupplierGUID',
  `maintenance_notes` varchar(500)  DEFAULT '' COMMENT 'MaintenanceNotes',
  `last_service_date` varchar(50)  DEFAULT '' COMMENT 'LastServiceDate',
  `zeroed_date` varchar(50)  DEFAULT '' COMMENT 'ZeroedDate',
  `calibration_vendor_guid` varchar(36)  DEFAULT '' COMMENT 'CalibrationVendorGUID',
  `last_calibrated_by_guid` varchar(36)  DEFAULT '' COMMENT 'LastCalibratedByGUID',
  `next_due_date` varchar(50)  DEFAULT '' COMMENT 'NextDueDate',
  `last_calibration_date` varchar(50)  DEFAULT '' COMMENT 'LastCalibrationDate',
  `last_calibration_result` int NOT NULL DEFAULT 0 COMMENT 'LastCalibrationResult',
  `last_calibration_result_text` varchar(50)  DEFAULT '' COMMENT 'LastCalibrationResultText',
  `calibration_frequency` int NOT NULL DEFAULT 0 COMMENT 'CalibrationFrequency',
  `calibration_interval` int NOT NULL DEFAULT 0 COMMENT 'CalibrationInterval',
  `calibration_interval_text` varchar(200)  DEFAULT '' COMMENT 'CalibrationIntervalText',
  `usage_count` int NOT NULL DEFAULT 0 COMMENT 'UsageCount',
  `cert_number` varchar(200)  DEFAULT '' COMMENT 'CertNumber',
  `cert_exp_date` varchar(50)  DEFAULT '' COMMENT 'CertExpDate',
  `units` int NOT NULL DEFAULT 0 COMMENT 'Units',
  `units_text` varchar(100)  DEFAULT '' COMMENT 'UnitsText',
  `resolution` varchar(200)  DEFAULT '' COMMENT 'Resolution',
  `uncertainty` varchar(200)  DEFAULT '' COMMENT 'Uncertainty',
  `op_range` varchar(200)  DEFAULT '' COMMENT 'OpRange',
  `upper_tol` varchar(50)  DEFAULT '' COMMENT 'UpperTol',
  `lower_tol` varchar(50)  DEFAULT '' COMMENT 'LowerTol',
  `notes` varchar(500)  DEFAULT '' COMMENT 'Notes',
  `receiver_guid` varchar(36)  DEFAULT '' COMMENT 'ReceiverGUID',
  `device_id` varchar(36)  DEFAULT '' COMMENT 'DeviceID',
  `erp_id` varchar(36)  DEFAULT '' COMMENT 'ERPID',
  `barcode_id` varchar(36)  DEFAULT '' COMMENT 'BarcodeID',
  `exp_status` int NOT NULL DEFAULT 0 COMMENT 'ExpStatus',
  `exp_status_text` varchar(50)  DEFAULT '' COMMENT 'exp_status_text',
  `flag` varchar(32)  DEFAULT '' COMMENT '特征值',
  `update_time` int NULL DEFAULT 0 COMMENT '更新时间',
  `deleted` tinyint NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `idx_gage_flag` (`flag`),
  KEY `idx_gage_guid` (`guid`)
);

-- ----------------------------
-- Table structure for im_gage_category
-- ----------------------------
-- DROP TABLE IF EXISTS `im_gage_category`;
CREATE TABLE IF NOT EXISTS `im_gage_category`  (
  `id` bigint NOT NULL COMMENT 'id',
  `company_id` bigint NULL DEFAULT 0 COMMENT '企业id',
  `company_name` varchar(100)  DEFAULT '' COMMENT '企业名称',
  `server_id` bigint NULL DEFAULT 0 COMMENT 'IM Server ID',
  `server_name` varchar(100)  DEFAULT '' COMMENT 'IM Server 名称',
  `category_code` varchar(200)  DEFAULT '' COMMENT '类目Code',
  `name` varchar(200)  DEFAULT '' COMMENT '类目名称',
  `update_time` int NULL DEFAULT 0 COMMENT '更新时间',
  `deleted` tinyint NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `im_keyset`  (
  `id` bigint NOT NULL COMMENT 'id',
  `part_guid` varchar(36)  DEFAULT '' COMMENT 'Part GUID',
  `guid` varchar(36)  DEFAULT '' COMMENT 'GUID',
  `server_id` bigint NULL DEFAULT 0 COMMENT 'IM Server ID',
  `name` varchar(200)  DEFAULT '' COMMENT '名称',
  `update_time` int NULL DEFAULT 0 COMMENT '更新时间',
  `deleted` tinyint NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`)
);
CREATE TABLE IF NOT EXISTS `im_character_designator`  (
  `id` bigint NOT NULL COMMENT 'id',
  `part_guid` varchar(36)  DEFAULT '' COMMENT 'Part GUID',
  `guid` varchar(36)  DEFAULT '' COMMENT 'GUID',
  `server_id` bigint NULL DEFAULT 0 COMMENT 'IM Server ID',
  `name` varchar(200)  DEFAULT '' COMMENT '名称',
  `update_time` int NULL DEFAULT 0 COMMENT '更新时间',
  `deleted` tinyint NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`)
);
CREATE TABLE IF NOT EXISTS `im_dimension_type`  (
  `id` bigint NOT NULL COMMENT 'id',
  `part_guid` varchar(36)  DEFAULT '' COMMENT 'Part GUID',
  `guid` varchar(36)  DEFAULT '' COMMENT 'GUID',
  `server_id` bigint NULL DEFAULT 0 COMMENT 'IM Server ID',
  `name` varchar(200)  DEFAULT '' COMMENT '名称',
  `update_time` int NULL DEFAULT 0 COMMENT '更新时间',
  `deleted` tinyint NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`)
);
-- ----------------------------
-- Table structure for im_insp_center
-- ----------------------------
-- DROP TABLE IF EXISTS `im_insp_center`;
CREATE TABLE IF NOT EXISTS `im_insp_center`  (
  `id` bigint NOT NULL COMMENT 'id',
  `company_id` bigint NULL DEFAULT 0 COMMENT '企业id',
  `company_name` varchar(100)  DEFAULT '' COMMENT '企业名称',
  `server_id` bigint NULL DEFAULT 0 COMMENT 'IM Server ID',
  `server_name` varchar(100)  DEFAULT '' COMMENT 'IM Server 名称',
  `guid` varchar(36) DEFAULT '' COMMENT 'GUID',
  `insp_center_id` varchar(36)  DEFAULT '' COMMENT 'InspCenterID',
  `name` varchar(200)  DEFAULT '' COMMENT 'Name',
  `company_guid` varchar(36)  DEFAULT '' COMMENT 'CompanyGUID',
  `description` varchar(200)  DEFAULT '' COMMENT 'Description',
  `erp_id` varchar(36)  DEFAULT '' COMMENT 'ERPID',
  `barcode_id` varchar(36)  DEFAULT '' COMMENT 'BarcodeID',
  `flag` varchar(32)  DEFAULT '' COMMENT '特征值',
  `update_time` int NULL DEFAULT 0 COMMENT '更新时间',
  `deleted` tinyint NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `idx_insp_center_flag` (`flag`),
  KEY `idx_insp_center_guid` (`guid`)
);

-- ----------------------------
-- Table structure for im_job
-- ----------------------------
-- DROP TABLE IF EXISTS `im_job`;
CREATE TABLE IF NOT EXISTS `im_job`  (
  `id` bigint NOT NULL COMMENT 'id',
  `company_id` bigint NULL DEFAULT 0 COMMENT '企业id',
  `company_name` varchar(100)  DEFAULT '' COMMENT '企业名称',
  `server_id` bigint NULL DEFAULT 0 COMMENT 'IM Server ID',
  `server_name` varchar(100)  DEFAULT '' COMMENT 'IM Server 名称',
  `aql_mode` tinyint NULL DEFAULT 0 COMMENT 'AQLMode',
  `aql_mode_text` varchar(100)  DEFAULT '' COMMENT 'AQLModeText',
  `aql_table_guid` varchar(36)  DEFAULT '' COMMENT 'AQLTableGUID',
  `activation_date` datetime NULL DEFAULT NULL COMMENT 'ActivationDate',
  `barcode_id` varchar(36)  DEFAULT '' COMMENT 'BarcodeID',
  `delivery_date` datetime NULL DEFAULT NULL COMMENT 'DeliveryDate',
  `erp_id` varchar(36)  DEFAULT '' COMMENT 'ERPID',
  `guid` varchar(36)  DEFAULT '' COMMENT 'GUID',
  `inspection_level` tinyint NULL DEFAULT 0 COMMENT 'InspectionLevel',
  `inspection_level_text` varchar(200)  DEFAULT '' COMMENT 'InspectionLevelText',
  `number` varchar(200)  DEFAULT '' COMMENT 'Number',
  `part_deleted` tinyint NULL DEFAULT 0 COMMENT 'PartDeleted',
  `part_guid` varchar(36)  DEFAULT '' COMMENT 'PartGUID',
  `quantity` int NULL DEFAULT 0 COMMENT 'Quantity',
  `revision` varchar(100)  DEFAULT '' COMMENT 'Revision',
  `status` tinyint NULL DEFAULT 0 COMMENT 'Status',
  `status_text` varchar(100)  DEFAULT '' COMMENT 'StatusText',
  `title` varchar(100) NOT NULL DEFAULT '' COMMENT 'Title',
  `total_failed_lots` int NOT NULL DEFAULT 0 COMMENT 'TotalFailedLots',
  `total_failed_samples` int NOT NULL DEFAULT 0 COMMENT 'TotalFailedSamples',
  `total_lots` int NOT NULL DEFAULT 0 COMMENT 'TotalLots',
  `total_samples` int NOT NULL DEFAULT 0 COMMENT 'TotalSamples',
  `flag` varchar(32)  DEFAULT '' COMMENT '特征值',
  `handle_state` BOOLEAN NULL DEFAULT FALSE COMMENT 'false待同步 true已同步',
  `update_time` int NULL DEFAULT 0 COMMENT '更新时间',
  `customer_guid` varchar(36)  DEFAULT '' COMMENT '所属企业GUID',
  `part_name` varchar(100)  DEFAULT '' COMMENT '所属零件名称',
  `part_number` varchar(100)  DEFAULT '' COMMENT '所属零件编号',
  `part_revision` varchar(5)  DEFAULT '' COMMENT '所属零件版本',
  `aql_table_name` varchar(50)  DEFAULT '' COMMENT 'aql抽样方案Name',
  `deleted` tinyint NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `idx_job_flag` (`flag`),
  KEY `idx_job_guid` (`guid`)
);

-- ----------------------------
-- Table structure for im_location
-- ----------------------------
-- DROP TABLE IF EXISTS `im_location`;
CREATE TABLE IF NOT EXISTS `im_location`  (
  `id` bigint NOT NULL COMMENT 'id',
  `company_id` bigint NULL DEFAULT 0 COMMENT '企业id',
  `company_name` varchar(100)  DEFAULT '' COMMENT '企业名称',
  `server_id` bigint NULL DEFAULT 0 COMMENT 'IM Server ID',
  `server_name` varchar(100)  DEFAULT '' COMMENT 'IM Server 名称',
  `guid` varchar(36)  DEFAULT '' COMMENT 'GUID',
  `company_guid` varchar(36)  DEFAULT '' COMMENT 'CompanyGUID',
  `type` tinyint NULL DEFAULT 0 COMMENT 'Type',
  `type_text` varchar(50)  DEFAULT '' COMMENT 'TypeText',
  `code` varchar(100)  DEFAULT '' COMMENT 'Code',
  `name` varchar(100)  DEFAULT '' COMMENT 'Name',
  `phone` varchar(50)  DEFAULT '' COMMENT 'Phone',
  `email` varchar(50)  DEFAULT '' COMMENT 'Email',
  `website` varchar(100)  DEFAULT '' COMMENT 'Website',
  `description` varchar(200)  DEFAULT '' COMMENT 'Description',
  `erp_id` varchar(36)  DEFAULT '' COMMENT 'ERPID',
  `barcode_id` varchar(36)  DEFAULT '' COMMENT 'barcodeID',
  `flag` varchar(32)  DEFAULT '' COMMENT '特征值',
  `update_time` int NULL DEFAULT 0 COMMENT '更新时间',
  `deleted` tinyint NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `idx_location_flag` (`flag`),
  KEY `idx_location_guid` (`guid`)
);

-- ----------------------------
-- Table structure for im_lot
-- ----------------------------
-- DROP TABLE IF EXISTS `im_lot`;
CREATE TABLE IF NOT EXISTS `im_lot`  (
  `id` bigint NOT NULL COMMENT 'id',
  `company_id` bigint NULL DEFAULT 0 COMMENT '企业id',
  `company_name` varchar(100)  DEFAULT '' COMMENT '企业名称',
  `server_id` bigint NULL DEFAULT 0 COMMENT 'IM Server ID',
  `server_name` varchar(100)  DEFAULT '' COMMENT 'IM Server 名称',
  `aql_table_guid` varchar(36)  DEFAULT '' COMMENT 'AQLTableGUID',
  `acceptance_status` varchar(10)  DEFAULT '' COMMENT 'AcceptanceStatus',
  `barcode_id` varchar(36)  DEFAULT '' COMMENT 'BarcodeID',
  `code_letter` varchar(5)  DEFAULT '' COMMENT 'CodeLetter',
  `due_date` datetime NULL DEFAULT NULL COMMENT 'DueDate',
  `erp_id` varchar(36)  DEFAULT '' COMMENT 'ERPID',
  `guid` varchar(36)  DEFAULT '' COMMENT 'GUID',
  `hours_per_shift` int NOT NULL DEFAULT 0 COMMENT 'HoursPerShift',
  `inspection_level` int NOT NULL DEFAULT 0 COMMENT 'InspectionLevel',
  `inspection_level_text` varchar(100)  DEFAULT '' COMMENT 'InspectionLevelText',
  `job_guid` varchar(36)  DEFAULT '' COMMENT 'JobGUID',
  `number` varchar(50)  DEFAULT '' COMMENT 'Number',
  `part_guid` varchar(36)  DEFAULT '' COMMENT 'PartGUID',
  `quality_level` varchar(36)  DEFAULT '' COMMENT 'QualityLevel',
  `quality_stage` int NOT NULL DEFAULT 0 COMMENT 'QualityStage',
  `quality_stage_text` varchar(50)  DEFAULT '' COMMENT 'QualityStageText',
  `sample_size` int NOT NULL DEFAULT 0 COMMENT 'SampleSize',
  `samples_per_hour` int NOT NULL DEFAULT 0 COMMENT 'SamplesPerHour',
  `size` int NOT NULL DEFAULT 0 COMMENT 'Size',
  `start_date` datetime NULL DEFAULT NULL COMMENT 'StartDate',
  `status` tinyint NULL DEFAULT 0 COMMENT 'Status',
  `status_text` varchar(50)  DEFAULT '' COMMENT 'StatusText',
  `total_failed_samples` int NOT NULL DEFAULT 0 COMMENT 'TotalFailedSamples',
  `total_samples` int NOT NULL DEFAULT 0 COMMENT 'TotalSamples',
  `flag` varchar(32)  DEFAULT '' COMMENT '特征值',
  `handle_state` BOOLEAN NULL DEFAULT FALSE COMMENT 'false待同步 true已同步',
  `update_time` int NULL DEFAULT 0 COMMENT '更新时间',
  `aql_table_name` varchar(50)  DEFAULT '' COMMENT 'AQL抽样方案名称',
  `deleted` tinyint NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `idx_lot_flag` (`flag`),
  KEY `idx_lot_guid` (`guid`)
);

-- ----------------------------
-- Table structure for im_machine
-- ----------------------------
-- DROP TABLE IF EXISTS `im_machine`;
CREATE TABLE IF NOT EXISTS `im_machine`  (
  `id` bigint NOT NULL COMMENT 'id',
  `company_id` bigint NULL DEFAULT 0 COMMENT '企业id',
  `company_name` varchar(100)  DEFAULT '' COMMENT '企业名称',
  `server_id` bigint NULL DEFAULT 0 COMMENT 'IM Server ID',
  `server_name` varchar(100)  DEFAULT '' COMMENT 'IM Server 名称',
  `guid` varchar(36)  DEFAULT '' COMMENT 'GUID',
  `work_cell_guid` varchar(36)  DEFAULT '' COMMENT 'WorkCellGUID',
  `machine_id` varchar(36)  DEFAULT '' COMMENT 'PlaceGUID',
  `title` varchar(100)  DEFAULT '' COMMENT 'Title',
  `brand` varchar(100)  DEFAULT '' COMMENT 'Brand',
  `serial_no` varchar(100)  DEFAULT '' COMMENT 'SerialNo',
  `status` tinyint NULL DEFAULT 0 COMMENT 'Status',
  `status_text` varchar(50)  DEFAULT '' COMMENT 'StatusText',
  `notes` varchar(200)  DEFAULT '' COMMENT 'Notes',
  `erp_id` varchar(36)  DEFAULT '' COMMENT 'ERPID',
  `barcode_id` varchar(36)  DEFAULT '' COMMENT 'barcodeID',
  `flag` varchar(32)  DEFAULT '' COMMENT '特征值',
  `update_time` int NULL DEFAULT 0 COMMENT '更新时间',
  `deleted` tinyint NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `idx_machine_flag` (`flag`),
  KEY `idx_machine_guid` (`guid`)
);

-- ----------------------------
-- Table structure for im_operation
-- ----------------------------
-- DROP TABLE IF EXISTS `im_operation`;
CREATE TABLE IF NOT EXISTS `im_operation`  (
  `id` bigint NOT NULL COMMENT 'id',
  `company_id` bigint NULL DEFAULT 0 COMMENT '企业id',
  `company_name` varchar(100)  DEFAULT '' COMMENT '企业名称',
  `server_id` bigint NULL DEFAULT 0 COMMENT 'IM Server ID',
  `server_name` varchar(100)  DEFAULT '' COMMENT 'IM Server 名称',
  `guid` varchar(36)  DEFAULT '' COMMENT 'GUID',
  `part_guid` varchar(36)  DEFAULT '' COMMENT 'PartGUID',
  `work_cell_guid` varchar(36)  DEFAULT '' COMMENT 'WorkCellGUID',
  `machine_guid` varchar(36)  DEFAULT '' COMMENT 'MachineGUID',
  `code` varchar(100)  DEFAULT '' COMMENT 'Code',
  `title` varchar(100)  DEFAULT '' COMMENT 'Title',
  `description` varchar(200)  DEFAULT '' COMMENT 'Description',
  `flag` varchar(32)  DEFAULT '' COMMENT '特征值',
  `update_time` int NULL DEFAULT 0 COMMENT '更新时间',
  `deleted` tinyint NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `idx_operation_flag` (`flag`),
  KEY `idx_operation_guid` (`guid`)
);

-- ----------------------------
-- Table structure for im_part
-- ----------------------------
-- DROP TABLE IF EXISTS `im_part`;
CREATE TABLE IF NOT EXISTS `im_part`  (
  `id` bigint NOT NULL COMMENT 'id',
  `company_id` bigint NULL DEFAULT 0 COMMENT '企业id',
  `company_name` varchar(100)  DEFAULT '' COMMENT '企业名称',
  `server_id` bigint NULL DEFAULT 0 COMMENT 'IM Server ID',
  `server_name` varchar(100)  DEFAULT '' COMMENT 'IM Server 名称',
  `barcode_id` varchar(36)  DEFAULT '' COMMENT 'barcodeID',
  `customer_guid` varchar(36)  DEFAULT '' COMMENT 'CustomerGUID',
  `erp_id` varchar(36)  DEFAULT '' COMMENT 'ERPID',
  `guid` varchar(36)  DEFAULT '' COMMENT 'GUID',
  `is_archived` tinyint NULL DEFAULT 0 COMMENT 'IsArchived',
  `is_deleted` tinyint NULL DEFAULT 0 COMMENT 'IsDeleted',
  `last_modification_date` datetime NULL DEFAULT NULL COMMENT 'LastModificationDate',
  `part_comments` varchar(200)  DEFAULT '' COMMENT 'PartComments',
  `part_name` varchar(200)  DEFAULT '' COMMENT 'PartName',
  `part_number` varchar(100)  DEFAULT '' COMMENT 'PartNumber',
  `part_revision_level` varchar(5)  DEFAULT '' COMMENT 'PartRevisionLevel',
  `flag` varchar(32)  DEFAULT '' COMMENT '特征值',
  `handle_state` BOOLEAN NULL DEFAULT FALSE COMMENT 'false待同步 true已同步',
  `update_time` int NULL DEFAULT 0 COMMENT '更新时间',
  `job_total` int NULL DEFAULT 0 COMMENT 'JOB数量',
  `lot_total` int NULL DEFAULT 0 COMMENT 'LOT总数量',
  `sample_total` int NULL DEFAULT 0 COMMENT 'SAMPLE数量',
  `customer_name` varchar(50)  DEFAULT '' COMMENT '企业名称',
  `deleted` tinyint NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `idx_part_flag` (`flag`),
  KEY `idx_part_guid` (`guid`)
);

-- ----------------------------
-- Table structure for im_place
-- ----------------------------
-- DROP TABLE IF EXISTS `im_place`;
CREATE TABLE IF NOT EXISTS `im_place`  (
  `id` bigint NOT NULL COMMENT 'id',
  `company_id` bigint NULL DEFAULT 0 COMMENT '企业id',
  `company_name` varchar(100)  DEFAULT '' COMMENT '企业名称',
  `server_id` bigint NULL DEFAULT 0 COMMENT 'IM Server ID',
  `server_name` varchar(100)  DEFAULT '' COMMENT 'IM Server 名称',
  `guid` varchar(36)  DEFAULT '' COMMENT 'GUID',
  `company_guid` varchar(36)  DEFAULT '' COMMENT 'CompanyGUID',
  `location_guid` varchar(36)  DEFAULT '' COMMENT 'LocationGUID',
  `name` varchar(100)  DEFAULT '' COMMENT 'Name',
  `phone` varchar(50)  DEFAULT '' COMMENT 'Phone',
  `email` varchar(50)  DEFAULT '' COMMENT 'Email',
  `website` varchar(100)  DEFAULT '' COMMENT 'Website',
  `description` varchar(200)  DEFAULT '' COMMENT 'Description',
  `erp_id` varchar(36)  DEFAULT '' COMMENT 'ERPID',
  `barcode_id` varchar(36)  DEFAULT '' COMMENT 'barcodeID',
  `flag` varchar(32)  DEFAULT '' COMMENT '特征值',
  `update_time` int NULL DEFAULT 0 COMMENT '更新时间',
  `deleted` tinyint NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `idx_place_flag` (`flag`),
  KEY `idx_place_guid` (`guid`)
);

-- ----------------------------
-- Table structure for im_placeholder
-- ----------------------------
-- DROP TABLE IF EXISTS `im_placeholder`;
CREATE TABLE IF NOT EXISTS `im_placeholder`  (
  `id` bigint NOT NULL COMMENT 'id',
  `company_id` bigint NULL DEFAULT 0 COMMENT '企业id',
  `company_name` varchar(100)  DEFAULT '' COMMENT '企业名称',
  `server_id` bigint NULL DEFAULT 0 COMMENT 'IM Server ID',
  `server_name` varchar(100)  DEFAULT '' COMMENT 'IM Server 名称',
  `guid` varchar(36)  DEFAULT '' COMMENT 'GUID',
  `sample_guid` varchar(36)  DEFAULT '' COMMENT 'SampleGUID',
  `dim_guid` varchar(36)  DEFAULT '' COMMENT 'DimGUID',
  `res_no` varchar(100)  DEFAULT '' COMMENT 'ResNo',
  `nominal` decimal(9, 4) NOT NULL DEFAULT 0.0000 COMMENT 'Nominal',
  `upper_tol` decimal(9, 4) NOT NULL DEFAULT 0.0000 COMMENT 'UpperTol',
  `lower_tol` decimal(9, 4) NOT NULL DEFAULT 0.0000 COMMENT 'LowerTol',
  `deviation` decimal(9, 4) NOT NULL DEFAULT 0.0000 COMMENT 'Deviation',
  `error` decimal(9, 4) NOT NULL DEFAULT 0.0000 COMMENT 'Error',
  `status` tinyint NULL DEFAULT 0 COMMENT 'Status',
  `status_text` varchar(50)  DEFAULT '' COMMENT 'StatusText',
  `flag` varchar(32)  DEFAULT '' COMMENT '特征值',
  `update_time` int NULL DEFAULT 0 COMMENT '更新时间',
  `deleted` tinyint NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `idx_placeholder_flag` (`flag`),
  KEY `idx_placeholder_guid` (`guid`)
);

-- ----------------------------
-- Table structure for im_procedure
-- ----------------------------
-- DROP TABLE IF EXISTS `im_procedure`;
CREATE TABLE IF NOT EXISTS `im_procedure`  (
  `id` bigint NOT NULL COMMENT 'id',
  `company_id` bigint NULL DEFAULT 0 COMMENT '企业id',
  `company_name` varchar(100)  DEFAULT '' COMMENT '企业名称',
  `server_id` bigint NULL DEFAULT 0 COMMENT 'IM Server ID',
  `server_name` varchar(100)  DEFAULT '' COMMENT 'IM Server 名称',
  `guid` varchar(36)  DEFAULT '' COMMENT 'GUID',
  `part_guid` varchar(36)  DEFAULT '' COMMENT 'PartGUID',
  `number` int NOT NULL DEFAULT 0 COMMENT 'Number',
  `code` varchar(200)  DEFAULT '' COMMENT 'Code',
  `title` varchar(200)  DEFAULT '' COMMENT 'Title',
  `type` int NOT NULL DEFAULT 0 COMMENT 'Type',
  `type_text` varchar(200)  DEFAULT '' COMMENT 'TypeText',
  `status` int NOT NULL DEFAULT 0 COMMENT 'Status',
  `status_text` varchar(200)  DEFAULT '' COMMENT 'StatusText',
  `insp_center_guid` varchar(36)  DEFAULT '' COMMENT 'InspCenterGUID',
  `description` varchar(200)  DEFAULT '' COMMENT 'Description',
  `erp_id` varchar(36)  DEFAULT '' COMMENT 'ERPID',
  `barcode_id` varchar(36)  DEFAULT '' COMMENT 'BarcodeID',
  `flag` varchar(32)  DEFAULT '' COMMENT '特征值',
  `handle_state` BOOLEAN NULL DEFAULT FALSE COMMENT 'false待同步 true已同步',
  `update_time` int NULL DEFAULT 0 COMMENT '更新时间',
  `deleted` tinyint NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `idx_procedure_flag` (`flag`),
  KEY `idx_procedure_guid` (`guid`)
);

-- ----------------------------
-- Table structure for im_result
-- ----------------------------
-- DROP TABLE IF EXISTS `im_result`;
CREATE TABLE IF NOT EXISTS `im_result`  (
  `id` bigint NOT NULL COMMENT 'id',
  `company_id` bigint NULL DEFAULT 0 COMMENT '企业id',
  `company_name` varchar(100)  DEFAULT '' COMMENT '企业名称',
  `server_id` bigint NULL DEFAULT 0 COMMENT 'IM Server ID',
  `server_name` varchar(100)  DEFAULT '' COMMENT 'IM Server 名称',
  `guid` varchar(36)  DEFAULT '' COMMENT '_guid',
  `part_guid` varchar(36)  DEFAULT '' COMMENT 'PartGUID',
  `part_name` varchar(200) DEFAULT '' COMMENT 'PartName',
  `part_number` varchar(100) DEFAULT '' COMMENT 'PartNumber',
  `part_revision_level` varchar(5) DEFAULT '' COMMENT 'PartRevisionLevel',
  `job_guid` varchar(36)  DEFAULT '' COMMENT 'JobGUID',
  `job_number` varchar(200) DEFAULT '' COMMENT 'JobNumber',
  `job_title` varchar(100) NOT NULL DEFAULT '' COMMENT 'JobTitle',
  `job_revision` varchar(10) DEFAULT '' COMMENT 'JobRevision',
  `lot_guid` varchar(36) DEFAULT '' COMMENT 'LotGUID',
  `lot_number` varchar(50) DEFAULT '' COMMENT 'LotNumber',
  `sample_guid` varchar(36) DEFAULT '' COMMENT 'SampleGUID',
  `sample_serial_number` varchar(100) DEFAULT '' COMMENT 'SampleSerialNumber',
  `dim_guid` varchar(36) DEFAULT '' COMMENT 'Dim_guid',
  `dim_no` varchar(50) DEFAULT '' COMMENT 'DimNo',
  `gage_guid` varchar(36) DEFAULT '' COMMENT 'GageGUID',
  `gage_name` varchar(50) DEFAULT '' COMMENT 'GageName',
  `measured_by_guid` varchar(36) DEFAULT '' COMMENT 'MeasuredByGUID',
  `measured_by_name` varchar(36) DEFAULT '' COMMENT 'MeasuredByName',
  `res_no` varchar(100)  DEFAULT '' COMMENT 'ResNo',
  `nominal` decimal(9,4) NOT NULL DEFAULT '0.0000' COMMENT 'Nominal',
  `data` decimal(9, 4) NOT NULL DEFAULT 0.0000 COMMENT 'Data',
  `upper_tol` decimal(9, 4) NOT NULL DEFAULT 0.0000 COMMENT 'UpperTol',
  `lower_tol` decimal(9, 4) NOT NULL DEFAULT 0.0000 COMMENT 'LowerTol',
  `deviation` decimal(9, 4) NOT NULL DEFAULT 0.0000 COMMENT 'Deviation',
  `error` decimal(9, 4) NOT NULL DEFAULT 0.0000 COMMENT 'Error',
  `status` int NOT NULL DEFAULT 0 COMMENT 'Status',
  `status_text` varchar(50)  DEFAULT '' COMMENT 'StatusText',
  `inspected_date` datetime NULL DEFAULT NULL COMMENT 'InspectedDate',
  `comment` varchar(50)  DEFAULT '' COMMENT 'Comment',
  `ncr_guid` varchar(36)  DEFAULT '' COMMENT 'NCRGUID',
  `flag` varchar(32)  DEFAULT '' COMMENT '特征值',
  `failed_flag` varchar(32)  DEFAULT '' COMMENT '特征值',
  `update_time` int NULL DEFAULT 0 COMMENT '更新时间',
  `deleted` tinyint NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `idx_result_flag` (`flag`),
  KEY `idx_result_guid` (`guid`)
);

-- ----------------------------
-- Table structure for im_sample
-- ----------------------------
-- DROP TABLE IF EXISTS `im_sample`;
CREATE TABLE IF NOT EXISTS `im_sample`  (
  `id` bigint NOT NULL COMMENT 'id',
  `company_id` bigint NULL DEFAULT 0 COMMENT '企业id',
  `company_name` varchar(100)  DEFAULT '' COMMENT '企业名称',
  `server_id` bigint NULL DEFAULT 0 COMMENT 'IM Server ID',
  `server_name` varchar(100)  DEFAULT '' COMMENT 'IM Server 名称',
  `barcode_id` varchar(36)  DEFAULT '' COMMENT 'BarcodeID',
  `cavity_number` int NOT NULL DEFAULT 0 COMMENT 'CavityNumber',
  `creation_date` datetime NULL DEFAULT NULL COMMENT 'CreationDate',
  `erp_id` varchar(36)  DEFAULT '' COMMENT 'ERPID',
  `fixture_number` varchar(50)  DEFAULT '' COMMENT 'FixtureNumber',
  `guid` varchar(36)  DEFAULT '' COMMENT 'GUID',
  `job_guid` varchar(36)  DEFAULT '' COMMENT 'JobGUID',
  `lot_guid` varchar(36)  DEFAULT '' COMMENT 'LotGUID',
  `machine_number` varchar(100)  DEFAULT '' COMMENT 'MachineNumber',
  `part_guid` varchar(36)  DEFAULT '' COMMENT 'PartGUID',
  `results` int NOT NULL DEFAULT 0 COMMENT 'Results',
  `serial_number` varchar(100)  DEFAULT '' COMMENT 'SerialNumber',
  `status` tinyint NULL DEFAULT 0 COMMENT 'Status',
  `status_text` varchar(50)  DEFAULT '' COMMENT 'StatusText',
  `flag` varchar(32)  DEFAULT '' COMMENT '特征值',
  `handle_state` BOOLEAN NULL DEFAULT FALSE COMMENT 'false待同步 true已同步',
  `update_time` int NULL DEFAULT 0 COMMENT '更新时间',
  `deleted` tinyint NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `idx_sample_flag` (`flag`),
  KEY `idx_sample_guid` (`guid`)
);

-- ----------------------------
-- Table structure for im_work_cell
-- ----------------------------
-- DROP TABLE IF EXISTS `im_work_cell`;
CREATE TABLE IF NOT EXISTS `im_work_cell`  (
  `id` bigint NOT NULL COMMENT 'id',
  `company_id` bigint NULL DEFAULT 0 COMMENT '企业id',
  `company_name` varchar(100)  DEFAULT '' COMMENT '企业名称',
  `server_id` bigint NULL DEFAULT 0 COMMENT 'IM Server ID',
  `server_name` varchar(100)  DEFAULT '' COMMENT 'IM Server 名称',
  `guid` varchar(36)  DEFAULT '' COMMENT 'GUID',
  `company_guid` varchar(36)  DEFAULT '' COMMENT 'CompanyGUID',
  `location_guid` varchar(36)  DEFAULT '' COMMENT 'LocationGUID',
  `place_guid` varchar(36)  DEFAULT '' COMMENT 'PlaceGUID',
  `work_cell_id` varchar(36)  DEFAULT '' COMMENT 'WorkCellID',
  `description` varchar(200)  DEFAULT '' COMMENT 'Description',
  `erp_id` varchar(36)  DEFAULT '' COMMENT 'ERPID',
  `barcode_id` varchar(36)  DEFAULT '' COMMENT 'barcodeID',
  `flag` varchar(32)  DEFAULT '' COMMENT '特征值',
  `update_time` int NULL DEFAULT 0 COMMENT '更新时间',
  `deleted` tinyint NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `idx_work_cell_flag` (`flag`),
  KEY `idx_work_cell_guid` (`guid`)
);

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
-- DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE IF NOT EXISTS `oauth_client_details`  (
  `id` bigint NOT NULL,
  `client_id` varchar(50)  DEFAULT NULL COMMENT '客户端ID',
  `client_name` varchar(50)  DEFAULT '' COMMENT '客户端名称',
  `resource_ids` varchar(255)  DEFAULT NULL COMMENT '客户端所能访问的资源id集合',
  `client_secret_plain` varchar(50)  DEFAULT '' COMMENT '客户端(client)的访问密匙明文',
  `client_secret` varchar(255)  DEFAULT NULL COMMENT '用于指定客户端(client)的访问密匙',
  `scope` varchar(255)  DEFAULT NULL COMMENT '指定客户端申请的权限范围',
  `authorized_grant_types` varchar(255)  DEFAULT NULL COMMENT '指定客户端支持的grant_type',
  `web_server_redirect_uri` varchar(255)  DEFAULT NULL COMMENT '客户端的重定向URI,可为空',
  `authorities` varchar(255)  DEFAULT NULL COMMENT '指定客户端所拥有的Spring Security的权限值',
  `access_token_validity` int NULL DEFAULT NULL COMMENT '设定客户端的access_token的有效时间值(单位:秒)',
  `refresh_token_validity` int NULL DEFAULT NULL COMMENT '设定客户端的refresh_token的有效时间值(单位:秒)',
  `additional_information` text  COMMENT '预留字段',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `archived` tinyint NULL DEFAULT 0 COMMENT '用于标识客户端是否已存档(即实现逻辑删除),默认值为\"0\"(即未存档)',
  `trusted` tinyint NULL DEFAULT 0 COMMENT '设置客户端是否为受信任的,默认为\"0\"(即不受信任的,1为受信任的).',
  `autoapprove` varchar(255)  DEFAULT 'false' COMMENT '设置用户是否自动Approval操作, 默认值为 \"false\", 可选值包括 \"true\",\"false\", \"read\",\"write\".',
  `deleted` tinyint NULL DEFAULT 0 COMMENT '是否删除 0否 1是',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for rbac_account_role
-- ----------------------------
-- DROP TABLE IF EXISTS `rbac_account_role`;
CREATE TABLE IF NOT EXISTS `rbac_account_role`  (
  `id` bigint NOT NULL DEFAULT 0 COMMENT 'ID',
  `account_id` bigint  NULL DEFAULT 0 COMMENT '账户ID',
  `role_id` bigint NOT NULL DEFAULT 0 COMMENT '角色ID',
  `role_name` varchar(30)  DEFAULT '' COMMENT '角色名称',
  `role_code` varchar(30)  DEFAULT '' COMMENT '角色编码',
  `system_name` varchar(30)  DEFAULT '' COMMENT '所属系统',
  `supplier_id` bigint NULL DEFAULT NULL COMMENT '所属服务商ID',
  `supplier_name` varchar(100)  DEFAULT '' COMMENT '所属服务商名称',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `deleted` tinyint NULL DEFAULT 0 COMMENT '是否删除 0是1否',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for rbac_role
-- ----------------------------
-- DROP TABLE IF EXISTS `rbac_role`;
CREATE TABLE IF NOT EXISTS `rbac_role`  (
  `id` bigint NOT NULL COMMENT 'ID',
  `name` varchar(30)  DEFAULT '' COMMENT '角色名称',
  `code` varchar(30)  DEFAULT '' COMMENT '角色编码',
  `remark` varchar(100)  DEFAULT NULL COMMENT '备注',
  `system_name` varchar(20)  DEFAULT '' COMMENT '所属系统',
  `editor` bigint NULL DEFAULT 0 COMMENT '修改者ID',
  `is_default` BOOLEAN NULL DEFAULT FALSE COMMENT '是否默认system_name默认权限',
  `is_authorize` BOOLEAN NULL DEFAULT FALSE COMMENT '是否可直接授权',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` tinyint NULL DEFAULT 0 COMMENT '逻辑删除 0正常 1删除',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for sys_im_api_record
-- ----------------------------
DROP TABLE IF EXISTS `sys_im_api_record`;
CREATE TABLE IF NOT EXISTS `sys_im_api_record`  (
  `id` bigint NOT NULL DEFAULT 0 COMMENT 'ID',
  `company_id` bigint NULL DEFAULT 0 COMMENT '企业id',
  `company_name` varchar(100)  DEFAULT '' COMMENT '企业名称',
  `account_id` bigint NULL DEFAULT 0 COMMENT '账户id',
  `staff_id` bigint NULL DEFAULT 0 COMMENT '职员id',
  `staff_name` varchar(100)  DEFAULT '' COMMENT '职员名称',
  `server_id` bigint NULL DEFAULT 0 COMMENT 'IM Server id',
  `server_name` varchar(100)  DEFAULT '' COMMENT 'IM Server 名称',
  `server_url` varchar(200)  DEFAULT '' COMMENT 'IM Server 连接地址',
  `content` varchar(100)  DEFAULT '' COMMENT '操作内容',
  `update_time` int NULL DEFAULT 0 COMMENT '更新时间',
  `deleted` tinyint NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for sys_im_server
-- ----------------------------
-- DROP TABLE IF EXISTS `sys_im_server`;
CREATE TABLE IF NOT EXISTS `sys_im_server`  (
  `id` bigint NOT NULL COMMENT 'id',
  `company_id` bigint NULL DEFAULT 0 COMMENT '企业id',
  `company_name` varchar(100)  DEFAULT '' COMMENT '企业名称',
  `code` varchar(20)  DEFAULT '' COMMENT '编码',
  `name` varchar(100)  DEFAULT '' COMMENT '名称',
  `url` varchar(200)  DEFAULT '' COMMENT '连接地址',
  `api_version` varchar(20)  DEFAULT '' COMMENT 'API 版本',
  `state` varchar(20)  DEFAULT '' COMMENT '审核状态 自动入库无公司名称 未审核 已审核',
  `refuse_reason` varchar(100)  DEFAULT '' COMMENT '拒绝原因',
  `creator_id` bigint NULL DEFAULT 0 COMMENT '创建者ID',
  `creator` varchar(20)  DEFAULT '' COMMENT '创建者名称',
  `update_time` int NULL DEFAULT 0 COMMENT '更新时间',
  `update_status` BOOLEAN NULL DEFAULT FALSE COMMENT '更新状态',
  `create_time` int NULL DEFAULT 0 COMMENT '创建时间',
  `deleted` tinyint NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`)
);

-- ----------------------------
-- Table structure for sys_im_user
-- ----------------------------
-- DROP TABLE IF EXISTS `sys_im_user`;
CREATE TABLE IF NOT EXISTS `sys_im_user`  (
  `id` bigint NOT NULL COMMENT 'id',
  `company_id` bigint NOT NULL COMMENT '企业id',
  `company_name` varchar(100)  DEFAULT '' COMMENT '企业名称',
  `server_id` bigint NULL DEFAULT 0 COMMENT 'IM Server ID',
  `server_name` varchar(100)  DEFAULT '' COMMENT 'IM Server 名称',
  `account_id` bigint NULL DEFAULT 0 COMMENT '用户id',
  `staff_id` bigint NULL DEFAULT 0 COMMENT '职员ID',
  `staff_name` varchar(100)  DEFAULT '' COMMENT '职员姓名',
  `username` varchar(50)  DEFAULT '' COMMENT '用户名',
  `password` varchar(80)  DEFAULT '' COMMENT '登录密码',
  `state` varchar(20)  DEFAULT '' COMMENT '审核状态 自动入库无公司名称 未审核 已审核',
  `update_time` int NULL DEFAULT 0 COMMENT '更新时间',
  `create_time` int NULL DEFAULT 0 COMMENT '创建时间',
  `deleted` tinyint NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`)
);
-- DROP TABLE IF EXISTS `im_receiver`;
CREATE TABLE IF NOT EXISTS `im_receiver` (
  `id` bigint NOT NULL COMMENT 'id',
  `company_id` bigint DEFAULT '0' COMMENT '企业id',
  `company_name` varchar(100) DEFAULT '' COMMENT '企业名称',
  `server_id` bigint DEFAULT '0' COMMENT 'IM Server ID',
  `server_name` varchar(100) DEFAULT '' COMMENT 'IM Server 名称',
  `guid` varchar(36) DEFAULT '' COMMENT 'GUID',
  `name` varchar(200) DEFAULT '' COMMENT 'Name',
  `number` varchar(200) DEFAULT '' COMMENT 'Number',
  `receiver_id` varchar(36) DEFAULT '' COMMENT 'ReceiverID',
  `status` tinyint DEFAULT '0' COMMENT 'Status',
  `status_text` varchar(100) DEFAULT '' COMMENT 'StatusText',
  `attachment_type` tinyint DEFAULT '0' COMMENT 'AttachmentType',
  `attachment_type_text` varchar(100) DEFAULT '' COMMENT 'AttachmentTypeText',
  `delimeter` varchar(100) DEFAULT '' COMMENT 'Delimeter',
  `decimal_separator` varchar(36) DEFAULT '' COMMENT 'DecimalSeparator',
  `remove_ena_marker_device` varchar(36) DEFAULT '' COMMENT 'RemoveEnaMarkerDevice',
  `Remove_end_marker_value` varchar(36) DEFAULT '' COMMENT 'RemoveEndMarkerValue',
  `channel_index` int DEFAULT '0' COMMENT 'ChannelIndex',
  `value_index` int DEFAULT '0' COMMENT 'ValueIndex',
  `description` varchar(200) DEFAULT '' COMMENT 'Description',
  `flag` varchar(32) DEFAULT '' COMMENT '特征值',
  `update_time` int DEFAULT '0' COMMENT '更新时间',
  `deleted` tinyint DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
);
-- DROP TABLE IF EXISTS `im_ncr`;
CREATE TABLE IF NOT EXISTS `im_ncr` (
  `id` bigint NOT NULL COMMENT 'id',
  `company_id` bigint DEFAULT '0' COMMENT '企业id',
  `company_name` varchar(100) DEFAULT '' COMMENT '企业名称',
  `server_id` bigint DEFAULT '0' COMMENT 'IM Server ID',
  `server_name` varchar(100) DEFAULT '' COMMENT 'IM Server 名称',
  `guid` varchar(36) DEFAULT '' COMMENT 'GUID',
  `job_guid` varchar(36) DEFAULT '' COMMENT 'JobGUID',
  `job_number` varchar(200) DEFAULT '' COMMENT 'JobNumber',
  `lot_guid` varchar(36) DEFAULT '' COMMENT 'LotGUID',
  `lot_number` varchar(200) DEFAULT '' COMMENT 'LotNumber',
  `number` varchar(200) DEFAULT '' COMMENT 'Number',
  `status` tinyint DEFAULT '0' COMMENT 'Status',
  `status_text` varchar(100) DEFAULT '' COMMENT 'StatusText',
  `creation_date` datetime NULL DEFAULT NULL COMMENT 'CreationDate',
  `created_by_guid` varchar(36) DEFAULT '' COMMENT 'CreatedByGUID',
  `created_by_name` varchar(100) DEFAULT '' COMMENT 'CreatedByName',
  `response_date` datetime NULL DEFAULT NULL COMMENT 'ResponseDate',
  `assigned_to_guid` varchar(100) DEFAULT '' COMMENT 'AssignedToGUID',
  `assigned_to_name` varchar(100) DEFAULT '' COMMENT 'AssignedToName',
  `work_cell_guid` varchar(100) DEFAULT '' COMMENT 'WorkCellGUID',
  `work_cell_name` varchar(100) DEFAULT '' COMMENT 'WorkCellName',
  `insp_center_guid` varchar(100) DEFAULT '' COMMENT 'InspCenterGUID',
  `insp_center_name` varchar(100) DEFAULT '' COMMENT 'InspCenterName',
  `barcode_id` varchar(36) DEFAULT '' COMMENT 'BarcodeID',
  `erp_id` varchar(36) DEFAULT '' COMMENT 'ERPID',
  `comments` varchar(500) DEFAULT '' COMMENT 'Comments',
  `defect_codes_count` varchar(500) DEFAULT '' COMMENT 'DefectCodesCount',
  `results_count` varchar(500) DEFAULT '' COMMENT 'ResultsCount',
  `flag` varchar(32) DEFAULT '' COMMENT '特征值',
  `update_time` int DEFAULT '0' COMMENT '更新时间',
  `deleted` tinyint DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `idx_ncr_flag` (`flag`),
  KEY `idx_ncr_guid` (`guid`)
);
-- DROP TABLE IF EXISTS `im_ncr_result`;
CREATE TABLE IF NOT EXISTS `im_ncr_result` (
  `id` bigint NOT NULL COMMENT 'id',
  `company_id` bigint DEFAULT '0' COMMENT '企业id',
  `company_name` varchar(100) DEFAULT '' COMMENT '企业名称',
  `server_id` bigint DEFAULT '0' COMMENT 'IM Server ID',
  `server_name` varchar(100) DEFAULT '' COMMENT 'IM Server 名称',
  `ncr_guid` varchar(36) DEFAULT '' COMMENT 'ncr对应GUID',
  `result_guid` varchar(36) DEFAULT '' COMMENT 'ResultGUID',
  `defect_code` varchar(50) DEFAULT '' COMMENT 'DefectCode',
  `defect_value` int NOT NULL DEFAULT '0' COMMENT 'DefectValue',
  `defect_location` varchar(100) DEFAULT '' COMMENT 'DefectLocation',
  `gage_guid` varchar(36) DEFAULT '' COMMENT 'GageGUID',
  `gage_title` varchar(100) DEFAULT '' COMMENT 'GageTitle',
  `dim_no` varchar(36) DEFAULT '' COMMENT 'DimNo',
  `dim_type` varchar(50) DEFAULT '' COMMENT 'DimType',
  `tol_type` varchar(50) DEFAULT '' COMMENT 'TolType',
  `tol_class` varchar(50) DEFAULT '' COMMENT 'TolClass',
	`nominal` decimal(11,5) NOT NULL DEFAULT '0.00000' COMMENT 'Nominal',
	`lower_tol` decimal(11,5) NOT NULL DEFAULT '0.00000' COMMENT 'LowerTol',
	`upper_tol` decimal(11,5) NOT NULL DEFAULT '0.00000' COMMENT 'UpperTol',
	`note` varchar(500) DEFAULT '' COMMENT 'Note',
	`feature1` varchar(100) DEFAULT '' COMMENT 'Feature1',
	`feature2` varchar(100) DEFAULT '' COMMENT 'Feature2',
	`balloon_location` varchar(100) DEFAULT '' COMMENT 'BalloonLocation',
	`designator` varchar(100) DEFAULT '' COMMENT 'Designator',
	`sampling_mode` varchar(100) DEFAULT '' COMMENT 'SamplingMode',
	`tool_category` varchar(100) DEFAULT '' COMMENT 'ToolCategory',
  `proc_index` int NOT NULL DEFAULT '0' COMMENT 'ProcIndex',
	`proc_code` varchar(100) DEFAULT '' COMMENT 'ProcCode',
	`proc_name` varchar(100) DEFAULT '' COMMENT 'ProcName',
	`proc_comments` varchar(100) DEFAULT '' COMMENT 'ProcComments',
	`proc_ext_id` varchar(100) DEFAULT '' COMMENT 'ProcExtID',
	`proc_barcode` varchar(100) DEFAULT '' COMMENT 'ProcBarcode',
	`ic_number` varchar(100) DEFAULT '' COMMENT 'ICNumber',
	`ic_name` varchar(100) DEFAULT '' COMMENT 'ICName',
	`ic_comments` varchar(100) DEFAULT '' COMMENT 'ICComments',
	`ic_ext_id` varchar(100) DEFAULT '' COMMENT 'ICExtID',
	`ic_barcode` varchar(100) DEFAULT '' COMMENT 'ICBarcode',
  `flag` varchar(32) DEFAULT '' COMMENT '特征值',
  `update_time` int DEFAULT '0' COMMENT '更新时间',
  `deleted` tinyint DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `idx_ncr_result_flag` (`flag`),
  KEY `idx_ncr_result_guid` (`result_guid`)
);
-- DROP TABLE IF EXISTS `im_contact`;
CREATE TABLE IF NOT EXISTS `im_contact` (
  `id` bigint NOT NULL COMMENT 'id',
  `server_id` bigint DEFAULT '0' COMMENT 'IM Server ID',
  `guid` varchar(36) DEFAULT '' COMMENT 'ID',
  `company_guid` varchar(36) DEFAULT '' COMMENT 'CompanyGUID',
  `username` varchar(50) DEFAULT '' COMMENT '用户名',
  `first_name` varchar(50) DEFAULT '' COMMENT 'FirstName',
  `last_name` varchar(50) DEFAULT '' COMMENT 'LastName',
  `email` varchar(100) DEFAULT '' COMMENT 'Email',
  `photo_file_guid` varchar(36) DEFAULT '' COMMENT 'PhotoFileGUID',
  `ad_user_name` varchar(50) DEFAULT '' COMMENT 'ADUserName',
  `employee_id` varchar(50) DEFAULT '' COMMENT 'EmployeeID',
  `barcode_id` varchar(36) DEFAULT '' COMMENT 'BarcodeID',
  `erp_id` varchar(36) DEFAULT '' COMMENT 'ERPID',
  `insp_center_guid` varchar(500) DEFAULT '' COMMENT 'InspCenter GUID',
  `insp_center_name` varchar(500) DEFAULT '' COMMENT 'InspCenter 名称',
  `password` varchar(50) DEFAULT '' COMMENT 'IM Password',
  `face` text COMMENT '人脸照片Base64',
  `flag` varchar(32) DEFAULT '' COMMENT '特征值',
  `update_time` int DEFAULT '0' COMMENT '更新时间',
  `deleted` tinyint DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `idx_contact_flag` (`flag`),
  KEY `idx_contact_guid` (`guid`)
);
DROP TABLE IF EXISTS `im_api_data`;
CREATE TABLE IF NOT EXISTS `im_api_data` (
  `id` varchar(36) NOT NULL PRIMARY KEY,
  `flag` varchar(32) DEFAULT '' COMMENT 'flag',
  `type` varchar(50) DEFAULT '' COMMENT '数据类型',
  `content` text COMMENT '接口返回的数据',
  KEY `idx_api_flag` (`flag`)
);
DROP TABLE IF EXISTS `im_api_rt_data`;
CREATE TABLE IF NOT EXISTS `im_api_rt_data` (
  `id` bigint NOT NULL COMMENT 'id',
  `guid` varchar(36) DEFAULT '' COMMENT 'guid',
  `source` varchar(36) DEFAULT '' COMMENT 'source',
  `flag` varchar(32) DEFAULT '' COMMENT 'flag',
  `type` varchar(50) DEFAULT '' COMMENT '数据类型',
  `content` text COMMENT '接口返回的数据',
  `deleted` tinyint DEFAULT '0' COMMENT '是否删除',
  KEY `idx_api_rt_source` (`source`),
  KEY `idx_api_rt_flag` (`flag`)
);
-- ----------------------------
-- Table structure for sys_route_conf
-- ----------------------------
-- DROP TABLE IF EXISTS `sys_route_conf`;
CREATE TABLE IF NOT EXISTS `sys_route_conf`  (
  `id` bigint NOT NULL COMMENT 'ID',
  `route_name` varchar(30)  DEFAULT '' COMMENT '名称',
  `route_id` varchar(30)  DEFAULT '' COMMENT 'routeId',
  `predicates` json NULL COMMENT '断言',
  `filters` json NULL COMMENT '过滤器',
  `uri` varchar(50)  DEFAULT '' COMMENT 'URI',
  `sorted` int NULL DEFAULT 0 COMMENT '排序',
  `creator` bigint NULL DEFAULT 0 COMMENT '创建者ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `deleted` tinyint NULL DEFAULT 0 COMMENT '逻辑删除 0正常 1删除',
  PRIMARY KEY (`id`)
);

-- DROP TABLE IF	EXISTS oauth_access_token;
CREATE TABLE IF NOT EXISTS oauth_access_token (
	create_time TIMESTAMP DEFAULT now(),
	token_id VARCHAR ( 255 ),
	token BLOB,
	authentication_id VARCHAR ( 255 ) UNIQUE,
	user_name VARCHAR ( 255 ),
	client_id VARCHAR ( 255 ),
	authentication BLOB,
	refresh_token VARCHAR ( 255 )
);
-- DROP TABLE IF	EXISTS oauth_refresh_token;
CREATE TABLE IF NOT EXISTS oauth_refresh_token ( create_time TIMESTAMP DEFAULT now(), token_id VARCHAR ( 255 ), token BLOB, authentication BLOB );
-- DROP TABLE IF	EXISTS oauth_code;
CREATE TABLE IF NOT EXISTS oauth_code ( create_time TIMESTAMP DEFAULT now(), CODE VARCHAR ( 255 ), authentication BLOB );