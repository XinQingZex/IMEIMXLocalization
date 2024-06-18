package com.agile.qdmp.standalong.model.convert.rbac;

import com.agile.qdmp.standalong.model.dto.rbac.AccountRoleDTO;
import com.agile.qdmp.standalong.model.entity.rbac.AccountRole;
import com.agile.tem.common.core.model.Convert;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 用户角色关联表 数据转换
 *
 * @author wenbinglei
 * @date 2021-03-23 18:02:04
 */
@Mapper
public interface AccountRoleConverter extends Convert<AccountRole, AccountRoleDTO> {
    AccountRoleConverter INSTANCE = Mappers.getMapper(AccountRoleConverter.class);
}
