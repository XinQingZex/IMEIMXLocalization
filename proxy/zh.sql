CREATE TABLE [dbo].[account_login_info] (
  [id] bigint  NOT NULL,
  [username] varchar(50) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [password] varchar(80) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [nickname] varchar(50) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [avatar] varchar(300) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [gender] varchar(10) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [email] varchar(50) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [mobile] varchar(15) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [user_source] varchar(20) COLLATE Chinese_PRC_CI_AS  NULL,
  [activated] bit DEFAULT 1 NOT NULL,
  [creator] varchar(20) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [created_time] datetime DEFAULT getdate() NULL,
  [state] varchar(8) COLLATE Chinese_PRC_CI_AS DEFAULT 'normal' NULL,
  [deleted] tinyint DEFAULT 0 NULL,
  CONSTRAINT [PK__account___3213E83F8F4E6E3C] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
)  
ON [PRIMARY]
GO

ALTER TABLE [dbo].[account_login_info] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'ID',
'SCHEMA', N'dbo',
'TABLE', N'account_login_info',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'用户名',
'SCHEMA', N'dbo',
'TABLE', N'account_login_info',
'COLUMN', N'username'
GO

EXEC sp_addextendedproperty
'MS_Description', N'登录密码',
'SCHEMA', N'dbo',
'TABLE', N'account_login_info',
'COLUMN', N'password'
GO

EXEC sp_addextendedproperty
'MS_Description', N'昵称',
'SCHEMA', N'dbo',
'TABLE', N'account_login_info',
'COLUMN', N'nickname'
GO

EXEC sp_addextendedproperty
'MS_Description', N'用户头像',
'SCHEMA', N'dbo',
'TABLE', N'account_login_info',
'COLUMN', N'avatar'
GO

EXEC sp_addextendedproperty
'MS_Description', N'用户性别',
'SCHEMA', N'dbo',
'TABLE', N'account_login_info',
'COLUMN', N'gender'
GO

EXEC sp_addextendedproperty
'MS_Description', N'邮件地址',
'SCHEMA', N'dbo',
'TABLE', N'account_login_info',
'COLUMN', N'email'
GO

EXEC sp_addextendedproperty
'MS_Description', N'手机号',
'SCHEMA', N'dbo',
'TABLE', N'account_login_info',
'COLUMN', N'mobile'
GO

EXEC sp_addextendedproperty
'MS_Description', N'用户来源',
'SCHEMA', N'dbo',
'TABLE', N'account_login_info',
'COLUMN', N'user_source'
GO

EXEC sp_addextendedproperty
'MS_Description', N'是否激活',
'SCHEMA', N'dbo',
'TABLE', N'account_login_info',
'COLUMN', N'activated'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建人',
'SCHEMA', N'dbo',
'TABLE', N'account_login_info',
'COLUMN', N'creator'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'account_login_info',
'COLUMN', N'created_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'状态, normal=正常,lock=锁定',
'SCHEMA', N'dbo',
'TABLE', N'account_login_info',
'COLUMN', N'state'
GO

EXEC sp_addextendedproperty
'MS_Description', N'逻辑删除',
'SCHEMA', N'dbo',
'TABLE', N'account_login_info',
'COLUMN', N'deleted'
GO


