package com.agile.qdmp.standalong.tool.api.impl;

import com.agile.qdmp.standalong.config.SysConfig;
import com.agile.qdmp.standalong.model.entity.integrator.Drawing;
import com.agile.qdmp.standalong.service.integrator.IQcCosService;
import com.agile.qdmp.standalong.tool.api.IMService;
import com.agile.qdmp.standalong.tool.error.IMError;
import com.agile.qdmp.standalong.tool.error.IMErrorException;
import com.agile.qdmp.standalong.tool.error.IMRuntimeException;
import com.agile.qdmp.standalong.tool.util.DataUtils;
import com.agile.qdmp.standalong.tool.util.http.*;
import com.agile.qdmp.standalong.tool.util.json.IMGsonBuilder;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * 基础实现类.
 * @Description:
 * @Author: wenbinglei
 * @Date: 2022/9/9 16:31
 */
@Slf4j
public abstract class BaseIMServiceImpl<H> implements IMService, RequestHttp<H> {
    private int retrySleepMillis = 1000;
    private int maxRetryTimes = 5;
    @Autowired
    private SysConfig sysConfig;
    @Override
    public String get(String url, String queryParam) throws IMErrorException {
        return execute(SimpleGetRequestExecutor.create(this), url, null, queryParam);
    }

    @Override
    public String post(String url, String token, Object obj) throws IMErrorException {
        return this.execute(SimplePostRequestExecutor.create(this), url, token, IMGsonBuilder.create().toJson(obj));
    }

//    @Override
//    public byte[] download(String url, String token, Object obj) throws IMErrorException {
//        Path filePath = null;
//        try {
//            RequestExecutor<File, String> executor = BaseMediaDownloadRequestExecutor.create(this, Files.createTempDirectory("test-tmp-im-").toFile(), qcCosService);
//            File file = this.execute(executor, url, token, IMGsonBuilder.create().toJson(obj));
//            filePath = file.toPath();
//            return Files.readAllBytes(filePath);
//        } catch (IOException e) {
//            throw new IMErrorException(IMError.builder().errorMsg(e.getMessage()).build(), e);
//        } finally {
//            if (filePath != null) {
//                try {
//                    // 及时删除文件，避免积压过多缓存文件
//                    Files.delete(filePath);
//                } catch (Exception ignored) {
//                }
//            }
//        }
//    }

    @Override
    public Drawing downloadImage(String url, String token, JSONObject params, Drawing drawing) throws IMErrorException {
        try {
//            Path filePath = Paths.get(sysConfig.getFileDirectory() + drawing.getGuid());
            Path filePath = Paths.get(sysConfig.getFileDirectory());
            if(Files.notExists(filePath)) {
                Files.createDirectory(filePath);
            }
            Path drawingPath = Paths.get(sysConfig.getFileDirectory() + drawing.getDrawingFile());

//            File dir = new File(drawingPath.toString());
//            File[] files = dir.listFiles();
//            if(Objects.nonNull(files) && files.length > 0) {
//                for (File file : files) {
//                    file.delete();
//                }
//            }
            File drawingFile;
            if(Files.notExists(drawingPath)) {
                drawingFile = Files.createDirectory(drawingPath).toFile();
            } else {
                drawingFile = new File(drawingPath.toString());
            }
//            Files.deleteIfExists(drawingPath);

            RequestExecutor<JSONObject, String> executor = BaseMediaDownloadRequestExecutor.create(this, drawingFile);
            JSONObject fileRes = this.execute(executor, url, token, IMGsonBuilder.create().toJson(params));
            drawing.setPdfUrl(fileRes.getString("pdfUrl"));
            drawing.setImageUrl(fileRes.getString("imageUrl"));
        } catch (IOException e) {
//            e.printStackTrace();
            throw new IMErrorException(IMError.builder().errorMsg(e.getMessage()).build(), e);
        } finally {
//            if (filePath != null) {
//                try {
//                    // 及时删除文件，避免积压过多缓存文件
//                    Files.delete(filePath);
//                } catch (Exception ignored) {
//                }
//            }
        }
        return drawing;
    }

    /**
     * 向IM发送请求
     */
    private <T, E> T execute(RequestExecutor<T, E> executor, String uri, String token, E data) throws IMErrorException {
        int retryTimes = 0;
        do {
            try {
                return this.executeInternal(executor, uri, token, data);
            } catch (IMErrorException e) {
                IMError error = e.getError();
                // -1 系统繁忙, 1000ms后重试
                if (error.getErrorCode() == -1) {
                    // 判断是否已经超了最大重试次数
                    if (retryTimes + 1 > this.maxRetryTimes) {
                        log.warn("重试达到最大次数【{}】", maxRetryTimes);
                        //最后一次重试失败后，直接抛出异常，不再等待
                        throw new IMRuntimeException("IM服务端异常，超出重试次数");
                    }

                    int sleepMillis = this.retrySleepMillis * (1 << retryTimes);
                    try {
                        log.warn("IM系统繁忙，{} ms 后重试(第{}次)", sleepMillis, retryTimes + 1);
                        Thread.sleep(sleepMillis);
                    } catch (InterruptedException e1) {
                        throw new IMRuntimeException(e1);
                    }
                } else {
                    throw e;
                }
            }
        } while (retryTimes++ < this.maxRetryTimes);

        log.warn("重试达到最大次数【{}】", this.maxRetryTimes);
        throw new IMRuntimeException("IM服务端异常，超出重试次数");
    }

    protected <T, E> T executeInternal(RequestExecutor<T, E> executor, String uri, String token, E data) throws IMErrorException {
        E dataForLog = DataUtils.handleDataWithSecret(data);

        try {
            T result = executor.execute(uri, token, data);
            log.debug("\n【请求地址】: {}\n【请求参数】：{}\n【响应数据】：{}", uri, dataForLog, result);
//            log.debug("\n【请求地址】: {}\n【请求参数】：{}", uri, dataForLog);
            return result;
        } catch (IMErrorException e) {
            IMError error = e.getError();
            if (error.getErrorCode() != 0) {
                log.warn("\n【请求地址】: {}\n【请求参数】：{}\n【错误信息】：{}", uri, dataForLog, error);
                throw new IMErrorException(error, e);
            }
            return null;
        } catch (IOException e) {
            log.warn("\n【请求地址】: {}\n【请求参数】：{}\n【异常信息】：{}", uri, dataForLog, e.getMessage());
            throw new IMErrorException(e);
        }
    }

    @Override
    public void setRetrySleepMillis(int retrySleepMillis) {
        this.retrySleepMillis = retrySleepMillis;
    }

    @Override
    public void setMaxRetryTimes(int maxRetryTimes) {
        this.maxRetryTimes = maxRetryTimes;
    }

}
