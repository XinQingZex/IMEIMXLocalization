package com.agile.qdmp.standalong.config;

import com.agile.qdmp.standalong.channel.mobile.MobileSecurityConfigurer;
import com.agile.qdmp.standalong.channel.face.FaceSecurityConfigurer;
import com.agile.qdmp.standalong.channel.user.UserSecurityConfigurer;
import com.agile.qdmp.standalong.channel.username.UsernameSecurityConfigurer;
import com.agile.qdmp.standalong.components.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

//import com.agile.qdmp.standalong.config.properties.HxProperties;

/**
 * @Author: wenbinglei
 * @Date: 2020/11/29 15:04
 * @Description:
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    private final TokenStore tokenStore;
    private final CustomizeLogoutSuccessHandler customizeLogoutSuccessHandler;
    private final CustomizeAuthExceptionEntryPoint customizeAuthExceptionEntryPoint;
    private final CustomizeAccessDeniedHandler customizeAccessDeniedHandler;

    public ResourceServerConfiguration(TokenStore tokenStore, CustomizeLogoutSuccessHandler customizeLogoutSuccessHandler, CustomizeAuthExceptionEntryPoint customizeAuthExceptionEntryPoint, CustomizeAccessDeniedHandler customizeAccessDeniedHandler) {
        this.tokenStore = tokenStore;
        this.customizeLogoutSuccessHandler = customizeLogoutSuccessHandler;
        this.customizeAuthExceptionEntryPoint = customizeAuthExceptionEntryPoint;
        this.customizeAccessDeniedHandler = customizeAccessDeniedHandler;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .csrf()
            .disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
           .apply(usernameSecurityConfigurer())
        .and()
           .apply(mobileSecurityConfigurer())
        .and()
            .apply(faceSecurityConfigurer())
        .and()
            .apply(userSecurityConfigurer())
        .and()
            .authorizeRequests()
                .antMatchers("/**/open/**").permitAll()
                .antMatchers("/refreshToken").permitAll()
                .antMatchers("/login/**").permitAll()
            .antMatchers("/api/**").authenticated()
        .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessHandler(customizeLogoutSuccessHandler)
        .and().headers().frameOptions().disable()
        ;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
            .resourceId("weijia-uaa")
            .tokenStore(tokenStore)
            // 认证异常处理类
            .authenticationEntryPoint(customizeAuthExceptionEntryPoint)
            // 权限异常处理类
            .accessDeniedHandler(customizeAccessDeniedHandler);
    }

    @Bean
    public MobileSecurityConfigurer mobileSecurityConfigurer() {
        return new MobileSecurityConfigurer();
    }

    @Bean
    public FaceSecurityConfigurer faceSecurityConfigurer() {
        return new FaceSecurityConfigurer();
    }

    @Bean
    public UsernameSecurityConfigurer usernameSecurityConfigurer() {
        return new UsernameSecurityConfigurer();
    }

    @Bean
    public UserSecurityConfigurer userSecurityConfigurer() {
        return new UserSecurityConfigurer();
    }

}
