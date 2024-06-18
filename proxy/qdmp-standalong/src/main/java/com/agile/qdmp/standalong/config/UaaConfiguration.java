package com.agile.qdmp.standalong.config;

import com.agile.qdmp.standalong.components.CustomizeOAuth2WebResponseExceptionTranslator;
import com.agile.qdmp.standalong.config.properties.UaaProperties;
import com.agile.qdmp.standalong.security.IatTokenEnhancer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.sql.DataSource;
import java.security.KeyPair;
import java.util.ArrayList;
import java.util.Collection;

@Configuration
@EnableAuthorizationServer
public class UaaConfiguration extends AuthorizationServerConfigurerAdapter implements ApplicationContextAware {

    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    private final UserDetailsService myUserDetailsService;
    private final UaaProperties uaaProperties;
    private final JdbcClientDetailsService localClientDetailsServiceImpl;
    private final CustomizeOAuth2WebResponseExceptionTranslator customizeOAuth2WebResponseExceptionTranslator;

    public UaaConfiguration(UserDetailsService myUserDetailsService, UaaProperties uaaProperties, JdbcClientDetailsService localClientDetailsServiceImpl, CustomizeOAuth2WebResponseExceptionTranslator customizeOAuth2WebResponseExceptionTranslator) {
        this.myUserDetailsService = myUserDetailsService;
        this.uaaProperties = uaaProperties;
        this.localClientDetailsServiceImpl = localClientDetailsServiceImpl;
        this.customizeOAuth2WebResponseExceptionTranslator = customizeOAuth2WebResponseExceptionTranslator;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(localClientDetailsServiceImpl);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //pick up all  TokenEnhancers incl. those defined in the application
        //this avoids changes to this class if an application wants to add its own to the chain
        Collection<TokenEnhancer> tokenEnhancers = applicationContext.getBeansOfType(TokenEnhancer.class).values();
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(new ArrayList<>(tokenEnhancers));
        endpoints
            .authenticationManager(authenticationManager)
            .userDetailsService(myUserDetailsService)
            .accessTokenConverter(jwtAccessTokenConverter())
            .tokenStore(tokenStore())
            .tokenEnhancer(tokenEnhancerChain)
//            .tokenEnhancer(customTokenEnhancer())
            // //配置WebResponseExceptionTranslator自定义异常，并重写translate方法返回自定义Oauth2认证异常信息
             .exceptionTranslator(customizeOAuth2WebResponseExceptionTranslator)
            // don't reuse or we will run into session inactivity timeouts
            .reuseRefreshTokens(false);
    }

    @Autowired
    private DataSource dataSource;
    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
//        MyRedisTokenStore tokenStore = new MyRedisTokenStore(redisConnectionFactory);
//        tokenStore.setPrefix(AuthoritiesConstants.UAA_PREFIX + AuthoritiesConstants.OAUTH_PREFIX);
//        return tokenStore;
    }

    /**
     * This bean generates an token enhancer, which manages the exchange between JWT acces tokens and Authentication
     * in both directions.
     *
     * @return an access token converter configured with the authorization server's public/private keys
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        KeyPair keyPair = new KeyStoreKeyFactory(
             new ClassPathResource(uaaProperties.getKeyStore().getName()), uaaProperties.getKeyStore().getPassword().toCharArray())
             .getKeyPair(uaaProperties.getKeyStore().getAlias());
        converter.setKeyPair(keyPair);
        return converter;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
//        oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
//        oauthServer.tokenKeyAccess("permitAll()")
//                .checkTokenAccess("permitAll()")
//                .allowFormAuthenticationForClients();
        oauthServer
        .allowFormAuthenticationForClients()
        // 开启/oauth/token_key验证端口无权限访问
        .tokenKeyAccess("permitAll()")
        // 开启/oauth/check_token验证端口认证权限访问
        .checkTokenAccess("isAuthenticated()");
//        .addTokenEndpointAuthenticationFilter(customizeBasicAuthenticationFilter);
    }

//    @Bean
//    public TokenEnhancer customTokenEnhancer(){
//        return new IatTokenEnhancer();
//    }
}
