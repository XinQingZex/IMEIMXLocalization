package com.agile.qdmp.standalong.channel.mobile;

import com.agile.qdmp.standalong.components.CustomizePreAuthenticationChecks;
import com.agile.qdmp.standalong.service.uaa.MyUserDetailsService;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;

/**
 * @Description: 手机登录校验逻辑 验证码登录
 * @Author: wenbinglei
 * @Date: 2020/12/2 11:07
 */
@Slf4j
public class MobileAuthenticationProvider implements AuthenticationProvider {

    private MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

    private UserDetailsChecker detailsChecker = new CustomizePreAuthenticationChecks();

    @Getter
    @Setter
    private MyUserDetailsService userDetailsService;

    @Override
    @SneakyThrows
    public Authentication authenticate(Authentication authentication) {
        if(authentication.getCredentials() == null) {
            log.debug("Authentication failed: no credentials provided");
            throw new BadCredentialsException(messages.getMessage("PhoneAuthenticationProvider.badCredentials", "Bad credentials"));
        }
        MobileAuthenticationToken mobileAuthenticationToken = (MobileAuthenticationToken) authentication;
        String principal = mobileAuthenticationToken.getPrincipal().toString();
        String presentedPassword = authentication.getCredentials().toString();

//        // 验证码验证，调用公共服务查询 key 为authentication.getPrincipal()的value， 并判断其与验证码是否匹配
//        if(!userDetailsService.checkCode(principal, presentedPassword)){
//            log.debug("Authentication failed: verifyCode does not match stored value");
//            throw new BadCredentialsException(messages.getMessage("PhoneAuthenticationProvider.badCredentials", "Bad verifyCode"));
//        }

        UserDetails userDetails = userDetailsService.loadUserByMobile(principal);
        if (userDetails == null) {
            log.debug("Authentication failed: no credentials provided");

            throw new BadCredentialsException(messages
                    .getMessage("AbstractUserDetailsAuthenticationProvider.noopBindAccount", "Noop Bind Account"));
        }

        // 检查账号状态
        detailsChecker.check(userDetails);

        MobileAuthenticationToken authenticationToken = new MobileAuthenticationToken(userDetails, authentication.getCredentials(), userDetails.getAuthorities());
        authenticationToken.setDetails(mobileAuthenticationToken.getDetails());
        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return MobileAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
