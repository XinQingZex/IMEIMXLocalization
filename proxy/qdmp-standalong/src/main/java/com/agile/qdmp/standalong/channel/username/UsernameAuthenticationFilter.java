package com.agile.qdmp.standalong.channel.username;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @Description: 用户名密码登录验证filter
 * @Author: wenbinglei
 * @Date: 2020/12/2 10:56
 */
public class UsernameAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private static final String SPRING_SECURITY_RESTFUL_USERNAME = "username";
    private static final String SPRING_SECURITY_RESTFUL_PASSWORD = "password";
    private static final String SPRING_SECURITY_RESTFUL_LOGIN_URL = "/login/username";

    @Getter
    @Setter
    private String usernameParameter = SPRING_SECURITY_RESTFUL_USERNAME;

    @Getter
    @Setter
    private boolean postOnly = true;

    @Getter
    @Setter
    private AuthenticationEventPublisher eventPublisher;

    @Getter
    @Setter
    private AuthenticationEntryPoint authenticationEntryPoint;

    public UsernameAuthenticationFilter() {
        super(new AntPathRequestMatcher(SPRING_SECURITY_RESTFUL_LOGIN_URL, "POST"));
    }

    @Override
    @SneakyThrows
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        if (postOnly && !request.getMethod().equals(HttpMethod.POST.name())) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        // 用户名密码登录
        String principal = obtainParameter(request, SPRING_SECURITY_RESTFUL_USERNAME);
        String credentials = obtainParameter(request, SPRING_SECURITY_RESTFUL_PASSWORD);
        if(StringUtils.isBlank(principal)) {
            String payload = getRequestPayload(request);
            if(StringUtils.isNotBlank(payload)) {
                principal = JSONObject.parseObject(payload).getString(SPRING_SECURITY_RESTFUL_USERNAME);
                credentials = JSONObject.parseObject(payload).getString(SPRING_SECURITY_RESTFUL_PASSWORD);
            }
        }

        UsernameAuthenticationToken usernameAuthenticationToken = new UsernameAuthenticationToken(principal, credentials);
        // Allow subclasses to set the "details" property
        setDetails(request, usernameAuthenticationToken);

        Authentication authResult = null;
        try {
            authResult = this.getAuthenticationManager().authenticate(usernameAuthenticationToken);
            logger.debug("Authentication success: " + authResult);
            SecurityContextHolder.getContext().setAuthentication(authResult);
        } catch (Exception failed) {
            SecurityContextHolder.clearContext();
            logger.debug("Authentication request failed: " + failed);
            eventPublisher.publishAuthenticationFailure(new BadCredentialsException(failed.getMessage(), failed), new PreAuthenticatedAuthenticationToken("access-token", "N/A"));
            try {
                authenticationEntryPoint.commence(request, response,
                        new UsernameNotFoundException(failed.getMessage(), failed));
            } catch (Exception e) {
                logger.error("authenticationEntryPoint handle error:{}", failed);
            }
        }

        return authResult;
    }

    private String obtainParameter(HttpServletRequest request, String parameter) {
        String result = request.getParameter(parameter);
        return result == null ? "" : result.trim();
    }

    private void setDetails(HttpServletRequest request, UsernameAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    private String getRequestPayload(HttpServletRequest req) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = req.getReader();) {
            char[] buff = new char[1024];
            int len;
            while ((len = reader.read(buff)) != -1) {
                sb.append(buff, 0, len);
            }
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
        return sb.toString();
    }
}
