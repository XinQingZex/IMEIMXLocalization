package com.agile.qdmp.standalong.tool.util.http;

import com.agile.qdmp.standalong.tool.bean.result.IMMediaUploadResult;
import com.agile.qdmp.standalong.tool.error.IMErrorException;
import com.agile.qdmp.standalong.tool.util.http.apache.ApacheMediaInputStreamUploadRequestExecutor;

import java.io.IOException;

/**
 * 上传媒体文件请求执行器.
 * 请求的参数是File, 返回的结果是String
 *
 * @Description:
 * @Author: wenbinglei
 * @Date: 2022/9/7 15:46
 */
public abstract class MediaInputStreamUploadRequestExecutor<H> implements RequestExecutor<IMMediaUploadResult, InputStreamData> {
    protected RequestHttp<H> requestHttp;

    public MediaInputStreamUploadRequestExecutor(RequestHttp requestHttp) {
        this.requestHttp = requestHttp;
    }

    @Override
    public void execute(String uri, String token, InputStreamData data, ResponseHandler<IMMediaUploadResult> handler) throws IMErrorException, IOException {
        handler.handle(this.execute(uri, token, data));
    }

    public static RequestExecutor<IMMediaUploadResult, InputStreamData> create(RequestHttp requestHttp) {
        return new ApacheMediaInputStreamUploadRequestExecutor(requestHttp);
    }

}
