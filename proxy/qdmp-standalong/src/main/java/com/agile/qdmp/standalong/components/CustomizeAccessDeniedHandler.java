package com.agile.qdmp.standalong.components;

import com.agile.tem.common.core.util.R;
import com.agile.qdmp.standalong.model.enums.CommonConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @Author: wenbinglei
 * @Date: 2021/4/2 15:22
 * @Description:
 */
@Component
@Slf4j
public class CustomizeAccessDeniedHandler implements AccessDeniedHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
//        log.info("CustomAccessDeniedHandler handle {}", accessDeniedException);
        response.setCharacterEncoding(CommonConstants.UTF8);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.OK.value());
        PrintWriter printWriter = response.getWriter();
//        printWriter.append(objectMapper.writeValueAsString(R.failed(accessDeniedException.getMessage())));
        printWriter.append(objectMapper.writeValueAsString(R.failed("凭证已过期，请重新登录")));
    }

}
