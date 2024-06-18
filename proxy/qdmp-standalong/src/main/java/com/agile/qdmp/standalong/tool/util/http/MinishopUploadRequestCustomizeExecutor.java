package com.agile.qdmp.standalong.tool.util.http;

import com.agile.qdmp.standalong.tool.bean.result.WxMinishopImageUploadCustomizeResult;
import com.agile.qdmp.standalong.tool.error.IMErrorException;
import com.agile.qdmp.standalong.tool.util.http.apache.ApacheMinishopMediaUploadRequestCustomizeExecutor;

import java.io.File;
import java.io.IOException;

public abstract class MinishopUploadRequestCustomizeExecutor<H> implements RequestExecutor<WxMinishopImageUploadCustomizeResult, File> {
    protected RequestHttp<H> requestHttp;
    protected String respType;
    protected String uploadType;
    protected String imgUrl;

    public MinishopUploadRequestCustomizeExecutor(RequestHttp requestHttp, String respType, String imgUrl) {
        this.requestHttp = requestHttp;
        this.respType = respType;
        if (imgUrl == null || imgUrl.isEmpty()) {
            this.uploadType = "0";
        } else {
            this.uploadType = "1";
            this.imgUrl = imgUrl;
        }
    }

    @Override
    public void execute(String uri, String token, File data, ResponseHandler<WxMinishopImageUploadCustomizeResult> handler) throws IMErrorException, IOException {
        handler.handle(this.execute(uri, token, data));
    }

    public static RequestExecutor<WxMinishopImageUploadCustomizeResult, File> create(RequestHttp requestHttp, String respType, String imgUrl) {
        return new ApacheMinishopMediaUploadRequestCustomizeExecutor(requestHttp, respType, imgUrl);
    }
}
