package com.agile.qdmp.standalong.service.uaa.impl;

import com.agile.qdmp.standalong.model.convert.account.LoginInfoConverter;
import com.agile.qdmp.standalong.model.convert.rbac.AccountRoleConverter;
import com.agile.qdmp.standalong.model.dto.account.LoginInfoDTO;
import com.agile.qdmp.standalong.model.dto.rbac.AccountRoleDTO;
import com.agile.qdmp.standalong.model.entity.account.LoginInfo;
import com.agile.qdmp.standalong.model.entity.integrator.Contact;
import com.agile.qdmp.standalong.model.entity.rbac.AccountRole;
import com.agile.qdmp.standalong.model.entity.rbac.Role;
import com.agile.qdmp.standalong.service.account.ILoginInfoService;
import com.agile.qdmp.standalong.service.integrator.IContactService;
import com.agile.qdmp.standalong.service.rbac.IAccountRoleService;
import com.agile.qdmp.standalong.service.rbac.IRoleService;
import com.agile.tem.common.core.util.R;
import com.agile.qdmp.standalong.model.dto.uaa.RegistDTO;
import com.agile.qdmp.standalong.security.SecurityUtils;
import com.agile.qdmp.standalong.service.uaa.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * <p>
 * 用户基础信息表 服务实现类
 * </p>
 *
 * @author Caratacus
 * @since 2019-01-06
 */
