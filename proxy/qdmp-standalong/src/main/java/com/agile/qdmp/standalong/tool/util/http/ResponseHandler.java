package com.agile.qdmp.standalong.tool.util.http;

/**
 * <pre>
 * http请求响应回调处理接口.
 * @Description:
 * @Author: wenbinglei
 * @Date: 2022/9/7 15:47
 */
public interface ResponseHandler<T> {
    /**
     * 响应结果处理.
     *
     * @param t 要处理的对象
     */
    void handle(T t);
}
