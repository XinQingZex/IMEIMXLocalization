package com.agile.qdmp.standalong.utils;

import com.agile.tem.common.core.controller.exception.BizException;
import com.agile.qdmp.standalong.model.enums.CommonConstants;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.codec.Base64;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

/**
 * @Description: 认证授权相关工具类
 * @Author: wenbinglei
 * @Date: 2020/12/2 12:00
 */
@Slf4j
@UtilityClass
public class AuthUtils {

    private final String BASIC_ = "Basic ";
    private final String SPLITE_COLON = ":";

    /**
     * 从header 请求中的clientId/clientSecret
     *
     * @param header header中的参数
     * @throws RuntimeException if the Basic header is not present or is not valid Base64
     */
    @SneakyThrows
    public String[] extractAndDecodeHeader(String header) {
        byte[] base64Token = header.substring(6).getBytes(CommonConstants.UTF8);
        byte[] decoded;
        try {
            decoded = Base64.decode(base64Token);
        } catch (IllegalArgumentException e) {
            throw new BizException("请求头中client信息格式错误");
        }
        String token = new String(decoded, StandardCharsets.UTF_8);
        String[] clientArrays = token.split(SPLITE_COLON);
        if (clientArrays.length != 2) {
            throw new BizException("请求头中client信息格式不正确");
        }
        return clientArrays;
    }

    /**
     * *
     *
     * @param request
     * @return
     */
    /**
     * 从header 请求中的clientId/clientSecret
     * @param request
     * @return
     */
    @SneakyThrows
    public String[] extractAndDecodeHeader(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith(BASIC_)) {
            throw new BizException("请求头中client信息为空");
        }
        return extractAndDecodeHeader(header);
    }
}
