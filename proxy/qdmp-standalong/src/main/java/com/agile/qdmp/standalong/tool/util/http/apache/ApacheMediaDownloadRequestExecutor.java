package com.agile.qdmp.standalong.tool.util.http.apache;

import com.agile.qdmp.standalong.tool.error.IMError;
import com.agile.qdmp.standalong.tool.error.IMErrorException;
import com.agile.qdmp.standalong.tool.util.http.BaseMediaDownloadRequestExecutor;
import com.agile.qdmp.standalong.tool.util.http.RequestHttp;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import static org.apache.commons.io.FileUtils.openOutputStream;

/**
 * @Description:
 * @Author: wenbinglei
 * @Date: 2022/9/7 16:33
 */
public class ApacheMediaDownloadRequestExecutor extends BaseMediaDownloadRequestExecutor<CloseableHttpClient> {
    public ApacheMediaDownloadRequestExecutor(RequestHttp requestHttp, File tmpDirFile) {
        super(requestHttp, tmpDirFile);
    }

//    @Override
//    public File execute(String uri, String token, String queryParam) throws IMErrorException, IOException {
//        if (queryParam != null) {
//            if (uri.indexOf('?') == -1) {
//                uri += '?';
//            }
//            uri += uri.endsWith("?") ? queryParam : '&' + queryParam;
//        }
//
//        HttpGet httpGet = new HttpGet(uri);
//        try (CloseableHttpResponse response = requestHttp.getRequestHttpClient().execute(httpGet);
//             InputStream inputStream = InputStreamResponseHandler.INSTANCE.handleResponse(response)) {
//            Header[] contentTypeHeader = response.getHeaders("Content-Type");
//            if (contentTypeHeader != null && contentTypeHeader.length > 0) {
//                if (contentTypeHeader[0].getValue().startsWith(ContentType.APPLICATION_JSON.getMimeType())) {
//                    // application/json; encoding=utf-8 下载媒体文件出错
//                    String responseContent = Utf8ResponseHandler.INSTANCE.handleResponse(response);
//                    throw new IMErrorException(IMError.fromJson(responseContent));
//                }
//            }
//
//            String fileName = new HttpResponseProxy(response).getFileName();
//            if (StringUtils.isBlank(fileName)) {
//                fileName = String.valueOf(System.currentTimeMillis());
//            }
//
//            String baseName = FilenameUtils.getBaseName(fileName);
//            if (StringUtils.isBlank(fileName) || baseName.length() < 3) {
//                baseName = String.valueOf(System.currentTimeMillis());
//            }
//
//            return FileUtils.createTmpFile(inputStream, baseName, FilenameUtils.getExtension(fileName), super.tmpDirFile);
//
//        } finally {
//            httpGet.releaseConnection();
//        }
//    }

    @Override
    public JSONObject execute(String uri, String token, String postEntity) throws IMErrorException, IOException {
        System.setProperty("sun.java2d.cmm", "sun.java2d.cmm.kcms.KcmsServiceProvider");
        System.setProperty("java.awt.headless", "true");
        System.setProperty("sun.java2d.xrender", "false");
        JSONObject res = new JSONObject();

        HttpPost httpPost = new HttpPost(uri);
        if (StringUtils.isNotBlank(token)) {
            httpPost.setHeader("Authorization", "Bearer " + token);
        }
        if (postEntity != null) {
            StringEntity entity = new StringEntity(postEntity, Consts.UTF_8);
            entity.setContentType("application/json; charset=utf-8");
            httpPost.setEntity(entity);
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        InputStream pdfInputStream = null;
        InputStream imageInputStream = null;

        PDDocument doc = null;
        PDFRenderer renderer = null;

        BufferedImage imageBuf = null;
        try (CloseableHttpResponse response = requestHttp.getRequestHttpClient().execute(httpPost);
             InputStream inputStream = InputStreamResponseHandler.INSTANCE.handleResponse(response)) {
//            response.getAllHeaders();
//            final Header[] headers = response.getAllHeaders();
//            for (final Header header : headers) {
//                System.out.println("<< " + header.toString());
//                for(final HeaderElement element: header.getElements()) {
//                    System.out.println(" \t << " + element.getName() + element.getValue());
//                }
//            }

            Header[] contentTypeHeader = response.getHeaders("Content-Type");
            if (contentTypeHeader != null && contentTypeHeader.length > 0) {
                if (contentTypeHeader[0].getValue().startsWith(ContentType.APPLICATION_JSON.getMimeType())) {
                    // application/json; encoding=utf-8 下载媒体文件出错
                    String responseContent = Utf8ResponseHandler.INSTANCE.handleResponse(response);
                    throw new IMErrorException(IMError.fromJson(responseContent));
                }
            }

            String fileName = Long.toString(System.currentTimeMillis());
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) > -1) {
                outputStream.write(buffer, 0, len);
            }
            outputStream.flush();
            pdfInputStream = new ByteArrayInputStream(outputStream.toByteArray());
            imageInputStream = new ByteArrayInputStream(outputStream.toByteArray());

            File pdfFile = new File(super.tmpDirFile.getPath() + File.separator +fileName + ".pdf");
            try (InputStream in = pdfInputStream; OutputStream out = openOutputStream(pdfFile)) {
                IOUtils.copy(in, out);
                res.put("pdfUrl", pdfFile.getName());
            }

            doc = Loader.loadPDF(imageInputStream);
            renderer = new PDFRenderer(doc);

            imageBuf = renderer.renderImage(0, 1f);
            // BufferedImage image = renderer.renderImageWithDPI(0, 296);
            ImageIO.write(imageBuf, "png", new File(super.tmpDirFile.getPath() + File.separator +fileName + ".png"));
            res.put("imageUrl", fileName + ".png");
        } finally {
            renderer = null;
            outputStream.close();

            if(imageBuf != null) {
                imageBuf.getGraphics().dispose();
            }
            if(doc != null) {
                doc.close();
            }
            if(pdfInputStream != null) {
                pdfInputStream.close();
            }
            if(imageInputStream != null) {
                imageInputStream.close();
            }
            httpPost.releaseConnection();
        }
        return res;
    }
}
