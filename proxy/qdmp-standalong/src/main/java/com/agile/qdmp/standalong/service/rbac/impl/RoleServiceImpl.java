package com.agile.qdmp.standalong.service.rbac.impl;

import com.agile.qdmp.standalong.mapper.rbac.RoleMapper;
import com.agile.qdmp.standalong.model.entity.rbac.Role;
import com.agile.qdmp.standalong.service.rbac.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 角色表 服务类
 *
 * @author wenbinglei
 * @date 2021-03-23 18:01:22
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
