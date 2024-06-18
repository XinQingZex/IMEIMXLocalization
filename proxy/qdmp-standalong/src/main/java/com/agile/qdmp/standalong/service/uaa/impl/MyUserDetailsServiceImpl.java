package com.agile.qdmp.standalong.service.uaa.impl;

import com.agile.qdmp.standalong.config.SysConfig;
import com.agile.qdmp.standalong.model.dto.account.LoginInfoDTO;
import com.agile.qdmp.standalong.model.dto.rbac.AccountRoleDTO;
import com.agile.qdmp.standalong.model.dto.uaa.RegistDTO;
import com.agile.qdmp.standalong.model.entity.account.LoginInfo;
import com.agile.qdmp.standalong.model.entity.integrator.Contact;
import com.agile.qdmp.standalong.model.entity.integrator.Dimension;
import com.agile.qdmp.standalong.model.entity.integrator.Procedure;
import com.agile.qdmp.standalong.service.account.ILoginInfoService;
import com.agile.qdmp.standalong.service.integrator.IContactService;
import com.agile.qdmp.standalong.service.integrator.IDimensionService;
import com.agile.qdmp.standalong.service.integrator.IImService;
import com.agile.qdmp.standalong.service.integrator.IProcedureService;
import com.agile.qdmp.standalong.service.uaa.IUserService;
import com.agile.qdmp.standalong.service.uaa.MyUserDetailsService;
import com.agile.qdmp.standalong.tool.api.IMService;
import com.agile.qdmp.standalong.tool.error.IMErrorException;
import com.agile.qdmp.standalong.utils.AuthUtils;
import com.agile.tem.common.core.util.R;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Description: 用户详细信息
 * @Author: wenbinglei
 * @Date: 2020/12/1 18:38
 */
@Slf4j
@Service
@AllArgsConstructor
public class MyUserDetailsServiceImpl implements MyUserDetailsService {

    @Autowired
    private final ILoginInfoService loginInfoService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ClientDetailsService clientDetailsService;
    @Autowired
    private final IMService imService;
    @Autowired
    private final IUserService userService;
    @Autowired
    private final IDimensionService dimensionService;
    @Autowired
    private final IContactService contactService;
    @Autowired
    private final IProcedureService procedureService;
    @Autowired
    private SysConfig sysConfig;

    private final static String ACCESS_TOKEN_KEY = "AccessToken";

