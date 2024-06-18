package com.agile.qdmp.standalong.components;

import com.agile.tem.common.core.util.R;
import com.agile.qdmp.standalong.model.enums.CommonConstants;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: wenbinglei
 * @Date: 2020/11/30 14:33
 * @Description: 登录失败
 */
@Component
@Slf4j
public class CustomizeLoginFailHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException e) throws IOException {
        response.setCharacterEncoding(CommonConstants.UTF8);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.OK.value());
        response.getWriter().write(JSONObject.toJSONString(R.failed(e.getMessage())));
//        response.getWriter().write(JSONObject.toJSONString(R.failed("Token过期，登录失效。")));
    }
}
