package com.agile.qdmp.standalong.security;

import com.agile.qdmp.standalong.model.entity.account.LoginInfo;
import com.agile.qdmp.standalong.service.account.ILoginInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Adds the standard "iat" claim to tokens so we know when they have been created.
 * This is needed for a session timeout due to inactivity (ignored in case of "remember-me").
 * @Author: wenbinglei
 * @Date: 2021/4/2 17:23
 */
@Component
public class IatTokenEnhancer implements TokenEnhancer {

    @Autowired
    private ILoginInfoService loginInfoService;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
        Map<String, Object> additionalInformation = token.getAdditionalInformation();
        if (additionalInformation.isEmpty()) {
            additionalInformation = new LinkedHashMap<String, Object>();
        }
        // 1. 获取认证信息
        // 客户端
        String clientId = authentication.getOAuth2Request().getClientId();
        // 用户
        Authentication userAuthentication = authentication.getUserAuthentication();
        Object principal = userAuthentication.getPrincipal();
        if (principal instanceof User){
            User user= (User) principal;
            LoginInfo loginInfo = loginInfoService.lambdaQuery().eq(LoginInfo::getUsername, user.getUsername()).select(LoginInfo::getUsername, LoginInfo::getAvatar).one();
            System.out.println("loginInfo:" + loginInfo);
            additionalInformation.put("user_name", loginInfo.getUsername());
            additionalInformation.put("im_token_access", loginInfo.getAvatar());
//            Iterator<GrantedAuthority> iterator = user.getAuthorities().iterator();
//            while(iterator.hasNext()) {
//                String imToken = iterator.next().getAuthority();
//                if(imToken.indexOf("DCS-CLIENT") == -1) {
//                    additionalInformation.put("im_token_access", imToken);
//                    break;
//                }
//            }
        }
        // 2.设置到accessToken中
        additionalInformation.put("clientId", clientId);
        //add "iat" claim with current time in secs
        //this is used for an inactive session timeout
        additionalInformation.put("iat", new Integer((int)(System.currentTimeMillis()/1000L)));
        token.setAdditionalInformation(additionalInformation);

        return accessToken;
    }

    private void addClaims(DefaultOAuth2AccessToken accessToken) {
        DefaultOAuth2AccessToken token = accessToken;
        Map<String, Object> additionalInformation = token.getAdditionalInformation();
        if (additionalInformation.isEmpty()) {
            additionalInformation = new LinkedHashMap<String, Object>();
        }
        //add "iat" claim with current time in secs
        //this is used for an inactive session timeout
        additionalInformation.put("iat", new Integer((int)(System.currentTimeMillis()/1000L)));
        token.setAdditionalInformation(additionalInformation);
    }
}
