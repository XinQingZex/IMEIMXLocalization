package com.agile.qdmp.standalong.tool.util.http.apache;

import com.agile.qdmp.standalong.tool.error.IMErrorException;
import com.agile.qdmp.standalong.tool.util.http.RequestHttp;
import com.agile.qdmp.standalong.tool.util.http.SimplePostRequestExecutor;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;

/**
 * @Description:
 * @Author: wenbinglei
 * @Date: 2022/9/7 16:40
 */
public class ApacheSimplePostRequestExecutor extends SimplePostRequestExecutor<CloseableHttpClient> {
    public ApacheSimplePostRequestExecutor(RequestHttp requestHttp) {
        super(requestHttp);
    }

    @Override
    public String execute(String uri, String token, String postEntity) throws IMErrorException, IOException {
        HttpPost httpPost = new HttpPost(uri);
        if(StringUtils.isNotBlank(token)) {
            httpPost.setHeader("Authorization", "Bearer " + token);
        }
        if (postEntity != null) {
            StringEntity entity = new StringEntity(postEntity, Consts.UTF_8);
            entity.setContentType("application/json; charset=utf-8");
            httpPost.setEntity(entity);
        }

        try (CloseableHttpResponse response = requestHttp.getRequestHttpClient().execute(httpPost)) {
            String responseContent = Utf8ResponseHandler.INSTANCE.handleResponse(response);
            return this.handleResponse(responseContent);
        } finally {
            httpPost.releaseConnection();
        }
    }

}
