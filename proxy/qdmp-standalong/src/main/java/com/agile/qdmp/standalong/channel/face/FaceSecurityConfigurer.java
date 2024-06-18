package com.agile.qdmp.standalong.channel.face;

import com.agile.qdmp.standalong.components.CustomizeAuthExceptionEntryPoint;
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
 * @Description: 第三方登录入口
 * @Author: wenbinglei
 * @Date: 2020/12/1 17:25
 */
@Getter
@Setter
public class FaceSecurityConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private CustomizeAuthExceptionEntryPoint customizeAuthExceptionEntryPoint;

    @Autowired
    private AuthenticationEventPublisher defaultAuthenticationEventPublisher;

    @Autowired
    private CustomizeLoginSuccessHandler customizeLoginSuccessHandler;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity http) {
        FaceAuthenticationFilter socialAuthenticationFilter = new FaceAuthenticationFilter();
        socialAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        socialAuthenticationFilter.setAuthenticationSuccessHandler(customizeLoginSuccessHandler);
        socialAuthenticationFilter.setEventPublisher(defaultAuthenticationEventPublisher);
        socialAuthenticationFilter.setAuthenticationEntryPoint(customizeAuthExceptionEntryPoint);

        FaceAuthenticationProvider socialAuthenticationProvider = new FaceAuthenticationProvider();
        socialAuthenticationProvider.setUserDetailsService(userDetailsService);
        http.authenticationProvider(socialAuthenticationProvider).addFilterAfter(socialAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class);
    }

}
