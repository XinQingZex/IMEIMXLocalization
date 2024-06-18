package com.agile.qdmp.standalong.tool.util.http;

import com.agile.qdmp.standalong.service.integrator.IQcCosService;
import com.agile.qdmp.standalong.tool.error.IMErrorException;
import com.agile.qdmp.standalong.tool.util.http.apache.ApacheMediaDownloadRequestExecutor;
import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.IOException;

/**
 * 下载媒体文件请求执行器.
 * 请求的参数是String, 返回的结果是File
 * 视频文件不支持下载
 *
 * @Description:
 * @Author: wenbinglei
 * @Date: 2022/9/7 15:27
 */
public abstract class BaseMediaDownloadRequestExecutor<H> implements RequestExecutor<JSONObject, String> {
    protected RequestHttp<H> requestHttp;
    protected File tmpDirFile;

    public BaseMediaDownloadRequestExecutor(RequestHttp<H> requestHttp, File tmpDirFile) {
        this.requestHttp = requestHttp;
        this.tmpDirFile = tmpDirFile;
    }

    @Override
    public void execute(String uri, String token, String data, ResponseHandler<JSONObject> handler) throws IMErrorException, IOException {
        handler.handle(this.execute(uri, token, data));
    }

    public static RequestExecutor<JSONObject, String> create(RequestHttp requestHttp, File tmpDirFile) {
        return new ApacheMediaDownloadRequestExecutor(requestHttp, tmpDirFile);
    }

}
