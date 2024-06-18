package com.agile.qdmp.standalong.controller.uaa;

import com.agile.tem.common.core.util.R;
import com.agile.qdmp.standalong.utils.AuthUtils;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidClientException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author: wenbinglei
 * @Date: 2021/4/6 08:58
 * @Description: 复写刷新token功能
 */
@RestController
@Slf4j
@Api(value = "客户端刷新token接口", tags = "客户端刷新token接口")
public class RefreshTokenController {

    @Autowired
    private TokenEndpoint tokenEndpoint;
    @Autowired
    private ClientDetailsService clientDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 客户端刷新token接口
     * @param request
     * @param parameters
     * @return
     * @throws HttpRequestMethodNotSupportedException
     */
    @RequestMapping(value = "/refreshToken", method = RequestMethod.POST)
    public R<OAuth2AccessToken> postAccessToken(HttpServletRequest request, @RequestParam Map<String, String> parameters) throws HttpRequestMethodNotSupportedException {
        String[] tokens = AuthUtils.extractAndDecodeHeader(request);
        assert tokens.length == 2;
        String clientId = tokens[0];
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
        log.info("clientDetails {} {}=={}", clientDetails.getClientId(), clientDetails.getClientSecret(), tokens[1]);
        if (null == clientDetails) {
            throw new UnapprovedClientAuthenticationException("clientId不存在" + clientId);
        }
        // 校验secret
        if (!passwordEncoder.matches(tokens[1], clientDetails.getClientSecret())) {
            throw new InvalidClientException("clientSecret不正确");
        }
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(clientDetails.getClientId(), clientDetails.getClientSecret(), clientDetails.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(token);
        try {
            OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(token, parameters).getBody();
            return R.ok(oAuth2AccessToken);
        } catch(InvalidGrantException e) {
            log.info(e.getMessage());
            return R.failed("refreshToken失效，请重新登录");
        }
    }
}