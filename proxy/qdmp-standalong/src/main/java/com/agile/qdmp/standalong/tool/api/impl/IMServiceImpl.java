package com.agile.qdmp.standalong.tool.api.impl;

import com.agile.qdmp.standalong.tool.util.http.apache.DefaultApacheHttpClientBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * <pre>
 * 默认接口实现类，使用apache httpclient实现
 * @Description:
 * @Author: wenbinglei
 * @Date: 2022/9/21 21:09
 */
@Service
public class IMServiceImpl extends BaseIMServiceImpl<CloseableHttpClient> {
    private CloseableHttpClient httpClient;

    @Override
    public CloseableHttpClient getRequestHttpClient() {
        return httpClient;
    }

    @PostConstruct
    public void initHttp() {
        DefaultApacheHttpClientBuilder apacheHttpClientBuilder = DefaultApacheHttpClientBuilder.get();
        this.httpClient = apacheHttpClientBuilder.build();
    }
}
