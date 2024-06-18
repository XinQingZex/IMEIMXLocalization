package com.agile.qdmp.standalong.tool.util.http.apache;

import com.agile.qdmp.standalong.tool.error.IMErrorException;
import com.agile.qdmp.standalong.tool.util.http.RequestHttp;
import com.agile.qdmp.standalong.tool.util.http.SimpleGetRequestExecutor;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;

/**
 * @Description:
 * @Author: wenbinglei
 * @Date: 2022/9/7 16:39
 */
public class ApacheSimpleGetRequestExecutor extends SimpleGetRequestExecutor<CloseableHttpClient> {
    public ApacheSimpleGetRequestExecutor(RequestHttp requestHttp) {
        super(requestHttp);
    }

    @Override
    public String execute(String uri, String token, String queryParam) throws IMErrorException, IOException {
        if (queryParam != null) {
            if (uri.indexOf('?') == -1) {
                uri += '?';
            }
            uri += uri.endsWith("?") ? queryParam : '&' + queryParam;
        }
        HttpGet httpGet = new HttpGet(uri);
        if(StringUtils.isNotBlank(token)) {
            httpGet.setHeader("Authorization", "Bearer " + "accessToken");
        }
        try (CloseableHttpResponse response = requestHttp.getRequestHttpClient().execute(httpGet)) {
            String responseContent = Utf8ResponseHandler.INSTANCE.handleResponse(response);
            return handleResponse(responseContent);
        } finally {
            httpGet.releaseConnection();
        }
    }

}
