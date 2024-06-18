package com.agile.qdmp.standalong.channel.user;

import com.agile.qdmp.standalong.components.CustomizePreAuthenticationChecks;
import com.agile.qdmp.standalong.service.uaa.MyUserDetailsService;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;

/**
 * @Description: 用户名密码登录校验逻辑
 * @Author: wenbinglei
 * @Date: 2020/12/2 11:07
 */
@Slf4j
public class UserAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsChecker detailsChecker = new CustomizePreAuthenticationChecks();

    @Getter
    @Setter
    private MyUserDetailsService userDetailsService;

    @Override
    @SneakyThrows
    public Authentication authenticate(Authentication authentication) {
        if(authentication.getCredentials() == null) {
            log.debug("Authentication failed: no credentials provided");
            throw new BadCredentialsException("用户名密码登录：客户端信息错误");
        }
        UserAuthenticationToken usernameAuthenticationToken = (UserAuthenticationToken) authentication;
        String principal = usernameAuthenticationToken.getPrincipal().toString();
        String credentials = usernameAuthenticationToken.getCredentials().toString();
        String serverUri = usernameAuthenticationToken.getServerUri();
        UserDetails userDetails = userDetailsService.loadUserByIM(principal, credentials, serverUri);
        if (userDetails == null) {
            log.debug("Authentication failed: no credentials provided");
            throw new BadCredentialsException("未找到用户");
        }
        // 检查账号状态
        detailsChecker.check(userDetails);

        UserAuthenticationToken authenticationToken = new UserAuthenticationToken(userDetails, authentication.getCredentials(), serverUri, userDetails.getAuthorities());
        authenticationToken.setDetails(usernameAuthenticationToken.getDetails());
        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UserAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
