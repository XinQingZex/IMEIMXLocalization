package com.agile.qdmp.standalong.tool.util.http;

/**
 * @Description:
 * @Author: wenbinglei
 * @Date: 2022/9/7 15:47
 */
public interface RequestHttp<H> {

    /**
     * 返回httpClient.
     *
     * @return 返回httpClient
     */
    H getRequestHttpClient();

}
