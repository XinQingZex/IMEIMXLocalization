REPLACE INTO `oauth_client_details`(`id`, `client_id`, `client_name`, `resource_ids`, `client_secret_plain`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `create_time`, `archived`, `trusted`, `autoapprove`, `deleted`) VALUES
(1, 'umc-uaa', 'UAA', NULL, 'weijia-uaa@', '$2a$10$lx7MlI685NnE6ZyZCKTdb.6a18QpK4oXxBf3ZVI/g6i8/ItqRyjHG', 'web-app', 'client_credentials', NULL, 'ROLE_ADMIN', 300, 252000, NULL, '2020-09-09 09:43:17', 1, 0, 'true', 0),
(2, 'sys-gateway', 'GATEWAY', NULL, 'weijia-gateway@', '$2a$10$.aiqLvQhrQYI563h6s78r.bg2cKsRavFdKb19VSEC4QxOmraPmRwS', 'web-app', 'client_credentials', NULL, 'ROLE_ADMIN', 300, 252000, NULL, '2020-09-09 09:43:17', 1, 0, 'true', 0),
(3, 'admin-web', '系统管理平台', NULL, 'admin-web@', '$2a$10$AQa28GcJnqMVawFNlz4pnONHnpwdYRWGOC9juyyxG4XOFqw8j3BcC', 'openid', 'implicit,refresh_token,password,authorization_code', NULL, '', 3600, 2592000, NULL, '2020-09-09 09:43:17', 1, 0, 'true', 0),
(4, 'erp-web-admin', 'ERP管理平台', NULL, 'erp-web-admin@', '$2a$10$4G985J5ROxWJ358V3sHydOunBgOCyiLxPqFxeUMG3YhKb2ZcDNzXW', 'openid', 'implicit,refresh_token,password,authorization_code,client_credentials', '', '', 3600, 2592000, NULL, '2021-11-05 10:10:14', 0, 0, 'false', 1),
(5, 'dcs-web-admin', '数据收集系统客户端', NULL, 'dcs-web-admin@', '$2a$10$HbtxGl09qNT9JUUieb1pS..ic9yvahWagWFArhImhSK8A1p.aEG9W', 'openid', 'implicit,refresh_token,password,authorization_code,client_credentials', '', '', 3600, 2592000, NULL, '2021-11-05 10:10:14', 0, 0, 'false', 0),
(6, 'rds-web-admin', '报告设计器客户端', NULL, 'rds-web-admin@', '$2a$10$sevUpgcpOg41bPYz0BXotO5H40idV/D8igk5lCx4r2gmhd68L5Wuq', 'openid', 'implicit,refresh_token,password,authorization_code,client_credentials', '', '', 3600, 2592000, NULL, '2021-11-05 10:10:14', 0, 0, 'false', 0),
(7, 'plugin-custom-export', '自定义图纸导出插件', NULL, 'plugin-custom-export@', '$2a$10$wRftsYtvv8I4AtAU.rKpOOahbeWhfOP/DcEgO2WJt8J4/WYzgc7c.', 'openid', 'implicit,refresh_token,password,authorization_code,client_credentials', '', '', 3600, 2592000, NULL, '2021-11-05 10:10:14', 0, 0, 'false', 0),
(8, 'dcs-web-client', '数据收集系统测量客户端', NULL, 'dcs-web-client@', '$2a$10$ZvAxutyRYNJKI3bug1LZj.ycx1aOLcv6qNJE50dtF6nJYL6PLwK/W', 'openid', 'implicit,refresh_token,password,authorization_code,client_credentials', '', '', 3600, 2592000, NULL, '2021-11-05 10:10:14', 0, 0, 'false', 0);


REPLACE INTO `account_login_info`(`id`, `username`, `password`, `nickname`, `avatar`, `gender`, `email`, `mobile`, `user_source`, `activated`, `creator`, `created_time`, `state`, `deleted`) VALUES
(1, 'wenbinglei', '$2a$10$xV9YKvuPcKM3H3tpCcItn.iMuEd8lvTDX79VS0wE5OgQzvVMvBTKa', '温丙磊', '', '男', 'wenbinglei@163.com', '134364416881', 'admin-web', TRUE, '', '2021-04-05 11:50:42', 'normal', 0),
(1518498884348186626, 'wbl', '$2a$10$MYgARZOdvv2267vmvN5leObb6KyQl9gSmmv2KCsNHDox1vgwkR4GC', '温丙磊', 'https://hx-1259337860.cos.ap-shanghai.myqcloud.com/supplier/20220425075447536012142.jpeg', '男', '', '13436441689', 'admin-web', TRUE, '', '2022-04-25 15:55:22', 'normal', 0),
(1571751278269665282, '20000000001', '$2a$10$BqcCck5El6KCqI5ZcM7DZuNpIRa/PBZyl013eb9MMF/vU6ET.QjRC', '测试人员', '', '男', '', '20000000001', 'admin-web', TRUE, '', '2022-09-19 14:41:20', 'normal', 0);

