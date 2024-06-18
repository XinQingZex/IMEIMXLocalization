package com.agile.qdmp.standalong.components;

import com.agile.tem.common.core.util.R;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: 用户成功退出
 * @Author: wenbinglei
 * @Date: 2020/11/25 9:46
 */
@Component
@Slf4j
public class CustomizeLogoutSuccessHandler implements LogoutSuccessHandler {

    private static final String BEARER_AUTHENTICATION = "Bearer ";
    private static final String HEADER_AUTHORIZATION = "authorization";

    @Autowired
    private TokenStore tokenStore;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String accessToken = request.getHeader(HEADER_AUTHORIZATION);
        if (StringUtils.isNotBlank(accessToken) && accessToken.startsWith(BEARER_AUTHENTICATION)) {
            accessToken = accessToken.replace(BEARER_AUTHENTICATION, "");
            OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(accessToken);
            response.setStatus(HttpStatus.OK.value());
            if (oAuth2AccessToken != null) {
                tokenStore.removeAccessToken(oAuth2AccessToken);
                OAuth2RefreshToken oAuth2RefreshToken = oAuth2AccessToken.getRefreshToken();
                tokenStore.removeRefreshToken(oAuth2RefreshToken);
                tokenStore.removeAccessTokenUsingRefreshToken(oAuth2RefreshToken);
                response.getWriter().append(JSONObject.toJSONString(R.ok("退出成功"))).flush();
            } else {
                response.getWriter().append(JSONObject.toJSONString(R.failed("退出失败"))).flush();
            }
        }
//        HttpUtils.writeSuccess(BaseResponse.createResponse(HttpStatusMsg.OK.getStatus(), "退出成功"), response);
    }
}