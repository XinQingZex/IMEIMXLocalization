package com.agile.qdmp.standalong.tool.util.http;

import com.agile.qdmp.standalong.tool.error.IMError;
import com.agile.qdmp.standalong.tool.error.IMErrorException;
import com.agile.qdmp.standalong.tool.util.http.apache.ApacheSimplePostRequestExecutor;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * 简单的POST请求执行器，请求的参数是String, 返回的结果也是String
 *
 * @Description:
 * @Author: wenbinglei
 * @Date: 2022/9/7 15:49
 */
public abstract class SimplePostRequestExecutor<H> implements RequestExecutor<String, String> {
    protected RequestHttp<H> requestHttp;

    public SimplePostRequestExecutor(RequestHttp requestHttp) {
        this.requestHttp = requestHttp;
    }

    @Override
    public void execute(String uri, String token, String data, ResponseHandler<String> handler)
            throws IMErrorException, IOException {
        handler.handle(this.execute(uri, token, data));
    }

    public static RequestExecutor<String, String> create(RequestHttp requestHttp) {
        return new ApacheSimplePostRequestExecutor(requestHttp);
    }

    @NotNull
    public String handleResponse(String responseContent) throws IMErrorException {
        if (responseContent.isEmpty()) {
            throw new IMErrorException("无响应内容");
        }

        if (responseContent.startsWith("<xml>")) {
            //xml格式输出直接返回
            return responseContent;
        }

        IMError error = IMError.fromJson(responseContent);
        if (error.getErrorCode() != 0) {
            throw new IMErrorException(error);
        }
        return responseContent;
    }
}
