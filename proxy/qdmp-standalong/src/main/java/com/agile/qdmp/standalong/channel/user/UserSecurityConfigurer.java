package com.agile.qdmp.standalong.channel.user;

import com.agile.qdmp.standalong.components.CustomizeAuthExceptionEntryPoint;
import com.agile.qdmp.standalong.components.CustomizeLoginFailHandler;
import com.agile.qdmp.standalong.components.CustomizeLoginSuccessHandler;
import com.agile.qdmp.standalong.service.uaa.MyUserDetailsService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Description: 用户名登录配置入口
 * @Author: wenbinglei
 * @Date: 2021/4/8 10:00
 */
@Getter
@Setter
public class UserSecurityConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private CustomizeAuthExceptionEntryPoint customizeAuthExceptionEntryPoint;

    @Autowired
    private AuthenticationEventPublisher defaultAuthenticationEventPublisher;

    @Autowired
    private CustomizeLoginSuccessHandler customizeLoginSuccessHandler;
    @Autowired
    private CustomizeLoginFailHandler customizeLoginFailHandler;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity http) {
        UserAuthenticationFilter usernameAuthenticationFilter = new UserAuthenticationFilter();
        usernameAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        usernameAuthenticationFilter.setAuthenticationSuccessHandler(customizeLoginSuccessHandler);
        usernameAuthenticationFilter.setAuthenticationFailureHandler(customizeLoginFailHandler);
        usernameAuthenticationFilter.setEventPublisher(defaultAuthenticationEventPublisher);
        usernameAuthenticationFilter.setAuthenticationEntryPoint(customizeAuthExceptionEntryPoint);

        UserAuthenticationProvider usernameAuthenticationProvider = new UserAuthenticationProvider();
        usernameAuthenticationProvider.setUserDetailsService(userDetailsService);
        http.authenticationProvider(usernameAuthenticationProvider).addFilterAfter(usernameAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
