package com.agile.qdmp.standalong.components;

import com.agile.tem.common.core.util.R;
import com.agile.qdmp.standalong.model.enums.CommonConstants;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @Description: 客户端异常处理 {@link AuthenticationException } 不同细化异常处理
 * @Author: wenbinglei
 * @Date: 2020/12/1 17:50
 */
@Slf4j
@Component
@AllArgsConstructor
public class CustomizeAuthExceptionEntryPoint implements AuthenticationEntryPoint {

    @Override
    @SneakyThrows
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        response.setCharacterEncoding(CommonConstants.UTF8);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

//        R result = R.failed();
//        // result.setData(authException);
//        if(authException instanceof CredentialsExpiredException) {
//            result.setMsg("认证失败：凭证已过期");
//        } else if(authException instanceof InsufficientAuthenticationException) {
//            result.setMsg("认证失败：缺少客户端身份验证信息");
//        } else if(authException instanceof DisabledException) {
//            result.setMsg("认证失败：用户已被禁用");
//        } else if(authException instanceof BadCredentialsException) {
//            result.setMsg("认证失败：授权服务器认证失败");
//        } else if(authException instanceof LockedException) {
//            result.setMsg("认证失败：账户锁定");
//        } else if(authException instanceof AccountExpiredException) {
//            result.setMsg("认证失败：账户过期");
//        } else if(authException instanceof UsernameNotFoundException) {
//            result.setMsg("认证失败：未找到用户");
//        } else if(authException.getCause() instanceof InvalidTokenException) {
//            result.setMsg("认证失败：无效或过期token");
//        } else{
//            result.setMsg(authException.getMessage());
//        }
        response.setStatus(HttpStatus.OK.value());
        PrintWriter printWriter = response.getWriter();
        printWriter.append(JSONObject.toJSONString(R.failed(authException.getMessage())));
//        printWriter.append(JSONObject.toJSONString(R.failed("凭证已过期，请重新登录")));
    }

}
