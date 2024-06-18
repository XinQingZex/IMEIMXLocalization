package com.agile.qdmp.standalong.channel.face;

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
 * @Description: 社交登录
 * @Author: wenbinglei
 * @Date: 2020/12/1 18:00
 */
@Slf4j
public class FaceAuthenticationProvider implements AuthenticationProvider {

    private MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

    private UserDetailsChecker detailsChecker = new CustomizePreAuthenticationChecks();

    @Getter
    @Setter
    private MyUserDetailsService userDetailsService;

    @Override
    @SneakyThrows
    public Authentication authenticate(Authentication authentication) {
        FaceAuthenticationToken socialAuthenticationToken = (FaceAuthenticationToken) authentication;

        String principal = socialAuthenticationToken.getPrincipal().toString();

        UserDetails userDetails = userDetailsService.loadUserByPhoto(principal);
        if (userDetails == null) {
            log.debug("Authentication failed: no credentials provided");

            throw new BadCredentialsException(messages
                    .getMessage("AbstractUserDetailsAuthenticationProvider.noopBindAccount", "Noop Bind Account"));
        }

        // 检查账号状态
        detailsChecker.check(userDetails);

        FaceAuthenticationToken authenticationToken = new FaceAuthenticationToken(userDetails,
                userDetails.getAuthorities());
        authenticationToken.setDetails(socialAuthenticationToken.getDetails());
        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return FaceAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
