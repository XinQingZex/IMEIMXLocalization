package com.agile.qdmp.standalong.service.rbac.impl;

import com.agile.qdmp.standalong.model.convert.rbac.AccountRoleConverter;
import com.agile.qdmp.standalong.model.dto.rbac.AccountRoleDTO;
import com.agile.qdmp.standalong.model.dto.rbac.query.AccountRoleQueryDTO;
import com.agile.qdmp.standalong.mapper.rbac.AccountRoleMapper;
import com.agile.qdmp.standalong.model.entity.rbac.AccountRole;
import com.agile.qdmp.standalong.model.entity.rbac.Role;
import com.agile.qdmp.standalong.service.rbac.IAccountRoleService;
import com.agile.qdmp.standalong.service.rbac.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;

/**
 * 用户角色关联表 服务类
 *
 * @author wenbinglei
 * @date 2021-03-23 18:02:04
 */
@Service
public class AccountRoleServiceImpl extends ServiceImpl<AccountRoleMapper, AccountRole> implements IAccountRoleService {

    @Resource
    private IRoleService roleService;

    @Override
    public int removeGuest(Long accountId, Long supplierId, String systemName) {
        return this.baseMapper.removeGuest(accountId, supplierId, systemName);
    }

    @Override
    public int removeRoles(Long accountId, Long supplierId, Long roleId) {
        return this.baseMapper.removeRoles(accountId, supplierId, roleId);
    }

    @Override
    public int removeByIdOriginal(Long id) {
        return this.baseMapper.removeByIdOriginal(id);
    }

    @Override
    public List<AccountRole> listAccountRoleByUserName(AccountRoleQueryDTO queryDTO) {
        return this.listAccountRoleByUserName(queryDTO);
    }

    @Override
    public Boolean createAccountRole(AccountRoleDTO accountRoleDTO) {
        AccountRole currentData = this.lambdaQuery()
                .eq(AccountRole::getAccountId, accountRoleDTO.getAccountId())
                .eq(AccountRole::getRoleId, accountRoleDTO.getRoleId())
                .eq(Objects.nonNull(accountRoleDTO.getSupplierId()), AccountRole::getSupplierId, accountRoleDTO.getSupplierId())
                .eq(StringUtils.isNotBlank(accountRoleDTO.getSystemName()), AccountRole::getSystemName, accountRoleDTO.getSystemName())
                .one();
        Role role = roleService.getById(accountRoleDTO.getRoleId());
        if(role == null) {
            return Boolean.FALSE;
        }
        if(currentData == null) {
            AccountRole data = AccountRoleConverter.INSTANCE.from(accountRoleDTO);
            data.setRoleName(role.getName());
            data.setRoleCode(role.getCode());
            data.setSystemName(role.getSystemName());
            data.setCreateTime(LocalDateTime.now(ZoneId.of("Asia/Shanghai")));
            // 删除当前账户 在当前系统中的 guest权限
            this.removeGuest(data.getAccountId(), data.getSupplierId(), data.getSystemName());
            return this.save(data);
        } else {
            // 有可能权限编码或服务商名称出现变化
            currentData.setRoleCode(role.getCode());
            currentData.setRoleName(role.getName());
            currentData.setSystemName(role.getSystemName());
            currentData.setSupplierName(accountRoleDTO.getSupplierName());
            return this.updateById(currentData);
        }
    }
}
