package com.agile.qdmp.standalong.service.integrator;

import java.io.InputStream;

/**
 * @Author: wenbinglei
 * @Date: 2022/5/31 18:19
 * @Description:
 */
public interface IQcCosService {

    /**
     * 上传
     * @param fileName
     * @param inputStream
     */
    String upload(String fileName, InputStream inputStream, String contentType, long size);

    /**
     * 删除
     * @param fileName
     */
    void remove(String fileName);

}