@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    private final PasswordEncoder passwordEncoder;
    private final ILoginInfoService loginInfoService;
    private final IAccountRoleService accountRoleService;
    private final IContactService contactService;
    private final IRoleService roleService;

    public UserServiceImpl(PasswordEncoder passwordEncoder, ILoginInfoService loginInfoService, IAccountRoleService accountRoleService, IContactService contactService, IRoleService roleService) {
        this.passwordEncoder = passwordEncoder;
        this.loginInfoService = loginInfoService;
        this.accountRoleService = accountRoleService;
        this.contactService = contactService;
        this.roleService = roleService;
    }

    @Override
    public R<LoginInfoDTO> register(RegistDTO infoDTO, String password) {
        // build new account
        LoginInfoDTO newUser = new LoginInfoDTO();
        String encryptedPassword = passwordEncoder.encode(password);
        newUser.setPassword(encryptedPassword);
        newUser.setUserSource(infoDTO.getSource());
        if(!StringUtils.isEmpty(infoDTO.getAvatar())) {
            newUser.setAvatar(infoDTO.getAvatar());
        }
        if(!StringUtils.isEmpty(infoDTO.getNickname())) {
            newUser.setNickname(infoDTO.getNickname());
        }
        if(!StringUtils.isEmpty(infoDTO.getGender())) {
            newUser.setGender(infoDTO.getGender());
        }
        if(!StringUtils.isEmpty(infoDTO.getMobile())) {
            newUser.setMobile(infoDTO.getMobile());
            if(StringUtils.isEmpty(infoDTO.getUsername())) {
                newUser.setUsername(infoDTO.getMobile());
            }
        }
        if(!StringUtils.isEmpty(infoDTO.getUsername())) {
            newUser.setUsername(infoDTO.getUsername());
        }
        newUser.setActivated(infoDTO.getActivated() == null ? true : infoDTO.getActivated());

        // save account
        R<LoginInfo> checkResult = loginInfoService.checkLoginInfoData(LoginInfoConverter.INSTANCE.from(newUser));
        if(checkResult.getCode() == 1) {
            return R.failed(newUser, checkResult.getMsg());
        }
        LoginInfo entity = LoginInfoConverter.INSTANCE.from(newUser);
        boolean result = loginInfoService.save(entity);
        if(result) {
            newUser.setId(entity.getId());
        } else {
            return R.failed(newUser, "注册失败");
        }

        // grant role
        if(infoDTO.getGrantDefaultRole() == null || infoDTO.getGrantDefaultRole()) {
            AccountRoleDTO customerRole = new AccountRoleDTO();
            // 根据注册渠道 获取默认权限
            List<Role> defaultRoles = roleService.lambdaQuery().eq(Role::getSystemName, infoDTO.getSource()).eq(Role::getIsDefault, Boolean.TRUE).list();
            if(defaultRoles != null && defaultRoles.size() > 0) {
                customerRole.setRoleId(defaultRoles.get(0).getId());
                if(infoDTO.getSupplierId() != null) {
                    customerRole.setSupplierId(infoDTO.getSupplierId());
                    customerRole.setSupplierName(infoDTO.getSupplierName());
                }
                customerRole.setAccountId(entity.getId());
                accountRoleService.createAccountRole(customerRole);
            }
        }
        return R.ok(newUser);
    }

    @Override
    public Boolean changePassword(Long userId, String currentClearTextPassword, String newPassword) {
        Boolean result = true;
        Optional<String> userName = SecurityUtils.getCurrentUserLogin();
        String currentUserName = userName.get();

        if(StringUtils.isEmpty(currentUserName)) {
            result = Boolean.FALSE;
        } else {
            LoginInfo account = loginInfoService.lambdaQuery().eq(LoginInfo::getUsername, currentUserName).one();
            if(account != null) {
                String currentEncryptedPassword = account.getPassword();
                if (passwordEncoder.matches(currentClearTextPassword, currentEncryptedPassword)) {
                    String encryptedPassword = passwordEncoder.encode(newPassword);
                    account.setPassword(encryptedPassword);
                    result = loginInfoService.updateById(account);
                } else {
                    return false;
                }
            }
        }
        return result;
    }

    @Override
    public R<Boolean> changeMobile(Long accountId, String mobile) {
        Optional<String> userName = SecurityUtils.getCurrentUserLogin();
        String currentUserName = userName.get();

        LoginInfo account = loginInfoService.lambdaQuery().eq(LoginInfo::getUsername, currentUserName).one();
        if(account == null){
            return R.failed(false, "手机号修改失败,未找到用户");
        }
        LoginInfo info = loginInfoService.lambdaQuery().eq(LoginInfo::getMobile, mobile).one();
        if(info != null && !info.getId().toString().equals(accountId.toString())){
            return R.failed(false, "手机号已绑定其他用户");
        }
        LoginInfo newInfo = new LoginInfo();
        newInfo.setId(accountId);
        newInfo.setMobile(mobile);
        loginInfoService.updateById(newInfo);
        return R.ok(true, "绑定成功");
    }

    @Override
    public Boolean resetPassword(Long userId, String newPassword) {
        LoginInfo account = new LoginInfo();
        account.setId(userId);
        String encryptedPassword = passwordEncoder.encode(newPassword);
        account.setPassword(encryptedPassword);
        return loginInfoService.updateById(account);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<LoginInfoDTO> getUserWithAuthorities(String systemName) {
        return SecurityUtils.getCurrentUserLogin().flatMap(
            userName -> {
                LoginInfo info = loginInfoService.lambdaQuery().eq(LoginInfo::getUsername, userName).select(LoginInfo::getId, LoginInfo::getMobile, LoginInfo::getUsername, LoginInfo::getNickname, LoginInfo::getPassword, LoginInfo::getAvatar, LoginInfo::getPassword, LoginInfo::getActivated).one();
                if(info != null) {
                    LoginInfoDTO infoDTO = LoginInfoConverter.INSTANCE.to(info);
                    List<AccountRole> accountRoles = accountRoleService.lambdaQuery()
                            .eq(!StringUtils.isEmpty(systemName), AccountRole::getSystemName, systemName)
                            .eq(AccountRole::getAccountId, info.getId())
                            .orderByDesc(AccountRole::getId)
                            .list();
                    List<AccountRoleDTO> roles = accountRoles == null || accountRoles.size() == 0 ? new ArrayList<>() : AccountRoleConverter.INSTANCE.to(accountRoles);
                    if(systemName.equalsIgnoreCase("dcs-web-client")) {
                        // 检查是否有待检任务
                        AccountRoleDTO inspCenter = new AccountRoleDTO();
                        inspCenter.setId(-1L);
                        inspCenter.setRoleCode("DCS-CLIENT-OP");
                        inspCenter.setRoleName(infoDTO.getMobile());
                        inspCenter.setSystemName(systemName);
                        roles.add(inspCenter);
                    } else {
                        AccountRoleDTO roleDTO = new AccountRoleDTO();
                        roleDTO.setId(-2L);
                        roleDTO.setRoleCode("DCS-CLIENT-USER");
                        roleDTO.setRoleName("DCS-CLIENT管理员权限");
                        roleDTO.setSystemName(systemName);
                        roles.add(roleDTO);
                    }
                    infoDTO.setRoles(roles);
                    return Optional.ofNullable(infoDTO);
                } else {
                    return null;
                }
            });
    }

}
