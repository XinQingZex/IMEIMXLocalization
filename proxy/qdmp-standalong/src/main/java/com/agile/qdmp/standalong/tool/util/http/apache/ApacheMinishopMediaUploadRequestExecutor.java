package com.agile.qdmp.standalong.tool.util.http.apache;

import com.agile.qdmp.standalong.tool.bean.result.WxMinishopImageUploadResult;
import com.agile.qdmp.standalong.tool.error.IMError;
import com.agile.qdmp.standalong.tool.error.IMErrorException;
import com.agile.qdmp.standalong.tool.util.http.MinishopUploadRequestExecutor;
import com.agile.qdmp.standalong.tool.util.http.RequestHttp;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.File;
import java.io.IOException;

/**
 * @Description:
 * @Author: wenbinglei
 * @Date: 2022/9/7 16:38
 */
@Slf4j
public class ApacheMinishopMediaUploadRequestExecutor extends MinishopUploadRequestExecutor<CloseableHttpClient> {
  public ApacheMinishopMediaUploadRequestExecutor(RequestHttp requestHttp) {
    super(requestHttp);
  }

  @Override
  public WxMinishopImageUploadResult execute(String uri, String token, File file) throws IMErrorException, IOException {
    HttpPost httpPost = new HttpPost(uri);
    if (file != null) {
      HttpEntity entity = MultipartEntityBuilder
        .create()
        .addBinaryBody("media", file)
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
      log.info("responseContent: " + responseContent);
      return WxMinishopImageUploadResult.fromJson(responseContent);
    } finally {
      httpPost.releaseConnection();
    }
  }
}