REPLACE INTO `rbac_role`(`id`, `name`, `code`, `remark`, `system_name`, `editor`, `is_default`, `is_authorize`, `update_time`, `deleted`) VALUES
(1, '平台超级管理员权限', 'SUPER_ADMIN', '平台超级管理员权限', 'admin-web', 0, FALSE, FALSE, '2021-11-05 10:39:45', 0),
(1517433572198621185, '平台管理员权限', 'ADMIN', '平台管理员权限', 'admin-web', 0, FALSE, TRUE, '2021-11-05 10:39:45', 0),
(1517434767973720066, 'DCS-CLIENT管理员权限', 'DCS-CLIENT-ADMIN', '数据收集系统客户端管理员权限', 'dcs-web-admin', 0, FALSE, FALSE, '2022-04-22 17:25:38', 0),
(1518531014516162562, 'DCS-CLIENT普通用户权限', 'DCS-CLIENT-USER', '数据收集系统客户端普通用户权限', 'dcs-web-admin', 0, TRUE, TRUE, '2022-04-25 18:03:01', 0),
(1572761687674425345, 'DCS_CLIENT_MEASURE', 'DCS_CLIENT_MEASURE', 'DCS-CLIENT检测用户权限', 'dcs-web-client', 0, TRUE, TRUE, '2022-09-22 09:36:21', 0);


REPLACE INTO `rbac_account_role`(`id`, `account_id`, `role_id`, `role_name`, `role_code`, `system_name`, `supplier_id`, `supplier_name`, `create_time`, `deleted`) VALUES
(1503287427161645057, 1, 1, '平台超级管理员权限', 'SUPER_ADMIN', 'admin-web', 0, '', '2022-03-14 16:30:27', 0),
(1518519235253780481, 1518498884348186626, 1517433572198621185, '平台管理员权限', 'ADMIN', 'admin-web', NULL, '', '2022-04-25 17:16:13', 0),
 (1571751330002210818, 1571751278269665282, 1517434767973720066, 'DCS-CLIENT管理员权限', 'DCS-CLIENT-ADMIN', 'dcs-web-admin', NULL, '', '2022-09-19 14:41:33', 0),
(1571751482855231489, 1571751278269665282, 1518531014516162562, 'DCS-CLIENT普通用户权限', 'DCS-CLIENT-USER', 'dcs-web-admin', NULL, '', '2022-09-19 14:42:10', 0);


-- REPLACE INTO `sys_im_server`(`id`, `company_id`, `company_name`, `code`, `name`, `url`, `api_version`, `state`, `refuse_reason`, `creator_id`, `creator`, `update_time`, `update_status`, `create_time`, `deleted`) VALUES
-- (1571728269483888641, 1571381440154054657, '天津鸿信', '20220919130955265', 'IM-1', 'https://bf28-117-12-146-226.ap.ngrok.io', '0.0.1', '正常', '', 0, '', 1667872474, FALSE, 1663564195, 0),
-- (1571728472068771841, 1571381440154054657, '天津鸿信', '20220919131043902', 'IM-2', 'https://bf28-117-12-146-226.ap.ngrok.io', '0.0.2', '正常', '', 0, '', 1666949180, FALSE, 1663564243, 0);



REPLACE INTO `sys_im_user`(`id`, `company_id`, `company_name`, `server_id`, `server_name`, `account_id`, `staff_id`, `staff_name`, `username`, `password`, `state`, `update_time`, `create_time`, `deleted`) VALUES
(1572521415704805378, 1571381440154054657, '天津鸿信', 1571728269483888641, 'IM-1', 1571751278269665282, 1571751253116424193, '测试人员', 'Admin', 'Admin', '', 1663753296, 1663753296, 0),
(1572522078526472193, 1571381440154054657, '天津鸿信', 1571728269483888641, 'IM-1', 1571751278269665282, 1571751253116424193, '测试人员', 'Admin', 'Admin', '', 1663753454, 1663753454, 0),
(1572764772475973633, 1571381440154054657, '天津鸿信', 1571728472068771841, 'IM-2', 1571751278269665282, 1571751253116424193, '测试人员', 'Admin', 'Admin', '正常', 1663811316, 1663811316, 0);

REPLACE INTO `erp_company`(`id`, `account_id`, `logo`, `code`, `name`, `contact_name`, `contact_phone`, `contact_mobile`, `province`, `city`, `district`, `address`, `latitude`, `longitude`, `state`, `refuse_reason`, `creator_id`, `creator`, `create_time`, `deleted`) VALUES
(1571381440154054657, 0, '', '1663481504677718', '天津鸿信', '张超', '', '15602103304', '天津市', '市辖区', '滨海新区', '测试地址', 0.000000, 0.000000, '未审核', '', 0, '', 1663481504, 0);

REPLACE INTO `erp_staff`(`id`, `company_id`, `company_name`, `department_id`, `department_name`, `account_id`, `code`, `name`, `contact_mobile`, `gender`, `create_time`, `state`, `deleted`)
VALUES (1571751253116424193, 1571381440154054657, '天津鸿信', 0, '', 1571751278269665282, '20220919144115680', '测试人员', '20000000001', '男', 1663569675, FALSE, 0);


UPDATE sys_im_server SET update_status = FALSE;