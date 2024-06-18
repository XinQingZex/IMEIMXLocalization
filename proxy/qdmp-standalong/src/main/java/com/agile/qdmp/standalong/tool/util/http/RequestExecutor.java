package com.agile.qdmp.standalong.tool.util.http;

import com.agile.qdmp.standalong.tool.error.IMErrorException;

import java.io.IOException;

/**
 * http请求执行器.
 *
 * @Description:
 * @Author: wenbinglei
 * @Date: 2022/9/7 15:47
 */
public interface RequestExecutor<T, E> {

    /**
     * 执行http请求.
     *
     * @param uri    uri
     * @param token   token
     * @param data   数据
     * @return 响应结果
     * @throws IMErrorException 自定义异常
     * @throws IOException      io异常
     */
    T execute(String uri, String token, E data) throws IMErrorException, IOException;

    /**
     * 执行http请求.
     *
     * @param uri     uri
     * @param token    token
     * @param data    数据
     * @param handler http响应处理器
     * @throws IMErrorException 自定义异常
     * @throws IOException      io异常
     */
    void execute(String uri, String token, E data, ResponseHandler<T> handler) throws IMErrorException, IOException;


}