    /**
     * 获取登录账户信息
     *
     * @param loginName
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    @SneakyThrows
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        // 检查登录终端
        String clientId = checkClient();
        if (StringUtils.isBlank(clientId)) {
            throw new BadCredentialsException("请求头中client信息为空");
        }
        log.info("do login user {} {}", loginName, clientId);
//        String lowercaseLoginNameName = loginName.toLowerCase(Locale.ENGLISH);
        String lowercaseLoginNameName = loginName;

        LoginInfoDTO accountInfo = loginInfoService.getAccountWithAuthoritiesByUsername(lowercaseLoginNameName, clientId);
        if (accountInfo == null) {
            log.error("查询不到用户信息 {} {}", loginName, clientId);
            throw new UsernameNotFoundException("查询不到用户信息");
        }
        log.info("用户信息 accountInfo {}", accountInfo);
        // 判断用户状态
        if (!accountInfo.getActivated()) {
            log.error("当前用户限制登录 {}", loginName);
            throw new LockedException("当前用户限制登录");
        }

        List<AccountRoleDTO> roles =new ArrayList<>();
        AccountRoleDTO role = new AccountRoleDTO();
        role.setRoleCode("DCS-CLIENT-DEFAULT");
        roles.add(role);
        accountInfo.setRoles(roles);
        // 判断当前用户是否具有登录权限
//        List<AccountRoleDTO> roles = accountInfo.getRoles();
//        log.info("当前用户权限 {}", roles);
//        Boolean hasRole = checkRoles(roles, clientId);
//        if(!hasRole) {
//            log.error("用户 {} 终端 {} 的登录受限", loginName, clientId);
//            throw new InvalidGrantException("用户没有当前终端的登录权限");
//        }
        return createSpringSecurityUser(lowercaseLoginNameName, accountInfo);
    }

    /**
     * 手机号码登录
     * @param mobile
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    @SneakyThrows
    public UserDetails loadUserByMobile(String mobile) throws UsernameNotFoundException {
        // 检查登录终端
        String clientId = checkClient();
        // 获取账户信息
        LoginInfoDTO accountInfo = loginInfoService.getAccountWithAuthoritiesByMobile(mobile, clientId);
        if (accountInfo == null) {
            log.error("查询不到手机用户信息 {} {}", mobile, clientId);
            throw new UsernameNotFoundException("查询不到手机用户信息");
        }
        log.error("手机用户信息 accountInfo {}", accountInfo);
        // 判断用户状态
        if (!accountInfo.getActivated()) {
            log.error("当前手机用户限制登录 {}", mobile);
            throw new UsernameNotFoundException("当前手机用户限制登录");
        }

        // 判断当前用户是否具有登录权限
        List<AccountRoleDTO> roles = accountInfo.getRoles();
        log.info("当前手机用户权限 {}", roles);
        Boolean hasRole = checkRoles(roles, clientId);
        if(!hasRole) {
            log.error("手机用户 {} 终端 {} 的登录受限", mobile, clientId);
            throw new InvalidGrantException("手机用户没有当前终端的登录权限");
        }

        return createSpringSecurityUser(accountInfo.getUsername(), accountInfo);
    }

    @Override
    public UserDetails loadUserByUsernameAndPwd(String username, String password) throws UsernameNotFoundException {
        // 检查登录终端
        String clientId = checkClient();
        if (StringUtils.isBlank(clientId)) {
            throw new BadCredentialsException("请求头中client信息为空");
        }
        if (StringUtils.isBlank(username)) {
            throw new BadCredentialsException("缺少用户名");
        }
        if (StringUtils.isBlank(password)) {
            throw new BadCredentialsException("缺少密码");
        }
        log.info("do login user {} {}", username, clientId);
        String lowercaseLoginNameName = username.toLowerCase(Locale.ENGLISH);

        LoginInfoDTO accountInfo = loginInfoService.getAccountWithAuthoritiesByUsername(lowercaseLoginNameName, clientId);
        if (accountInfo == null) {
            log.error("查询不到用户信息 {} {}", username, clientId);
            throw new UsernameNotFoundException("查询不到用户信息");
        }

        log.info("用户信息 accountInfo {}, password {}", accountInfo, password);
        if(!passwordEncoder.matches(password, accountInfo.getPassword())) {
            throw new BadCredentialsException("用户名密码错误");
        }

        // 判断用户状态
        if (!accountInfo.getActivated()) {
            log.error("当前用户限制登录 {}", username);
            throw new LockedException("当前用户限制登录");
        }

        // 判断当前用户是否具有登录权限
        List<AccountRoleDTO> roles = accountInfo.getRoles();
        log.info("当前用户权限 {}", roles);
        Boolean hasRole = checkRoles(roles, clientId);
        if(!hasRole) {
            log.error("用户 {} 终端 {} 的登录受限", username, clientId);
            throw new InvalidGrantException("用户没有当前终端的登录权限");
        }
        return createSpringSecurityUser(lowercaseLoginNameName, accountInfo);
    }

    @Override
    public UserDetails loadUserByIM(String username, String password, String serverUri) throws UsernameNotFoundException {
        // 检查登录终端
        String clientId = checkClient();
        if (StringUtils.isBlank(clientId)) {
            throw new BadCredentialsException("请求头中client信息为空");
        }
//        if (StringUtils.isBlank(serverUri)) {
//            throw new BadCredentialsException("缺少代理服务器地址");
//        }
        if (StringUtils.isBlank(username)) {
            throw new BadCredentialsException("缺少用户名");
        }
        if (StringUtils.isBlank(password)) {
            throw new BadCredentialsException("缺少密码");
        }
        log.info("loadUserByIM do login im user {} {}", username, serverUri);

        Contact contact = null;
        if(clientId.equalsIgnoreCase("dcs-web-client")) {
            contact = contactService.getByCustomUser(username);
            // 检查是否有待检任务
            if(contact == null) {
                throw new BadCredentialsException("当前登录账户未在IM中配置");
            } else {
//                if(StringUtils.isBlank(contact.getInspCenterGuid())) {
//                    throw new BadCredentialsException("登录账户未设置所属检测中心");
//                }
//                List<Procedure> procedures = procedureService.lambdaQuery().in(Procedure::getInspCenterGuid, contact.getInspCenterGuid().split(",")).list();
//                if(Objects.isNull(procedures) || procedures.size() == 0) {
//                    throw new BadCredentialsException("账户所属检测中心没有待检程序");
//                }
//                Integer dimCount = dimensionService.lambdaQuery().in(Dimension::getInspCenterGuid, contact.getInspCenterGuid().split(",")).count();
//                if(dimCount.intValue() == 0) {
//                    throw new BadCredentialsException("账户所属检测中心没有待检尺寸");
//                }
            }
        }

        try {
            JSONObject postData = new JSONObject();
            postData.put("Username", username);
            postData.put("Password", password);
            String apiResult = imService.post(sysConfig.getServerUri() + "/api/auth/token", null, postData);
            JSONObject res = JSONObject.parseObject(apiResult);
            if(res.containsKey(ACCESS_TOKEN_KEY) && StringUtils.isNotBlank(res.getString(ACCESS_TOKEN_KEY))) {
                LoginInfoDTO accountInfo = new LoginInfoDTO();
                accountInfo.setUsername(username);
                accountInfo.setPassword(password);
                accountInfo.setActivated(true);

                List<AccountRoleDTO> roles =new ArrayList<>();
                AccountRoleDTO role = new AccountRoleDTO();
                role.setRoleCode(res.getString(ACCESS_TOKEN_KEY));
                roles.add(role);
                accountInfo.setRoles(roles);

                // 此处需要新注册用户（可能多个服务器端中用户名重复）
                RegistDTO infoDTO = new RegistDTO();
                infoDTO.setUsername(accountInfo.getUsername());
                infoDTO.setPassword(accountInfo.getPassword());
                infoDTO.setSource(clientId);
                infoDTO.setNickname(accountInfo.getUsername());
                if(clientId.equalsIgnoreCase("dcs-web-client")) {
                    infoDTO.setGrantDefaultRole(false);
                    infoDTO.setMobile(contact.getEmployeeId());
                }
                infoDTO.setActivated(true);
                R<LoginInfoDTO> loginInfoDTOR = userService.register(infoDTO, password);
                log.info("----------> loginInfoDTOR {}", loginInfoDTOR);
                loginInfoService.lambdaUpdate().eq(LoginInfo::getUsername, accountInfo.getUsername()).set(LoginInfo::getAvatar, res.getString(ACCESS_TOKEN_KEY)).update();

                return createSpringSecurityUser(accountInfo.getUsername(), accountInfo);
            } else {
                log.error("用户登录Server端错误 {} {}", username, serverUri);
                throw new UsernameNotFoundException("用户登录Server端错误，请确认用户名和密码。");
            }
        } catch (IMErrorException e) {
            log.error(e.getMessage());
            throw new UsernameNotFoundException("连接Server端超时，请重试。");
        }

    }

    @Override
    public UserDetails loadUserByPhoto(String filePath) throws UsernameNotFoundException {
        return null;
    }

    /**
     * 检查客户端
     * @return
     */
    private String checkClient() {
        String[] clientToken = getClientId();
        if(clientToken == null) {
            return null;
        }
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientToken[0]);
        log.info("clientDetails {} {}=={}", clientDetails.getClientId(), clientDetails.getClientSecret(), clientToken[1]);
        if (null == clientDetails) {
            throw new UnapprovedClientAuthenticationException("clientId不存在" + clientToken[0]);
        }
        // 校验secret
        if (!passwordEncoder.matches(clientToken[1], clientDetails.getClientSecret())) {
            throw new InvalidClientException("clientSecret不正确");
        }
        return clientToken[0];
    }

    /**
     * 获取ClientId
     *
     * @return
     */
    private String[] getClientId() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return null;
        }
        HttpServletRequest request = attributes.getRequest();
        return AuthUtils.extractAndDecodeHeader(request);
    }

    /**
     * 检查当前登录用户是否拥有终端访问权限
     *
     * @param roles
     * @param clientId
     * @return
     */
    private Boolean checkRoles(List<AccountRoleDTO> roles, String clientId) {
        Boolean result = Boolean.FALSE;
        if (roles != null && roles.size() > 0) {
            for (AccountRoleDTO roleDTO : roles) {
                if (roleDTO.getSystemName().equalsIgnoreCase(clientId)) {
                    result = Boolean.TRUE;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 构建登录用户
     *
     * @param lowercaseLoginName
     * @param account
     * @return
     */
    private org.springframework.security.core.userdetails.User createSpringSecurityUser(String lowercaseLoginName, LoginInfoDTO account) {
        log.info("createSpringSecurityUser lowercaseLoginName {}, account{}", lowercaseLoginName, account);
        if (!account.getActivated()) {
            throw new InvalidGrantException("Account " + lowercaseLoginName + " was not activated");
        }
        List<GrantedAuthority> grantedAuthorities = account.getRoles().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getRoleCode()))
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(account.getUsername(),
                account.getPassword(),
                grantedAuthorities);
    }

}
