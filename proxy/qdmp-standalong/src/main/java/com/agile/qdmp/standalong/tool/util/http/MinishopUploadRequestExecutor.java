package com.agile.qdmp.standalong.tool.util.http;

import com.agile.qdmp.standalong.tool.bean.result.WxMinishopImageUploadResult;
import com.agile.qdmp.standalong.tool.error.IMErrorException;
import com.agile.qdmp.standalong.tool.util.http.apache.ApacheMinishopMediaUploadRequestExecutor;

import java.io.File;
import java.io.IOException;

public abstract class MinishopUploadRequestExecutor<H> implements RequestExecutor<WxMinishopImageUploadResult, File> {
    protected RequestHttp<H> requestHttp;

    public MinishopUploadRequestExecutor(RequestHttp requestHttp) {
        this.requestHttp = requestHttp;
    }

    @Override
    public void execute(String uri, String token, File data, ResponseHandler<WxMinishopImageUploadResult> handler) throws IMErrorException, IOException {
        handler.handle(this.execute(uri, token, data));
    }

    public static RequestExecutor<WxMinishopImageUploadResult, File> create(RequestHttp requestHttp) {
        return new ApacheMinishopMediaUploadRequestExecutor(requestHttp);
    }
}
