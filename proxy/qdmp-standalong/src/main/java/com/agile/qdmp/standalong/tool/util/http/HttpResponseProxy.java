package com.agile.qdmp.standalong.tool.util.http;

import com.agile.qdmp.standalong.tool.error.IMErrorException;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.util.regex.Pattern;

/**
 * <pre>
 * 三种http框架的response代理类，方便提取公共方法
 * </pre>
 *
 * @Description:
 * @Author: wenbinglei
 * @Date: 2022/9/7 15:27
 */
public class HttpResponseProxy {
    private static final Pattern PATTERN = Pattern.compile(".*filename=\"(.*)\"");

    private CloseableHttpResponse apacheHttpResponse;

    public HttpResponseProxy(CloseableHttpResponse apacheHttpResponse) {
        this.apacheHttpResponse = apacheHttpResponse;
    }

    public String getFileName() throws IMErrorException {
        //由于对象只能由一个构造方法实现，因此三个response对象必定且只有一个不为空
        if (this.apacheHttpResponse != null) {
            return this.getFileName(this.apacheHttpResponse);
        }

        //cannot happen
        return null;
    }

    private String getFileName(CloseableHttpResponse response) throws IMErrorException {
        Header[] contentDispositionHeader = response.getHeaders("Content-Disposition");
        if (contentDispositionHeader == null || contentDispositionHeader.length == 0) {
            throw new IMErrorException("无法获取到文件名，Content-Disposition为空");
        }

        return this.extractFileNameFromContentString(contentDispositionHeader[0].getValue());
    }

    private String extractFileNameFromContentString(String content) throws IMErrorException {
        if (content == null || content.length() == 0) {
            throw new IMErrorException("无法获取到文件名，content为空");
        }
//        Matcher m = PATTERN.matcher(content.split(";")[1]);
//        if (m.matches()) {
//            return m.group(1);
//        }
//        throw new IMErrorException("无法获取到文件名，header信息有问题");

        return content.split("''")[1];

    }

}
