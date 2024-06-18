package com.agile.qdmp.standalong.tool.util.http;

import com.agile.qdmp.standalong.tool.bean.result.IMMediaUploadResult;
import com.agile.qdmp.standalong.tool.error.IMErrorException;
import com.agile.qdmp.standalong.tool.util.http.apache.ApacheMediaUploadRequestExecutor;

import java.io.File;
import java.io.IOException;

/**
 * 上传媒体文件请求执行器.
 * 请求的参数是File, 返回的结果是String
 *
 * @Description:
 * @Author: wenbinglei
 * @Date: 2022/9/7 15:46
 */
public abstract class MediaUploadRequestExecutor<H> implements RequestExecutor<IMMediaUploadResult, File> {
    protected RequestHttp<H> requestHttp;

    public MediaUploadRequestExecutor(RequestHttp requestHttp) {
        this.requestHttp = requestHttp;
    }

    @Override
    public void execute(String uri, String token, File data, ResponseHandler<IMMediaUploadResult> handler) throws IMErrorException, IOException {
        handler.handle(this.execute(uri, token, data));
    }

    public static RequestExecutor<IMMediaUploadResult, File> create(RequestHttp requestHttp) {
        return new ApacheMediaUploadRequestExecutor(requestHttp);
    }

}
