package com.agile.qdmp.standalong.tool.util.http.apache;

import com.agile.qdmp.standalong.tool.bean.result.IMMediaUploadResult;
import com.agile.qdmp.standalong.tool.error.IMError;
import com.agile.qdmp.standalong.tool.error.IMErrorException;
import com.agile.qdmp.standalong.tool.util.http.InputStreamData;
import com.agile.qdmp.standalong.tool.util.http.MediaInputStreamUploadRequestExecutor;
import com.agile.qdmp.standalong.tool.util.http.RequestHttp;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;

/**
 * 文件输入流上传.
 *
 * @Description:
 * @Author: wenbinglei
 * @Date: 2022/9/7 16:35
 */
public class ApacheMediaInputStreamUploadRequestExecutor extends MediaInputStreamUploadRequestExecutor<CloseableHttpClient> {
    public ApacheMediaInputStreamUploadRequestExecutor(RequestHttp requestHttp) {
        super(requestHttp);
    }

    @Override
    public IMMediaUploadResult execute(String uri, String token, InputStreamData data) throws IMErrorException, IOException {
        HttpPost httpPost = new HttpPost(uri);
        if (data != null) {
            HttpEntity entity = MultipartEntityBuilder
                    .create()
                    .addBinaryBody("media", data.getInputStream(), ContentType.DEFAULT_BINARY, data.getFilename())
                    .setMode(HttpMultipartMode.RFC6532)
                    .build();
            httpPost.setEntity(entity);
        }
        try (CloseableHttpResponse response = requestHttp.getRequestHttpClient().execute(httpPost)) {
            String responseContent = Utf8ResponseHandler.INSTANCE.handleResponse(response);
            IMError error = IMError.fromJson(responseContent);
            if (error.getErrorCode() != 0) {
                throw new IMErrorException(error);
            }
            return IMMediaUploadResult.fromJson(responseContent);
        } finally {
            httpPost.releaseConnection();
        }
    }
}
