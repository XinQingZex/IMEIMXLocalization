package com.agile.qdmp.standalong.service.rbac;

import com.agile.qdmp.standalong.model.dto.rbac.AccountRoleDTO;
import com.agile.qdmp.standalong.model.dto.rbac.query.AccountRoleQueryDTO;
import com.agile.qdmp.standalong.model.entity.rbac.AccountRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 用户角色关联表
 *
 * @author wenbinglei
 * @date 2021-03-23 18:02:04
 */
public interface IAccountRoleService extends IService<AccountRole> {

    /**
     * 删除用户在指定系统中的Gutest权限
     * @param accountId
     * @param supplierId
     * @param systemName
     * @return
     */
    int removeGuest(Long accountId, Long supplierId, String systemName);

    /**
     * 删除同样权限(因为要重设)
     * @param accountId
     * @param supplierId
     * @param roleId
     * @return
     */
    int removeRoles(Long accountId, Long supplierId, Long roleId);

    /**
     * 物理删除
     * @param id
     * @return
     */
    int removeByIdOriginal(Long id);

    /**
     * 根据用户名获取权限列表-登录前检测可使用的客户端
     * @param queryDTO
     * @return
     */
    List<AccountRole> listAccountRoleByUserName(AccountRoleQueryDTO queryDTO);

    /**
     * 创建AccountRole
     * @param accountRoleDTO
     * @return
     */
    Boolean createAccountRole(AccountRoleDTO accountRoleDTO);
}
