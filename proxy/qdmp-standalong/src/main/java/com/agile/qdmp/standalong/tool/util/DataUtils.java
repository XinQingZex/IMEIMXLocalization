package com.agile.qdmp.standalong.tool.util;

import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 数据处理工具类
 *
 * @Description:
 * @Author: wenbinglei
 * @Date: 2022/9/7 17:59
 */
public class DataUtils {
    /**
     * 将数据中包含的secret字符使用星号替换，防止日志打印时被输出
     */
    public static <E> E handleDataWithSecret(E data) {
        E dataForLog = data;
        if (data instanceof String && StringUtils.contains((String) data, "&secret=")) {
            dataForLog = (E) RegExUtils.replaceAll((String) data, "&secret=\\w+&", "&secret=******&");
        }
        return dataForLog;
    }
}