CREATE TABLE [dbo].[oauth_access_token] (
  [token_id] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [token] varbinary(max)  NULL,
  [authentication_id] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [user_name] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [client_id] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [authentication] varbinary(max)  NULL,
  [refresh_token] varchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  CONSTRAINT [PK__oauth_ac__CB3C9E172A03A896] PRIMARY KEY CLUSTERED ([token_id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
)  
ON [PRIMARY]
TEXTIMAGE_ON [PRIMARY]
GO

ALTER TABLE [dbo].[oauth_access_token] SET (LOCK_ESCALATION = TABLE)


CREATE TABLE [dbo].[oauth_client_details] (
  [id] bigint  NOT NULL,
  [client_id] nvarchar(50) COLLATE Chinese_PRC_CI_AS  NULL,
  [client_name] nvarchar(50) COLLATE Chinese_PRC_CI_AS  NULL,
  [resource_ids] nvarchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [client_secret_plain] nvarchar(50) COLLATE Chinese_PRC_CI_AS  NULL,
  [client_secret] nvarchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [scope] nvarchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [authorized_grant_types] nvarchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [web_server_redirect_uri] nvarchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [authorities] nvarchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [access_token_validity] int DEFAULT 0 NULL,
  [refresh_token_validity] int DEFAULT 0 NULL,
  [additional_information] text COLLATE Chinese_PRC_CI_AS  NULL,
  [create_time] datetime  NOT NULL,
  [archived] tinyint DEFAULT 0 NULL,
  [trusted] tinyint DEFAULT 0 NULL,
  [autoapprove] nvarchar(255) COLLATE Chinese_PRC_CI_AS  NULL,
  [deleted] tinyint DEFAULT 0 NULL,
  CONSTRAINT [PK__oauth_cl__3213E83F1058AD88] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
)  
ON [PRIMARY]
TEXTIMAGE_ON [PRIMARY]
GO

ALTER TABLE [dbo].[oauth_client_details] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'客户端ID',
'SCHEMA', N'dbo',
'TABLE', N'oauth_client_details',
'COLUMN', N'client_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'客户端名称',
'SCHEMA', N'dbo',
'TABLE', N'oauth_client_details',
'COLUMN', N'client_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'客户端所能访问的资源id集合',
'SCHEMA', N'dbo',
'TABLE', N'oauth_client_details',
'COLUMN', N'resource_ids'
GO

EXEC sp_addextendedproperty
'MS_Description', N'客户端(client)的访问密匙明文',
'SCHEMA', N'dbo',
'TABLE', N'oauth_client_details',
'COLUMN', N'client_secret_plain'
GO

EXEC sp_addextendedproperty
'MS_Description', N'用于指定客户端(client)的访问密匙',
'SCHEMA', N'dbo',
'TABLE', N'oauth_client_details',
'COLUMN', N'client_secret'
GO

EXEC sp_addextendedproperty
'MS_Description', N'指定客户端申请的权限范围',
'SCHEMA', N'dbo',
'TABLE', N'oauth_client_details',
'COLUMN', N'scope'
GO

EXEC sp_addextendedproperty
'MS_Description', N'指定客户端支持的grant_type',
'SCHEMA', N'dbo',
'TABLE', N'oauth_client_details',
'COLUMN', N'authorized_grant_types'
GO

EXEC sp_addextendedproperty
'MS_Description', N'客户端的重定向URI,可为空',
'SCHEMA', N'dbo',
'TABLE', N'oauth_client_details',
'COLUMN', N'web_server_redirect_uri'
GO

EXEC sp_addextendedproperty
'MS_Description', N'指定客户端所拥有的Spring Security的权限值',
'SCHEMA', N'dbo',
'TABLE', N'oauth_client_details',
'COLUMN', N'authorities'
GO

EXEC sp_addextendedproperty
'MS_Description', N'设定客户端的access_token的有效时间值(单位:秒)',
'SCHEMA', N'dbo',
'TABLE', N'oauth_client_details',
'COLUMN', N'access_token_validity'
GO

EXEC sp_addextendedproperty
'MS_Description', N'设定客户端的refresh_token的有效时间值(单位:秒)',
'SCHEMA', N'dbo',
'TABLE', N'oauth_client_details',
'COLUMN', N'refresh_token_validity'
GO

EXEC sp_addextendedproperty
'MS_Description', N'预留字段',
'SCHEMA', N'dbo',
'TABLE', N'oauth_client_details',
'COLUMN', N'additional_information'
GO

EXEC sp_addextendedproperty
'MS_Description', N'用于标识客户端是否已存档(即实现逻辑删除),默认值为"0"(即未存档)',
'SCHEMA', N'dbo',
'TABLE', N'oauth_client_details',
'COLUMN', N'archived'
GO

EXEC sp_addextendedproperty
'MS_Description', N'设置客户端是否为受信任的,默认为"0"(即不受信任的,1为受信任的).',
'SCHEMA', N'dbo',
'TABLE', N'oauth_client_details',
'COLUMN', N'trusted'
GO

EXEC sp_addextendedproperty
'MS_Description', N'设置用户是否自动Approval操作, 默认值为 "false", 可选值包括 "true","false", "read","write".',
'SCHEMA', N'dbo',
'TABLE', N'oauth_client_details',
'COLUMN', N'autoapprove'
GO

EXEC sp_addextendedproperty
'MS_Description', N'是否删除 0否 1是',
'SCHEMA', N'dbo',
'TABLE', N'oauth_client_details',
'COLUMN', N'deleted'
GO


CREATE TABLE [dbo].[oauth_code] (
  [CODE] nvarchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [authentication] varbinary(1000)  NULL,
  CONSTRAINT [PK__oauth_co__AA1D43788D2FDA70] PRIMARY KEY CLUSTERED ([CODE])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
)  
ON [PRIMARY]
GO

ALTER TABLE [dbo].[oauth_code] SET (LOCK_ESCALATION = TABLE)
GO


CREATE TABLE [dbo].[oauth_refresh_token] (
  [token_id] varchar(255) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [token] varbinary(max)  NULL,
  [authentication] varbinary(max)  NULL,
  CONSTRAINT [PK__oauth_re__CB3C9E17DF83ECE1] PRIMARY KEY CLUSTERED ([token_id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
)  
ON [PRIMARY]
TEXTIMAGE_ON [PRIMARY]
GO

ALTER TABLE [dbo].[oauth_refresh_token] SET (LOCK_ESCALATION = TABLE)

CREATE TABLE [dbo].[rbac_account_role] (
  [id] bigint  NOT NULL,
  [account_id] bigint DEFAULT 0 NULL,
  [role_id] bigint DEFAULT 0 NOT NULL,
  [role_name] nvarchar(30) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [role_code] nvarchar(30) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [system_name] nvarchar(30) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [supplier_id] bigint DEFAULT 0 NULL,
  [supplier_name] nvarchar(100) COLLATE Chinese_PRC_CI_AS  NULL,
  [create_time] datetime DEFAULT getdate() NOT NULL,
  [deleted] tinyint DEFAULT 0 NULL,
  CONSTRAINT [PK__rbac_acc__3213E83FF6BCFF25] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
)  
ON [PRIMARY]
GO

ALTER TABLE [dbo].[rbac_account_role] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'ID',
'SCHEMA', N'dbo',
'TABLE', N'rbac_account_role',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'账户ID',
'SCHEMA', N'dbo',
'TABLE', N'rbac_account_role',
'COLUMN', N'account_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'角色ID',
'SCHEMA', N'dbo',
'TABLE', N'rbac_account_role',
'COLUMN', N'role_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'角色名称',
'SCHEMA', N'dbo',
'TABLE', N'rbac_account_role',
'COLUMN', N'role_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'角色编码',
'SCHEMA', N'dbo',
'TABLE', N'rbac_account_role',
'COLUMN', N'role_code'
GO

EXEC sp_addextendedproperty
'MS_Description', N'所属系统',
'SCHEMA', N'dbo',
'TABLE', N'rbac_account_role',
'COLUMN', N'system_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'所属服务商ID',
'SCHEMA', N'dbo',
'TABLE', N'rbac_account_role',
'COLUMN', N'supplier_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'所属服务商名称',
'SCHEMA', N'dbo',
'TABLE', N'rbac_account_role',
'COLUMN', N'supplier_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'创建时间',
'SCHEMA', N'dbo',
'TABLE', N'rbac_account_role',
'COLUMN', N'create_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'是否删除 0是1否',
'SCHEMA', N'dbo',
'TABLE', N'rbac_account_role',
'COLUMN', N'deleted'
GO

CREATE TABLE [dbo].[rbac_role] (
  [id] bigint  NOT NULL,
  [name] nvarchar(30) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [code] nvarchar(30) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [remark] nvarchar(100) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [system_name] nvarchar(20) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [editor] bigint DEFAULT 0 NULL,
  [is_default] tinyint DEFAULT 0 NULL,
  [is_authorize] tinyint DEFAULT 0 NULL,
  [update_time] datetime DEFAULT getdate() NULL,
  [deleted] tinyint DEFAULT 0 NULL,
  CONSTRAINT [PK__rbac_rol__3213E83FFBD4DCC8] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
)  
ON [PRIMARY]
GO

ALTER TABLE [dbo].[rbac_role] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'ID',
'SCHEMA', N'dbo',
'TABLE', N'rbac_role',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'角色名称',
'SCHEMA', N'dbo',
'TABLE', N'rbac_role',
'COLUMN', N'name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'角色编码',
'SCHEMA', N'dbo',
'TABLE', N'rbac_role',
'COLUMN', N'code'
GO

EXEC sp_addextendedproperty
'MS_Description', N'备注',
'SCHEMA', N'dbo',
'TABLE', N'rbac_role',
'COLUMN', N'remark'
GO

EXEC sp_addextendedproperty
'MS_Description', N'所属系统',
'SCHEMA', N'dbo',
'TABLE', N'rbac_role',
'COLUMN', N'system_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'修改者ID',
'SCHEMA', N'dbo',
'TABLE', N'rbac_role',
'COLUMN', N'editor'
GO

EXEC sp_addextendedproperty
'MS_Description', N'是否默认system_name默认权限',
'SCHEMA', N'dbo',
'TABLE', N'rbac_role',
'COLUMN', N'is_default'
GO

EXEC sp_addextendedproperty
'MS_Description', N'是否可直接授权',
'SCHEMA', N'dbo',
'TABLE', N'rbac_role',
'COLUMN', N'is_authorize'
GO

EXEC sp_addextendedproperty
'MS_Description', N'更新时间',
'SCHEMA', N'dbo',
'TABLE', N'rbac_role',
'COLUMN', N'update_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'逻辑删除 0正常 1删除',
'SCHEMA', N'dbo',
'TABLE', N'rbac_role',
'COLUMN', N'deleted'
GO


CREATE TABLE [dbo].[sys_im_api_record] (
  [id] bigint  NOT NULL,
  [company_id] bigint DEFAULT 0 NULL,
  [company_name] nvarchar(100) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [account_id] bigint DEFAULT 0 NULL,
  [staff_id] bigint DEFAULT 0 NULL,
  [staff_name] nvarchar(100) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [server_id] bigint DEFAULT 0 NULL,
  [server_name] nvarchar(100) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [server_url] nvarchar(200) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [content] nvarchar(100) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [update_time] int DEFAULT 0 NULL,
  [deleted] tinyint DEFAULT 0 NULL,
  CONSTRAINT [PK__sys_im_a__3213E83F1A1DF900] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
)  
ON [PRIMARY]
GO

ALTER TABLE [dbo].[sys_im_api_record] SET (LOCK_ESCALATION = TABLE)
GO

EXEC sp_addextendedproperty
'MS_Description', N'ID',
'SCHEMA', N'dbo',
'TABLE', N'sys_im_api_record',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'企业id',
'SCHEMA', N'dbo',
'TABLE', N'sys_im_api_record',
'COLUMN', N'company_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'企业名称',
'SCHEMA', N'dbo',
'TABLE', N'sys_im_api_record',
'COLUMN', N'company_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'账户id',
'SCHEMA', N'dbo',
'TABLE', N'sys_im_api_record',
'COLUMN', N'account_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'职员id',
'SCHEMA', N'dbo',
'TABLE', N'sys_im_api_record',
'COLUMN', N'staff_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'职员名称',
'SCHEMA', N'dbo',
'TABLE', N'sys_im_api_record',
'COLUMN', N'staff_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'IM Server id',
'SCHEMA', N'dbo',
'TABLE', N'sys_im_api_record',
'COLUMN', N'server_id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'IM Server 名称',
'SCHEMA', N'dbo',
'TABLE', N'sys_im_api_record',
'COLUMN', N'server_name'
GO

EXEC sp_addextendedproperty
'MS_Description', N'IM Server 连接地址',
'SCHEMA', N'dbo',
'TABLE', N'sys_im_api_record',
'COLUMN', N'server_url'
GO

EXEC sp_addextendedproperty
'MS_Description', N'操作内容',
'SCHEMA', N'dbo',
'TABLE', N'sys_im_api_record',
'COLUMN', N'content'
GO

EXEC sp_addextendedproperty
'MS_Description', N'更新时间',
'SCHEMA', N'dbo',
'TABLE', N'sys_im_api_record',
'COLUMN', N'update_time'
GO

EXEC sp_addextendedproperty
'MS_Description', N'是否删除',
'SCHEMA', N'dbo',
'TABLE', N'sys_im_api_record',
'COLUMN', N'deleted'
GO
CREATE TABLE [dbo].[im_api_data] (
  [id] nvarchar(36) COLLATE Chinese_PRC_CI_AS  NOT NULL,
  [flag] nvarchar(32) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [type] nvarchar(50) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [content] nvarchar(max) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  CONSTRAINT [PK__im_api_d__3213E83FB0583A01] PRIMARY KEY CLUSTERED ([id])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
)  
ON [PRIMARY]
TEXTIMAGE_ON [PRIMARY]
GO

ALTER TABLE [dbo].[im_api_data] SET (LOCK_ESCALATION = TABLE)
GO

CREATE NONCLUSTERED INDEX [idx_api_flag]
ON [dbo].[im_api_data] (
  [flag] ASC
)
GO

EXEC sp_addextendedproperty
'MS_Description', N'flag',
'SCHEMA', N'dbo',
'TABLE', N'im_api_data',
'COLUMN', N'flag'
GO

EXEC sp_addextendedproperty
'MS_Description', N'数据类型',
'SCHEMA', N'dbo',
'TABLE', N'im_api_data',
'COLUMN', N'type'
GO

CREATE TABLE [dbo].[im_api_rt_data] (
  [id] bigint  NOT NULL,
  [guid] nvarchar(36) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [source] nvarchar(36) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [flag] nvarchar(32) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [type] nvarchar(50) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [content] nvarchar(max) COLLATE Chinese_PRC_CI_AS DEFAULT '' NULL,
  [deleted] tinyint DEFAULT 0 NULL
)  
ON [PRIMARY]
TEXTIMAGE_ON [PRIMARY]
GO

ALTER TABLE [dbo].[im_api_rt_data] SET (LOCK_ESCALATION = TABLE)
GO

CREATE NONCLUSTERED INDEX [idx_api_rt_source]
ON [dbo].[im_api_rt_data] (
  [source] ASC
)
GO

CREATE NONCLUSTERED INDEX [idx_api_rt_flag]
ON [dbo].[im_api_rt_data] (
  [flag] ASC
)
GO

EXEC sp_addextendedproperty
'MS_Description', N'id',
'SCHEMA', N'dbo',
'TABLE', N'im_api_rt_data',
'COLUMN', N'id'
GO

EXEC sp_addextendedproperty
'MS_Description', N'guid',
'SCHEMA', N'dbo',
'TABLE', N'im_api_rt_data',
'COLUMN', N'guid'
GO

EXEC sp_addextendedproperty
'MS_Description', N'source',
'SCHEMA', N'dbo',
'TABLE', N'im_api_rt_data',
'COLUMN', N'source'
GO

EXEC sp_addextendedproperty
'MS_Description', N'flag',
'SCHEMA', N'dbo',
'TABLE', N'im_api_rt_data',
'COLUMN', N'flag'
GO

EXEC sp_addextendedproperty
'MS_Description', N'数据类型',
'SCHEMA', N'dbo',
'TABLE', N'im_api_rt_data',
'COLUMN', N'type'
GO

EXEC sp_addextendedproperty
'MS_Description', N'接口返回的数据',
'SCHEMA', N'dbo',
'TABLE', N'im_api_rt_data',
'COLUMN', N'content'
GO

EXEC sp_addextendedproperty
'MS_Description', N'是否删除',
'SCHEMA', N'dbo',
'TABLE', N'im_api_rt_data',
'COLUMN', N'deleted'
GO

EXEC sp_addextendedproperty
'MS_Description', N'接口返回的数据',
'SCHEMA', N'dbo',
'TABLE', N'im_api_data',
'COLUMN', N'content'
GO




INSERT INTO oauth_client_details(id, client_id, client_name, resource_ids, client_secret_plain, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, create_time, archived, trusted, autoapprove, deleted) VALUES
(1, 'umc-uaa', 'UAA', NULL, 'weijia-uaa@', '$2a$10$lx7MlI685NnE6ZyZCKTdb.6a18QpK4oXxBf3ZVI/g6i8/ItqRyjHG', 'web-app', 'client_credentials', NULL, 'ROLE_ADMIN', 300, 252000, NULL, '2020-09-09 09:43:17', 1, 0, 'true', 0),
(2, 'sys-gateway', 'GATEWAY', NULL, 'weijia-gateway@', '$2a$10$.aiqLvQhrQYI563h6s78r.bg2cKsRavFdKb19VSEC4QxOmraPmRwS', 'web-app', 'client_credentials', NULL, 'ROLE_ADMIN', 300, 252000, NULL, '2020-09-09 09:43:17', 1, 0, 'true', 0),
(3, 'admin-web', '系统管理平台', NULL, 'admin-web@', '$2a$10$AQa28GcJnqMVawFNlz4pnONHnpwdYRWGOC9juyyxG4XOFqw8j3BcC', 'openid', 'implicit,refresh_token,password,authorization_code', NULL, '', 3600, 2592000, NULL, '2020-09-09 09:43:17', 1, 0, 'true', 0),
(4, 'erp-web-admin', 'ERP管理平台', NULL, 'erp-web-admin@', '$2a$10$4G985J5ROxWJ358V3sHydOunBgOCyiLxPqFxeUMG3YhKb2ZcDNzXW', 'openid', 'implicit,refresh_token,password,authorization_code,client_credentials', '', '', 3600, 2592000, NULL, '2021-11-05 10:10:14', 0, 0, 'false', 1),
(5, 'dcs-web-admin', '数据收集系统客户端', NULL, 'dcs-web-admin@', '$2a$10$HbtxGl09qNT9JUUieb1pS..ic9yvahWagWFArhImhSK8A1p.aEG9W', 'openid', 'implicit,refresh_token,password,authorization_code,client_credentials', '', '', 3600, 2592000, NULL, '2021-11-05 10:10:14', 0, 0, 'false', 0),
(6, 'rds-web-admin', '报告设计器客户端', NULL, 'rds-web-admin@', '$2a$10$sevUpgcpOg41bPYz0BXotO5H40idV/D8igk5lCx4r2gmhd68L5Wuq', 'openid', 'implicit,refresh_token,password,authorization_code,client_credentials', '', '', 3600, 2592000, NULL, '2021-11-05 10:10:14', 0, 0, 'false', 0),
(7, 'plugin-custom-export', '自定义图纸导出插件', NULL, 'plugin-custom-export@', '$2a$10$wRftsYtvv8I4AtAU.rKpOOahbeWhfOP/DcEgO2WJt8J4/WYzgc7c.', 'openid', 'implicit,refresh_token,password,authorization_code,client_credentials', '', '', 3600, 2592000, NULL, '2021-11-05 10:10:14', 0, 0, 'false', 0),
(8, 'dcs-web-client', '数据收集系统测量客户端', NULL, 'dcs-web-client@', '$2a$10$ZvAxutyRYNJKI3bug1LZj.ycx1aOLcv6qNJE50dtF6nJYL6PLwK/W', 'openid', 'implicit,refresh_token,password,authorization_code,client_credentials', '', '', 3600, 2592000, NULL, '2021-11-05 10:10:14', 0, 0, 'false', 0);


INSERT INTO rbac_role(id, name, code, remark, system_name, editor, is_default, is_authorize, update_time, deleted) VALUES
(1, '平台超级管理员权限', 'SUPER_ADMIN', '平台超级管理员权限', 'admin-web', 0, 0, 0, '2021-11-05 10:39:45', 0),
(1517433572198621185, '平台管理员权限', 'ADMIN', '平台管理员权限', 'admin-web', 0, 0, 1, '2021-11-05 10:39:45', 0),
(1517434767973720066, 'DCS-CLIENT管理员权限', 'DCS-CLIENT-ADMIN', '数据收集系统客户端管理员权限', 'dcs-web-admin', 0, 0, 0, '2022-04-22 17:25:38', 0),
(1518531014516162562, 'DCS-CLIENT普通用户权限', 'DCS-CLIENT-USER', '数据收集系统客户端普通用户权限', 'dcs-web-admin', 0, 1, 1, '2022-04-25 18:03:01', 0),
(1572761687674425345, 'DCS_CLIENT_MEASURE', 'DCS_CLIENT_MEASURE', 'DCS-CLIENT检测用户权限', 'dcs-web-client', 0, 1, 1, '2022-09-22 09:36:21', 0);