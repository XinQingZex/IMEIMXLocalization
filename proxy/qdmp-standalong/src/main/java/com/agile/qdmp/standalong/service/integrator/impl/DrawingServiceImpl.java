package com.agile.qdmp.standalong.service.integrator.impl;

import com.agile.qdmp.standalong.config.SysConfig;
import com.agile.qdmp.standalong.mapper.integrator.DrawingMapper;
import com.agile.qdmp.standalong.model.entity.integrator.Dimension;
import com.agile.qdmp.standalong.model.entity.integrator.Drawing;
import com.agile.qdmp.standalong.model.enums.CommonConstants;
import com.agile.qdmp.standalong.service.integrator.IDimensionService;
import com.agile.qdmp.standalong.service.integrator.IDrawingService;
import com.agile.qdmp.standalong.tool.api.IMService;
import com.agile.qdmp.standalong.tool.error.IMErrorException;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.util.Matrix;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * DRAWING 服务类
 *
 * @author wenbinglei
 * @date 2022-10-22 15:03:30
 */
@Service
@Slf4j
public class DrawingServiceImpl extends ServiceImpl<DrawingMapper, Drawing> implements IDrawingService {
    @Resource
    private IMService imService;
    @Resource
    private IDimensionService dimensionService;
    @Resource
    private SysConfig sysConfig;


    @Override
    public Boolean completeDrawingList(List<Drawing> drawings, String serverUri, String accessToken) {
        if (drawings == null || drawings.size() == 0) {
            return false;
        }

        Map<String, Drawing> dataList = new HashMap<>(10);
        for(Drawing drawing : drawings) {
            dataList.put(drawing.getFlag(), drawing);
        }
        List<Drawing> exists = this.lambdaQuery().in(Drawing::getFlag, dataList.keySet()).select(Drawing::getFlag).list();
        if(exists.size() == dataList.size()) {
            return true;
        }
        for(Drawing drawing : exists) {
            dataList.remove(drawing.getFlag());
        }

        for(Drawing drawing : dataList.values()) {
            completeDrawingData(drawing, serverUri, accessToken);
        }

        return true;
    }

    @Override
    public Drawing completeDrawingData(Drawing drawing, String serverUri, String accessToken) {
        if(StringUtils.isNotBlank(drawing.getNotes())) {
            List<String> sizes = parseImageSize(drawing.getNotes());
            if(sizes.size() == 4) {
                drawing.setImageWidth(Integer.parseInt(sizes.get(0)));
                drawing.setImageHeight(Integer.parseInt(sizes.get(1)));
                drawing.setDrawingWidth(Integer.parseInt(sizes.get(2)));
                drawing.setDrawingHeight(Integer.parseInt(sizes.get(3)));
            }
        }

        drawing.setImageUrl(null);
        drawing.setPdfUrl(null);

        Drawing currentDrawing = this.lambdaQuery().eq(Drawing::getGuid, drawing.getGuid()).one();
        Boolean needDownFile;
        if(currentDrawing != null) {
            drawing.setId(currentDrawing.getId());
            this.updateById(drawing);
            if(!currentDrawing.getNotes().equalsIgnoreCase(drawing.getNotes()) || StringUtils.isBlank(currentDrawing.getImageUrl()) || StringUtils.isBlank(currentDrawing.getPdfUrl())) {
                needDownFile = true;
            } else {
                return currentDrawing;
            }
        } else {
            this.save(drawing);
            needDownFile = true;
        }

        if(needDownFile) {
            JSONObject tokenParamsData = new JSONObject();
            tokenParamsData.put("GUID", drawing.getDrawingFile());
            try {
                String tokenResult = imService.post(serverUri + "/api/filestorage/token", accessToken, tokenParamsData);
                JSONObject tokenJSON = JSONObject.parseObject(tokenResult);
                String token = tokenJSON.getString("Token");

                if(StringUtils.isNotBlank(token)) {
                    drawing = imService.downloadImage(serverUri + "/api/filestorage/download", accessToken, tokenJSON, drawing);
                    if(StringUtils.isNotBlank(drawing.getImageUrl())) {
                        lambdaUpdate().set(Drawing::getImageUrl, drawing.getImageUrl()).set(Drawing::getPdfUrl, drawing.getPdfUrl()).eq(Drawing::getDrawingFile, drawing.getDrawingFile()).update();
                    }
//                    this.updateById(drawing);
                }
            } catch (IMErrorException e) {
                log.error(e.getMessage());
                return null;
            }
        }
        return drawing;
    }

    @Override
    public void buildImage() {
        System.setProperty("sun.java2d.cmm", "sun.java2d.cmm.kcms.KcmsServiceProvider");
        System.setProperty("java.awt.headless", "true");
        System.setProperty("sun.java2d.xrender", "false");
        List<Drawing> drawingList = lambdaQuery().eq(Drawing::getImageDimUrl, CommonConstants.EMPTY_STR).ne(Drawing::getImageUrl, CommonConstants.EMPTY_STR).select(Drawing::getPdfUrl, Drawing::getId, Drawing::getImageUrl, Drawing::getImageDimUrl, Drawing::getDrawingFile, Drawing::getGuid, Drawing::getImageWidth, Drawing::getImageHeight).list();
        if(Objects.nonNull(drawingList) && drawingList.size() > 0) {
            for(Drawing drawing: drawingList) {
                List<Dimension> dims = dimensionService.lambdaQuery().eq(Dimension::getDrawingGuid, drawing.getGuid()).list();
                String imagePath = createImage(drawing, dims);
                if(StringUtils.isNotBlank(imagePath)) {
                    drawing.setImageDimUrl(imagePath);
                    updateById(drawing);
                }
            }
        }
    }

