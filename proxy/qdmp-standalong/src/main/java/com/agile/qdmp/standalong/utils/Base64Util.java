package com.agile.qdmp.standalong.utils;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

/**
 * @Author: wenbinglei
 * @Date: 2022/4/22 16:36
 * @Description:
 */
public class Base64Util {
    // 字符串编码
    private static final String UTF_8 = "UTF-8";

    /**
     * 加密字符串
     *
     * @param inputData
     * @return
     */
    public static String decodeData(String inputData) {
        try {
            if (null == inputData) {
                return null;
            }
            return new String(Base64.decodeBase64(inputData.getBytes(UTF_8)), UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密加密后的字符串
     *
     * @param inputData
     * @return
     */
    public static String encodeData(String inputData) {
        try {
            if (null == inputData) {
                return null;
            }
            return new String(Base64.encodeBase64(inputData.getBytes(UTF_8)), UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String s = "sec-app-bag:sec-app-bag@";
        String encodeStr = Base64Util.encodeData(s);
        System.out.println(encodeStr);
        System.out.println(Base64Util.decodeData(encodeStr));
    }
}
