package com.agile.qdmp.standalong.service.account.impl;

import com.agile.qdmp.standalong.mapper.account.LoginInfoMapper;
import com.agile.qdmp.standalong.model.convert.account.LoginInfoConverter;
import com.agile.qdmp.standalong.model.convert.rbac.AccountRoleConverter;
import com.agile.qdmp.standalong.model.dto.account.LoginInfoDTO;
import com.agile.qdmp.standalong.model.dto.rbac.AccountRoleDTO;
import com.agile.qdmp.standalong.model.entity.account.LoginInfo;
import com.agile.qdmp.standalong.model.entity.rbac.AccountRole;
import com.agile.qdmp.standalong.service.account.ILoginInfoService;
import com.agile.qdmp.standalong.service.rbac.IAccountRoleService;
import com.agile.tem.common.core.util.R;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户登录信息表 服务类
 *
 * @author wenbinglei
 * @date 2021-03-23 18:00:31
 */
@Service
public class LoginInfoServiceImpl extends ServiceImpl<LoginInfoMapper, LoginInfo> implements ILoginInfoService {

    @Resource
    private IAccountRoleService accountRoleService;

    @Override
    public LoginInfoDTO getAccountWithAuthoritiesByUsername(String username, String systemName) {
        LoginInfo info = this.lambdaQuery().eq(LoginInfo::getUsername, username).select(LoginInfo::getId, LoginInfo::getMobile, LoginInfo::getUsername, LoginInfo::getNickname, LoginInfo::getPassword, LoginInfo::getAvatar, LoginInfo::getPassword, LoginInfo::getActivated).one();
        if(info == null) {
            return null;
        } else {
            LoginInfoDTO infoDTO = LoginInfoConverter.INSTANCE.to(info);
            List<AccountRole> accountRoles = accountRoleService.lambdaQuery()
                    .eq(StringUtils.isNotEmpty(systemName), AccountRole::getSystemName, systemName)
                    .eq(AccountRole::getAccountId, info.getId())
                    .orderByDesc(AccountRole::getId)
                    .list();
            List<AccountRoleDTO> roles = accountRoles == null || accountRoles.size() == 0 ? null : AccountRoleConverter.INSTANCE.to(accountRoles);
            infoDTO.setRoles(roles);
            return infoDTO;
        }
    }

    @Override
    public LoginInfoDTO getAccountWithAuthoritiesByMobile(String mobile, String systemName) {
        LoginInfo info = this.lambdaQuery().eq(LoginInfo::getMobile, mobile).select(LoginInfo::getId, LoginInfo::getUsername, LoginInfo::getNickname, LoginInfo::getPassword, LoginInfo::getAvatar, LoginInfo::getPassword, LoginInfo::getActivated).one();
        if(info == null) {
            return null;
        } else {
            LoginInfoDTO infoDTO = LoginInfoConverter.INSTANCE.to(info);
            List<AccountRole> accountRoles = accountRoleService.lambdaQuery()
                    .eq(StringUtils.isNotEmpty(systemName), AccountRole::getSystemName, systemName)
                    .eq(AccountRole::getAccountId, info.getId())
                    .orderByDesc(AccountRole::getId)
                    .list();
            List<AccountRoleDTO> roles = accountRoles == null || accountRoles.size() == 0 ? null : AccountRoleConverter.INSTANCE.to(accountRoles);
            infoDTO.setRoles(roles);
            return infoDTO;
        }
    }

    @Override
    public R<LoginInfo> checkLoginInfoData(LoginInfo loginInfo) {
        if(StringUtils.isNotBlank(loginInfo.getUsername())) {
            if(this.lambdaQuery().eq(LoginInfo::getUsername, loginInfo.getUsername()).count() > 0) {
                return R.failed(loginInfo, "用户名已经存在");
            }
        }
        if(StringUtils.isNotBlank(loginInfo.getMobile())) {
            if(this.lambdaQuery().eq(LoginInfo::getMobile, loginInfo.getMobile()).count() > 0) {
                return R.failed(loginInfo, "手机号码已经存在");
            }
        }
        return R.ok();
    }
}