    @Override
    public List<JSONObject> fetchFileList(HashMap<String, String> params) {
        List<JSONObject> res = new ArrayList<>();
        if(!params.containsKey(CommonConstants.TYPE) || !params.containsKey(CommonConstants.ID)) {
            return res;
        }
        String type = params.get(CommonConstants.TYPE);
        String id = params.get(CommonConstants.ID);
        switch (type) {
            case CommonConstants.PART_STR:
                res = baseMapper.fetchPartFiles(id);
                break;
            case CommonConstants.DIM_STR:
                res = baseMapper.fetchDimFiles(id);
                break;
            default:
        }
        return res;
    }

    @Override
    public JSONObject fetchDimFileList(Long id) {
        return baseMapper.fetchDimFileList(id);
    }


    /**
     * 过滤notes中的图纸尺寸
     * @param content
     * @return
     */
    private List<String> parseImageSize(String content){
        // 编写正则表达式
        String regFileName = "(\\d+)\\sx\\s(\\d+)\\spx";
        // 匹配当前正则表达式
        Matcher matcher = Pattern.compile(regFileName).matcher(content);
        List<String> sizes = new ArrayList<String>();
        while(matcher.find()) {
            sizes.add(matcher.group(1));
            sizes.add(matcher.group(2));
        }
        return sizes;
    }

    /**
     * 创建图片
     * @param drawing
     * @param dims
     * @return
     */
    private String createImage(Drawing drawing, List<Dimension> dims) {
        String res = null;
        BufferedImage expectedImage = null;
        Path drawingPath = Paths.get(sysConfig.getFileDirectory() + drawing.getDrawingFile());

        File file = new File(drawingPath + File.separator + drawing.getPdfUrl());
        try(PDDocument doc = Loader.loadPDF(file)) {
            PDPage page = doc.getPage(0);
            float width = page.getMediaBox().getWidth();
            Float height = page.getMediaBox().getHeight();
            int rotation = page.getRotation();
            float ratio = 1f;

            try (PDPageContentStream contentStream = new PDPageContentStream(doc, page, PDPageContentStream.AppendMode.APPEND, false))
            {
                switch (rotation)
                {
                    case 90:
                        ratio = height / drawing.getImageWidth();
                        contentStream.transform(Matrix.getRotateInstance(Math.toRadians(90), 0, 0));
                        for(Dimension dim : dims) {
                            String[] shapeCenter = dim.getShapeCenter().split(",");
                            Float pX = Integer.parseInt(shapeCenter[0].trim()) * ratio;
                            Float pY = Integer.parseInt(shapeCenter[1].trim()) * ratio;
                            drawCircle(contentStream, pX.intValue(), pY.intValue() * -1 - 10, 10, dim.getShapeTitle());
                        }
                        break;
//                case 180:
//                    contentStream.transform(Matrix.getRotateInstance(Math.toRadians(180), width, height));
//                    break;
//                case 270:
//                    width = page.getMediaBox().getHeight();
//                    height = page.getMediaBox().getWidth();
//                    contentStream.transform(Matrix.getRotateInstance(Math.toRadians(270), 0, width));
//                    break;
                    default:
                        ratio = width / drawing.getImageWidth();

                        for(Dimension dim : dims) {
                            if(StringUtils.isBlank(dim.getShapeCenter())) {continue;}
                            String[] shapeCenter = dim.getShapeCenter().split(",");
                            Float pX = Integer.parseInt(shapeCenter[0].trim()) * ratio;
                            Float pY = Integer.parseInt(shapeCenter[1].trim()) * ratio;
                            drawCircle(contentStream, pX.intValue(), pY.intValue() * -1 - 10, 10, dim.getShapeTitle());
                            drawCircle(contentStream, pX.intValue(), height.intValue() - pY.intValue() - 10, 10, dim.getShapeTitle());
                        }
                        break;
                }

            }
            expectedImage = new PDFRenderer(doc).renderImage(0, 1f);
            res = Long.toString(System.currentTimeMillis()) + ".png";
            ImageIO.write(expectedImage, "png", new File(drawingPath + File.separator + res));
        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            if (expectedImage != null) {
                expectedImage.getGraphics().dispose();
            }
        }
        return res;
    }

    static final PDFont font = PDType1Font.HELVETICA;
    private static void drawCircle(PDPageContentStream contentStream, int cx, int cy, int r, String number) throws IOException {
        final float k = 0.552284749831f;
        contentStream.setNonStrokingColor(Color.BLUE);
        contentStream.moveTo(cx - r, cy);
        contentStream.curveTo(cx - r, cy + k * r, cx - k * r, cy + r, cx, cy + r);
        contentStream.curveTo(cx + k * r, cy + r, cx + r, cy + k * r, cx + r, cy);
        contentStream.curveTo(cx + r, cy - k * r, cx + k * r, cy - r, cx, cy - r);
        contentStream.curveTo(cx - k * r, cy - r, cx - r, cy - k * r, cx - r, cy);
        contentStream.fill();

        contentStream.setNonStrokingColor(Color.WHITE);
        contentStream.beginText();
        contentStream.setFont(font, 12);
        contentStream.newLineAtOffset(cx - 5 , cy - 5);
        contentStream.showText(number);
        contentStream.endText();
    }
}
