package com.agile.qdmp.standalong.tool.api;

import com.agile.qdmp.standalong.model.entity.integrator.Contact;
import com.agile.qdmp.standalong.model.entity.integrator.Drawing;
import com.agile.qdmp.standalong.tool.error.IMErrorException;
import com.alibaba.fastjson.JSONObject;

/**
 * IM开放的API接口
 *
 * @Description:
 * @Author: wenbinglei
 * @Date: 2022/9/7 12:01
 */
public interface IMService {

    /**
     * 当本Service没有实现某个API的时候，可以用这个，针对所有API中的GET请求.
     *
     * @param queryParam 参数
     * @param url        请求接口地址
     * @return 接口响应字符串
     * @throws IMErrorException 异常
     */
    String get(String url, String queryParam) throws IMErrorException;

    /**
     * 当本Service没有实现某个API的时候，可以用这个，针对所有PI中的POST请求.
     *
     * @param url 请求接口地址
     * @param token token
     * @param obj 请求对象
     * @return 接口响应字符串
     * @throws IMErrorException 异常
     */
    String post(String url, String token, Object obj) throws IMErrorException;

//    /**
//     * 当本Service没有实现某个API的时候，可以用这个，针对所有PI中的POST请求.
//     *
//     * @param url 请求接口地址
//     * @param token token
//     * @param obj 请求对象
//     * @return 接口响应字符串
//     * @throws IMErrorException 异常
//     */
//    byte[] download(String url, String token, Object obj) throws IMErrorException;

    /**
     * 下载PDF文件并转化为base64图片
     * @param url
     * @param token
     * @param params
     * @param drawing
     * @return
     * @throws IMErrorException
     */
    Drawing downloadImage(String url, String token, JSONObject params, Drawing drawing) throws IMErrorException;

    /**
     * 设置当微信系统响应系统繁忙时，要等待多少 retrySleepMillis(ms) * 2^(重试次数 - 1) 再发起重试.
     *
     * @param retrySleepMillis 默认：1000ms
     */
    void setRetrySleepMillis(int retrySleepMillis);

    /**
     * <pre>
     * 设置当IM系统响应系统繁忙时，最大重试次数.
     * 默认：5次
     * </pre>
     *
     * @param maxRetryTimes 最大重试次数
     */
    void setMaxRetryTimes(int maxRetryTimes);
}
