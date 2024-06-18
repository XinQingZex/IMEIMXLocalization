package com.agile.qdmp.standalong.tool.util;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.util.Matrix;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Test {
    static final String DEFAULT_FILENAME = "D:\\111\\test\\pdf\\1675387368634.pdf"; // 源PDF
//    static final String DEFAULT_FILENAME = "D:\\111\\test\\pdf\\1670038865171.pdf"; // 源PDF 90
    static final String DEFAULT_FILENAME_TEMP = "D:\\111\\test\\pdf\\copy.pdf"; // 生成新的PDF
    static final String IMAGE_DIR = "D:\\111\\test\\pdf"; // 生成新的PDF
    static final PDFont font = PDType1Font.HELVETICA;

    /**
     * PDF添加图片水印
     * */
    public static void main(String[] args) throws IOException {
        BufferedImage expectedImage;

        File file = new File(DEFAULT_FILENAME);
        PDDocument doc = Loader.loadPDF(file);

        PDPage page = doc.getPage(0);
        System.out.println("page.getRotation():" + page.getRotation());
        float width = page.getMediaBox().getWidth();
        Float height = page.getMediaBox().getHeight();
        int rotation = page.getRotation();
//        int rotation = 0;

        float ratio = 0.75f;

        try (PDPageContentStream contentStream = new PDPageContentStream(doc, page, PDPageContentStream.AppendMode.APPEND, false))
        {
            switch (rotation)
            {
                case 90:
                    contentStream.transform(Matrix.getRotateInstance(Math.toRadians(90), 0, 0));
                    for(int i = 0; i < points.size(); i++) {
                        JSONObject p = points.getJSONObject(i);
                        String[] shapeCenter = p.getString("shapeCenter").split(",");
                        List<Integer> cs = new ArrayList<>();
                        cs.add((int) (Integer.parseInt(shapeCenter[0]) * ratio));
                        cs.add((int) (Integer.parseInt(shapeCenter[1]) * ratio));
                        drawCircle(contentStream, cs.get(0), cs.get(1) * -1 - 10, 10, p.getString("shapeTitle"));
                    }
                    break;
                case 180:
//                    contentStream.transform(Matrix.getRotateInstance(Math.toRadians(180), height, width));
////                    contentStream.transform(Matrix.getRotateInstance(Math.toRadians(180), 0, 0));
//                    for(int i = 0; i < points.size(); i++) {
//                        JSONObject p = points.getJSONObject(i);
//                        String[] shapeCenter = p.getString("shapeCenter").split(",");
//                        List<Integer> cs = new ArrayList<>();
//                        cs.add((int) (Integer.parseInt(shapeCenter[0]) * ratio));
//                        cs.add((int) (Integer.parseInt(shapeCenter[1]) * ratio));
////                        drawCircle(contentStream, cs.get(0), cs.get(1) * -1 - 10, 10, p.getString("shapeTitle"));
////                        drawCircle(contentStream, cs.get(0) * -1, cs.get(1) * -1, 10, p.getString("shapeTitle"));
//                        drawCircle(contentStream, cs.get(0), cs.get(1), 10, p.getString("shapeTitle"));
//                    }
//                    break;
//                case 270:
//                    width = page.getMediaBox().getHeight();
//                    height = page.getMediaBox().getWidth();
//                    contentStream.transform(Matrix.getRotateInstance(Math.toRadians(270), 0, width));
                    break;
                default:
                    for(int i = 0; i < points.size(); i++) {
                        JSONObject p = points.getJSONObject(i);
                        String[] shapeCenter = p.getString("shapeCenter").split(",");
                        List<Integer> cs = new ArrayList<>();
                        cs.add((int) (Integer.parseInt(shapeCenter[0].trim()) * ratio));
                        cs.add((int) (Integer.parseInt(shapeCenter[1].trim()) * ratio));
                        System.out.println(cs);
                        drawCircle(contentStream, cs.get(0), height.intValue() - cs.get(1) - 10, 10, p.getString("shapeTitle"));
                    }
                    break;
            }

        }
//        doc.save(DEFAULT_FILENAME_TEMP);
        expectedImage = new PDFRenderer(doc).renderImage(0, 2);
        ImageIO.write(expectedImage, "png", new File(IMAGE_DIR, "test.png"));
        doc.close();
    }


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


    static final JSONArray points = JSONArray.parseArray("[{\"shapeCenter\":\"411,405\",\"shapePoints\":\"58,22\",\"shapeTitle\":\"7\"},{\"shapeCenter\":\"404,546\",\"shapePoints\":\"60,21\",\"shapeTitle\":\"16\"},{\"shapeCenter\":\"554,477\",\"shapePoints\":\"37,22\",\"shapeTitle\":\"10\"},{\"shapeCenter\":\"650,521\",\"shapePoints\":\"48,22\",\"shapeTitle\":\"15\"},{\"shapeCenter\":\"652,194\",\"shapePoints\":\"22,27\",\"shapeTitle\":\"5\"},{\"shapeCenter\":\"459,440\",\"shapePoints\":\"57,22\",\"shapeTitle\":\"8\"},{\"shapeCenter\":\"510,627\",\"shapePoints\":\"48,22\",\"shapeTitle\":\"18\"},{\"shapeCenter\":\"305,180\",\"shapePoints\":\"20,22\",\"shapeTitle\":\"3\"},{\"shapeCenter\":\"562,645\",\"shapePoints\":\"48,21\",\"shapeTitle\":\"19\"},{\"shapeCenter\":\"376,517\",\"shapePoints\":\"29,22\",\"shapeTitle\":\"12\"},{\"shapeCenter\":\"247,146\",\"shapePoints\":\"28,22\",\"shapeTitle\":\"2\"},{\"shapeCenter\":\"181,259\",\"shapePoints\":\"22,28\",\"shapeTitle\":\"6\"},{\"shapeCenter\":\"540,173\",\"shapePoints\":\"116,70\",\"shapeTitle\":\"4\"},{\"shapeCenter\":\"664,634\",\"shapePoints\":\"71,25\",\"shapeTitle\":\"20\"},{\"shapeCenter\":\"414,718\",\"shapePoints\":\"38,22\",\"shapeTitle\":\"22\"},{\"shapeCenter\":\"643,684\",\"shapePoints\":\"46,22\",\"shapeTitle\":\"21\"},{\"shapeCenter\":\"258,493\",\"shapePoints\":\"75,25\",\"shapeTitle\":\"9\"},{\"shapeCenter\":\"549,519\",\"shapePoints\":\"38,22\",\"shapeTitle\":\"14\"},{\"shapeCenter\":\"474,514\",\"shapePoints\":\"38,21\",\"shapeTitle\":\"13\"},{\"shapeCenter\":\"371,624\",\"shapePoints\":\"23,28\",\"shapeTitle\":\"17\"},{\"shapeCenter\":\"643,41\",\"shapePoints\":\"107,70\",\"shapeTitle\":\"1\"},{\"shapeCenter\":\"259,512\",\"shapePoints\":\"64,53\",\"shapeTitle\":\"11\"}]");

    static final JSONArray points_1 = JSONArray.parseArray("[\n" +
            "  {\n" +
            "    \"id\": \"1621410671817547780\",\n" +
            "    \"companyId\": \"0\",\n" +
            "    \"companyName\": \"\",\n" +
            "    \"serverId\": \"0\",\n" +
            "    \"serverName\": \"\",\n" +
            "    \"partGuid\": \"\",\n" +
            "    \"datMod1\": \"\",\n" +
            "    \"datMod2\": \"\",\n" +
            "    \"datMod3\": \"\",\n" +
            "    \"datum1\": \"\",\n" +
            "    \"datum2\": \"\",\n" +
            "    \"datum3\": \"\",\n" +
            "    \"designator\": \"\",\n" +
            "    \"dimNo\": \"20.3\",\n" +
            "    \"dimSort\": \"00200003\",\n" +
            "    \"drawingGuid\": \"f39abc3e-185e-42b1-9080-b27fcdb7059c\",\n" +
            "    \"guid\": \"18f3eb2e-dd9b-4f03-96a2-a192972db6ca\",\n" +
            "    \"lowerTol\": 0,\n" +
            "    \"multiplier\": 1,\n" +
            "    \"nominal\": 0,\n" +
            "    \"note\": \"MARKED\",\n" +
            "    \"operationGuid\": \"6891eb0f-5778-44b9-b12b-c75d10a7da0c\",\n" +
            "    \"requirement\": \"MARKED\",\n" +
            "    \"shapeActive\": 1,\n" +
            "    \"shapeCenter\": \"34,645\",\n" +
            "    \"shapeLocation\": \"E8\",\n" +
            "    \"shapeOffset\": \"0,0\",\n" +
            "    \"shapePoints\": \"35,10\",\n" +
            "    \"shapeRotateAngle\": 0,\n" +
            "    \"shapeTitle\": \"20\",\n" +
            "    \"shapeType\": 3,\n" +
            "    \"tolCalc\": 0,\n" +
            "    \"tolClass\": \"\",\n" +
            "    \"tolMod1\": \"\",\n" +
            "    \"tolMod2\": \"\",\n" +
            "    \"tolMod3\": \"\",\n" +
            "    \"tolType\": 0,\n" +
            "    \"tolTypeText\": \"\",\n" +
            "    \"type\": 19,\n" +
            "    \"typeText\": \"Note\",\n" +
            "    \"units\": 1,\n" +
            "    \"upperTol\": 0,\n" +
            "    \"procedureGuid\": \"\",\n" +
            "    \"inspCenterGuid\": \"\",\n" +
            "    \"flag\": \"d262b43e68a40fc1613304a34dfd6320\",\n" +
            "    \"updateTime\": 1675409401,\n" +
            "    \"resultResNo\": \"20.3\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"1621410668462104578\",\n" +
            "    \"companyId\": \"0\",\n" +
            "    \"companyName\": \"\",\n" +
            "    \"serverId\": \"0\",\n" +
            "    \"serverName\": \"\",\n" +
            "    \"partGuid\": \"\",\n" +
            "    \"datMod1\": \"\",\n" +
            "    \"datMod2\": \"\",\n" +
            "    \"datMod3\": \"\",\n" +
            "    \"datum1\": \"\",\n" +
            "    \"datum2\": \"\",\n" +
            "    \"datum3\": \"\",\n" +
            "    \"designator\": \"\",\n" +
            "    \"dimNo\": \"12\",\n" +
            "    \"dimSort\": \"00120001\",\n" +
            "    \"drawingGuid\": \"f39abc3e-185e-42b1-9080-b27fcdb7059c\",\n" +
            "    \"guid\": \"b5a9b546-1986-4415-a30d-9600fb18f061\",\n" +
            "    \"lowerTol\": 30,\n" +
            "    \"multiplier\": 1,\n" +
            "    \"nominal\": 0,\n" +
            "    \"note\": \"*\",\n" +
            "    \"operationGuid\": \"6891eb0f-5778-44b9-b12b-c75d10a7da0c\",\n" +
            "    \"requirement\": \"30 MIN\",\n" +
            "    \"shapeActive\": 1,\n" +
            "    \"shapeCenter\": \"506,601\",\n" +
            "    \"shapeLocation\": \"E8\",\n" +
            "    \"shapeOffset\": \"0,0\",\n" +
            "    \"shapePoints\": \"22,6\",\n" +
            "    \"shapeRotateAngle\": 0,\n" +
            "    \"shapeTitle\": \"12\",\n" +
            "    \"shapeType\": 3,\n" +
            "    \"tolCalc\": 1,\n" +
            "    \"tolClass\": \"\",\n" +
            "    \"tolMod1\": \"\",\n" +
            "    \"tolMod2\": \"\",\n" +
            "    \"tolMod3\": \"\",\n" +
            "    \"tolType\": 5,\n" +
            "    \"tolTypeText\": \"MIN\",\n" +
            "    \"type\": 15,\n" +
            "    \"typeText\": \"Linear\",\n" +
            "    \"units\": 1,\n" +
            "    \"upperTol\": 999,\n" +
            "    \"procedureGuid\": \"\",\n" +
            "    \"inspCenterGuid\": \"\",\n" +
            "    \"flag\": \"715e85f4c9a1fbc44242997089ed6a8b\",\n" +
            "    \"updateTime\": 1675409401,\n" +
            "    \"resultResNo\": \"12\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"1621410669389045768\",\n" +
            "    \"companyId\": \"0\",\n" +
            "    \"companyName\": \"\",\n" +
            "    \"serverId\": \"0\",\n" +
            "    \"serverName\": \"\",\n" +
            "    \"partGuid\": \"\",\n" +
            "    \"datMod1\": \"\",\n" +
            "    \"datMod2\": \"\",\n" +
            "    \"datMod3\": \"\",\n" +
            "    \"datum1\": \"\",\n" +
            "    \"datum2\": \"\",\n" +
            "    \"datum3\": \"\",\n" +
            "    \"designator\": \"\",\n" +
            "    \"dimNo\": \"23.8\",\n" +
            "    \"dimSort\": \"00230008\",\n" +
            "    \"drawingGuid\": \"f39abc3e-185e-42b1-9080-b27fcdb7059c\",\n" +
            "    \"guid\": \"3b789d04-6212-4ec2-87da-84c530749db4\",\n" +
            "    \"lowerTol\": 0,\n" +
            "    \"multiplier\": 1,\n" +
            "    \"nominal\": 0,\n" +
            "    \"note\": \"H HOLE\",\n" +
            "    \"operationGuid\": \"6891eb0f-5778-44b9-b12b-c75d10a7da0c\",\n" +
            "    \"requirement\": \"H HOLE\",\n" +
            "    \"shapeActive\": 1,\n" +
            "    \"shapeCenter\": \"175,816\",\n" +
            "    \"shapeLocation\": \"E8\",\n" +
            "    \"shapeOffset\": \"0,0\",\n" +
            "    \"shapePoints\": \"44,10\",\n" +
            "    \"shapeRotateAngle\": 0,\n" +
            "    \"shapeTitle\": \"23\",\n" +
            "    \"shapeType\": 3,\n" +
            "    \"tolCalc\": 0,\n" +
            "    \"tolClass\": \"\",\n" +
            "    \"tolMod1\": \"\",\n" +
            "    \"tolMod2\": \"\",\n" +
            "    \"tolMod3\": \"\",\n" +
            "    \"tolType\": 0,\n" +
            "    \"tolTypeText\": \"\",\n" +
            "    \"type\": 19,\n" +
            "    \"typeText\": \"Note\",\n" +
            "    \"units\": 1,\n" +
            "    \"upperTol\": 0,\n" +
            "    \"procedureGuid\": \"\",\n" +
            "    \"inspCenterGuid\": \"\",\n" +
            "    \"flag\": \"73bccc7ba71d09643be4a905cf20a766\",\n" +
            "    \"updateTime\": 1675409401,\n" +
            "    \"resultResNo\": \"23.8\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"1621410670446010373\",\n" +
            "    \"companyId\": \"0\",\n" +
            "    \"companyName\": \"\",\n" +
            "    \"serverId\": \"0\",\n" +
            "    \"serverName\": \"\",\n" +
            "    \"partGuid\": \"\",\n" +
            "    \"datMod1\": \"\",\n" +
            "    \"datMod2\": \"\",\n" +
            "    \"datMod3\": \"\",\n" +
            "    \"datum1\": \"\",\n" +
            "    \"datum2\": \"\",\n" +
            "    \"datum3\": \"\",\n" +
            "    \"designator\": \"\",\n" +
            "    \"dimNo\": \"20.1\",\n" +
            "    \"dimSort\": \"00200001\",\n" +
            "    \"drawingGuid\": \"f39abc3e-185e-42b1-9080-b27fcdb7059c\",\n" +
            "    \"guid\": \"091a3f53-5e85-4428-bc1a-e2cfba4d27b1\",\n" +
            "    \"lowerTol\": 0,\n" +
            "    \"multiplier\": 1,\n" +
            "    \"nominal\": 11,\n" +
            "    \"note\": \"1A\",\n" +
            "    \"operationGuid\": \"6891eb0f-5778-44b9-b12b-c75d10a7da0c\",\n" +
            "    \"requirement\": \"11 ± 0\",\n" +
            "    \"shapeActive\": 1,\n" +
            "    \"shapeCenter\": \"34,645\",\n" +
            "    \"shapeLocation\": \"E8\",\n" +
            "    \"shapeOffset\": \"0,0\",\n" +
            "    \"shapePoints\": \"35,10\",\n" +
            "    \"shapeRotateAngle\": 0,\n" +
            "    \"shapeTitle\": \"20\",\n" +
            "    \"shapeType\": 3,\n" +
            "    \"tolCalc\": 1,\n" +
            "    \"tolClass\": \"\",\n" +
            "    \"tolMod1\": \"\",\n" +
            "    \"tolMod2\": \"\",\n" +
            "    \"tolMod3\": \"\",\n" +
            "    \"tolType\": 1,\n" +
            "    \"tolTypeText\": \"Tolerance\",\n" +
            "    \"type\": 15,\n" +
            "    \"typeText\": \"Linear\",\n" +
            "    \"units\": 1,\n" +
            "    \"upperTol\": 0,\n" +
            "    \"procedureGuid\": \"\",\n" +
            "    \"inspCenterGuid\": \"\",\n" +
            "    \"flag\": \"05fa8f39a84972bd56e2bf3dd335386e\",\n" +
            "    \"updateTime\": 1675409401,\n" +
            "    \"resultResNo\": \"20.1\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"1621410668394995714\",\n" +
            "    \"companyId\": \"0\",\n" +
            "    \"companyName\": \"\",\n" +
            "    \"serverId\": \"0\",\n" +
            "    \"serverName\": \"\",\n" +
            "    \"partGuid\": \"\",\n" +
            "    \"datMod1\": \"\",\n" +
            "    \"datMod2\": \"\",\n" +
            "    \"datMod3\": \"\",\n" +
            "    \"datum1\": \"\",\n" +
            "    \"datum2\": \"\",\n" +
            "    \"datum3\": \"\",\n" +
            "    \"designator\": \"\",\n" +
            "    \"dimNo\": \"19.2\",\n" +
            "    \"dimSort\": \"00190002\",\n" +
            "    \"drawingGuid\": \"f39abc3e-185e-42b1-9080-b27fcdb7059c\",\n" +
            "    \"guid\": \"7ad70173-cedb-4a97-ab3b-632ab881e022\",\n" +
            "    \"lowerTol\": -0.5,\n" +
            "    \"multiplier\": 1,\n" +
            "    \"nominal\": 25,\n" +
            "    \"note\": \".\",\n" +
            "    \"operationGuid\": \"6891eb0f-5778-44b9-b12b-c75d10a7da0c\",\n" +
            "    \"requirement\": \"25\",\n" +
            "    \"shapeActive\": 1,\n" +
            "    \"shapeCenter\": \"174,636\",\n" +
            "    \"shapeLocation\": \"E8\",\n" +
            "    \"shapeOffset\": \"0,0\",\n" +
            "    \"shapePoints\": \"39,5\",\n" +
            "    \"shapeRotateAngle\": 0,\n" +
            "    \"shapeTitle\": \"19\",\n" +
            "    \"shapeType\": 3,\n" +
            "    \"tolCalc\": 1,\n" +
            "    \"tolClass\": \"\",\n" +
            "    \"tolMod1\": \"\",\n" +
            "    \"tolMod2\": \"\",\n" +
            "    \"tolMod3\": \"\",\n" +
            "    \"tolType\": 0,\n" +
            "    \"tolTypeText\": \"\",\n" +
            "    \"type\": 15,\n" +
            "    \"typeText\": \"Linear\",\n" +
            "    \"units\": 1,\n" +
            "    \"upperTol\": 0.5,\n" +
            "    \"procedureGuid\": \"\",\n" +
            "    \"inspCenterGuid\": \"\",\n" +
            "    \"flag\": \"b5af8f64c6ae5ccad7dbc667ad8eb048\",\n" +
            "    \"updateTime\": 1675409401,\n" +
            "    \"resultResNo\": \"19.2\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"1621410670961909769\",\n" +
            "    \"companyId\": \"0\",\n" +
            "    \"companyName\": \"\",\n" +
            "    \"serverId\": \"0\",\n" +
            "    \"serverName\": \"\",\n" +
            "    \"partGuid\": \"\",\n" +
            "    \"datMod1\": \"\",\n" +
            "    \"datMod2\": \"\",\n" +
            "    \"datMod3\": \"\",\n" +
            "    \"datum1\": \"\",\n" +
            "    \"datum2\": \"\",\n" +
            "    \"datum3\": \"\",\n" +
            "    \"designator\": \"\",\n" +
            "    \"dimNo\": \"17.1\",\n" +
            "    \"dimSort\": \"00170001\",\n" +
            "    \"drawingGuid\": \"f39abc3e-185e-42b1-9080-b27fcdb7059c\",\n" +
            "    \"guid\": \"97a41048-1de3-4f25-aa34-f928f8c860fc\",\n" +
            "    \"lowerTol\": -999,\n" +
            "    \"multiplier\": 1,\n" +
            "    \"nominal\": 0.127,\n" +
            "    \"note\": \"\",\n" +
            "    \"operationGuid\": \"6891eb0f-5778-44b9-b12b-c75d10a7da0c\",\n" +
            "    \"requirement\": \"{ 0~.1~2~7~ }\",\n" +
            "    \"shapeActive\": 1,\n" +
            "    \"shapeCenter\": \"165,629\",\n" +
            "    \"shapeLocation\": \"E6\",\n" +
            "    \"shapeOffset\": \"0,0\",\n" +
            "    \"shapePoints\": \"26,16\",\n" +
            "    \"shapeRotateAngle\": 0,\n" +
            "    \"shapeTitle\": \"17\",\n" +
            "    \"shapeType\": 3,\n" +
            "    \"tolCalc\": 0,\n" +
            "    \"tolClass\": \"\",\n" +
            "    \"tolMod1\": \"\",\n" +
            "    \"tolMod2\": \"\",\n" +
            "    \"tolMod3\": \"\",\n" +
            "    \"tolType\": 3,\n" +
            "    \"tolTypeText\": \"Basic\",\n" +
            "    \"type\": 15,\n" +
            "    \"typeText\": \"Linear\",\n" +
            "    \"units\": 1,\n" +
            "    \"upperTol\": 999,\n" +
            "    \"procedureGuid\": \"\",\n" +
            "    \"inspCenterGuid\": \"\",\n" +
            "    \"flag\": \"7c7567be12c92d73ecd5a42306c08fd9\",\n" +
            "    \"updateTime\": 1675409401,\n" +
            "    \"resultResNo\": \"17.1\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"1621410670446010412\",\n" +
            "    \"companyId\": \"0\",\n" +
            "    \"companyName\": \"\",\n" +
            "    \"serverId\": \"0\",\n" +
            "    \"serverName\": \"\",\n" +
            "    \"partGuid\": \"\",\n" +
            "    \"datMod1\": \"\",\n" +
            "    \"datMod2\": \"\",\n" +
            "    \"datMod3\": \"\",\n" +
            "    \"datum1\": \"\",\n" +
            "    \"datum2\": \"\",\n" +
            "    \"datum3\": \"\",\n" +
            "    \"designator\": \"\",\n" +
            "    \"dimNo\": \"11.2\",\n" +
            "    \"dimSort\": \"00110002\",\n" +
            "    \"drawingGuid\": \"f39abc3e-185e-42b1-9080-b27fcdb7059c\",\n" +
            "    \"guid\": \"6ac83abf-abb9-4ade-bfb3-5450ef2cbb99\",\n" +
            "    \"lowerTol\": -0.5,\n" +
            "    \"multiplier\": 1,\n" +
            "    \"nominal\": 131,\n" +
            "    \"note\": \"\\\\\",\n" +
            "    \"operationGuid\": \"6891eb0f-5778-44b9-b12b-c75d10a7da0c\",\n" +
            "    \"requirement\": \"131\",\n" +
            "    \"shapeActive\": 1,\n" +
            "    \"shapeCenter\": \"234,596\",\n" +
            "    \"shapeLocation\": \"E8\",\n" +
            "    \"shapeOffset\": \"0,0\",\n" +
            "    \"shapePoints\": \"46,12\",\n" +
            "    \"shapeRotateAngle\": 0,\n" +
            "    \"shapeTitle\": \"11\",\n" +
            "    \"shapeType\": 3,\n" +
            "    \"tolCalc\": 1,\n" +
            "    \"tolClass\": \"\",\n" +
            "    \"tolMod1\": \"\",\n" +
            "    \"tolMod2\": \"\",\n" +
            "    \"tolMod3\": \"\",\n" +
            "    \"tolType\": 0,\n" +
            "    \"tolTypeText\": \"\",\n" +
            "    \"type\": 15,\n" +
            "    \"typeText\": \"Linear\",\n" +
            "    \"units\": 1,\n" +
            "    \"upperTol\": 0.5,\n" +
            "    \"procedureGuid\": \"\",\n" +
            "    \"inspCenterGuid\": \"\",\n" +
            "    \"flag\": \"bddd4590bcd0c5a59704dd096ac3a875\",\n" +
            "    \"updateTime\": 1675409401,\n" +
            "    \"resultResNo\": \"11.2\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"1621410668789260309\",\n" +
            "    \"companyId\": \"0\",\n" +
            "    \"companyName\": \"\",\n" +
            "    \"serverId\": \"0\",\n" +
            "    \"serverName\": \"\",\n" +
            "    \"partGuid\": \"\",\n" +
            "    \"datMod1\": \"\",\n" +
            "    \"datMod2\": \"\",\n" +
            "    \"datMod3\": \"\",\n" +
            "    \"datum1\": \"\",\n" +
            "    \"datum2\": \"\",\n" +
            "    \"datum3\": \"\",\n" +
            "    \"designator\": \"\",\n" +
            "    \"dimNo\": \"2.2\",\n" +
            "    \"dimSort\": \"00020002\",\n" +
            "    \"drawingGuid\": \"f39abc3e-185e-42b1-9080-b27fcdb7059c\",\n" +
            "    \"guid\": \"dc681931-7b17-4f3f-a7aa-38fedd8a3097\",\n" +
            "    \"lowerTol\": -0.5,\n" +
            "    \"multiplier\": 1,\n" +
            "    \"nominal\": 1,\n" +
            "    \"note\": \"BETWEEH A B\",\n" +
            "    \"operationGuid\": \"6891eb0f-5778-44b9-b12b-c75d10a7da0c\",\n" +
            "    \"requirement\": \"1\",\n" +
            "    \"shapeActive\": 1,\n" +
            "    \"shapeCenter\": \"258,356\",\n" +
            "    \"shapeLocation\": \"E8\",\n" +
            "    \"shapeOffset\": \"0,0\",\n" +
            "    \"shapePoints\": \"40,12\",\n" +
            "    \"shapeRotateAngle\": 0,\n" +
            "    \"shapeTitle\": \"2\",\n" +
            "    \"shapeType\": 3,\n" +
            "    \"tolCalc\": 1,\n" +
            "    \"tolClass\": \"\",\n" +
            "    \"tolMod1\": \"\",\n" +
            "    \"tolMod2\": \"\",\n" +
            "    \"tolMod3\": \"\",\n" +
            "    \"tolType\": 0,\n" +
            "    \"tolTypeText\": \"\",\n" +
            "    \"type\": 15,\n" +
            "    \"typeText\": \"Linear\",\n" +
            "    \"units\": 1,\n" +
            "    \"upperTol\": 0.5,\n" +
            "    \"procedureGuid\": \"\",\n" +
            "    \"inspCenterGuid\": \"\",\n" +
            "    \"flag\": \"0f208b55c05d98ba435ef795c7c29ddf\",\n" +
            "    \"updateTime\": 1675409401,\n" +
            "    \"resultResNo\": \"2.2\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"1621410670378901510\",\n" +
            "    \"companyId\": \"0\",\n" +
            "    \"companyName\": \"\",\n" +
            "    \"serverId\": \"0\",\n" +
            "    \"serverName\": \"\",\n" +
            "    \"partGuid\": \"\",\n" +
            "    \"datMod1\": \"\",\n" +
            "    \"datMod2\": \"\",\n" +
            "    \"datMod3\": \"\",\n" +
            "    \"datum1\": \"\",\n" +
            "    \"datum2\": \"\",\n" +
            "    \"datum3\": \"\",\n" +
            "    \"designator\": \"\",\n" +
            "    \"dimNo\": \"17.2\",\n" +
            "    \"dimSort\": \"00170002\",\n" +
            "    \"drawingGuid\": \"f39abc3e-185e-42b1-9080-b27fcdb7059c\",\n" +
            "    \"guid\": \"0106fbd0-d5cc-4457-a260-4b2089bc699e\",\n" +
            "    \"lowerTol\": 0,\n" +
            "    \"multiplier\": 1,\n" +
            "    \"nominal\": 0,\n" +
            "    \"note\": \"CJ\",\n" +
            "    \"operationGuid\": \"6891eb0f-5778-44b9-b12b-c75d10a7da0c\",\n" +
            "    \"requirement\": \"CJ\",\n" +
            "    \"shapeActive\": 1,\n" +
            "    \"shapeCenter\": \"165,629\",\n" +
            "    \"shapeLocation\": \"E6\",\n" +
            "    \"shapeOffset\": \"0,0\",\n" +
            "    \"shapePoints\": \"26,16\",\n" +
            "    \"shapeRotateAngle\": 0,\n" +
            "    \"shapeTitle\": \"17\",\n" +
            "    \"shapeType\": 3,\n" +
            "    \"tolCalc\": 0,\n" +
            "    \"tolClass\": \"\",\n" +
            "    \"tolMod1\": \"\",\n" +
            "    \"tolMod2\": \"\",\n" +
            "    \"tolMod3\": \"\",\n" +
            "    \"tolType\": 0,\n" +
            "    \"tolTypeText\": \"\",\n" +
            "    \"type\": 19,\n" +
            "    \"typeText\": \"Note\",\n" +
            "    \"units\": 1,\n" +
            "    \"upperTol\": 0,\n" +
            "    \"procedureGuid\": \"\",\n" +
            "    \"inspCenterGuid\": \"\",\n" +
            "    \"flag\": \"79e9043a9b5669dad67c80f0d3fa488c\",\n" +
            "    \"updateTime\": 1675409401,\n" +
            "    \"resultResNo\": \"17.2\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"1621410670638948355\",\n" +
            "    \"companyId\": \"0\",\n" +
            "    \"companyName\": \"\",\n" +
            "    \"serverId\": \"0\",\n" +
            "    \"serverName\": \"\",\n" +
            "    \"partGuid\": \"\",\n" +
            "    \"datMod1\": \"\",\n" +
            "    \"datMod2\": \"\",\n" +
            "    \"datMod3\": \"\",\n" +
            "    \"datum1\": \"\",\n" +
            "    \"datum2\": \"\",\n" +
            "    \"datum3\": \"\",\n" +
            "    \"designator\": \"\",\n" +
            "    \"dimNo\": \"17.3\",\n" +
            "    \"dimSort\": \"00170003\",\n" +
            "    \"drawingGuid\": \"f39abc3e-185e-42b1-9080-b27fcdb7059c\",\n" +
            "    \"guid\": \"265ed0ff-d543-4c58-b1c7-dd37ccbbf5ff\",\n" +
            "    \"lowerTol\": -0.01,\n" +
            "    \"multiplier\": 1,\n" +
            "    \"nominal\": 0.02,\n" +
            "    \"note\": \":\",\n" +
            "    \"operationGuid\": \"6891eb0f-5778-44b9-b12b-c75d10a7da0c\",\n" +
            "    \"requirement\": \"0.02\",\n" +
            "    \"shapeActive\": 1,\n" +
            "    \"shapeCenter\": \"165,629\",\n" +
            "    \"shapeLocation\": \"E6\",\n" +
            "    \"shapeOffset\": \"0,0\",\n" +
            "    \"shapePoints\": \"26,16\",\n" +
            "    \"shapeRotateAngle\": 0,\n" +
            "    \"shapeTitle\": \"17\",\n" +
            "    \"shapeType\": 3,\n" +
            "    \"tolCalc\": 1,\n" +
            "    \"tolClass\": \"\",\n" +
            "    \"tolMod1\": \"\",\n" +
            "    \"tolMod2\": \"\",\n" +
            "    \"tolMod3\": \"\",\n" +
            "    \"tolType\": 0,\n" +
            "    \"tolTypeText\": \"\",\n" +
            "    \"type\": 15,\n" +
            "    \"typeText\": \"Linear\",\n" +
            "    \"units\": 1,\n" +
            "    \"upperTol\": 0.01,\n" +
            "    \"procedureGuid\": \"\",\n" +
            "    \"inspCenterGuid\": \"\",\n" +
            "    \"flag\": \"dcf9a4489c1ea72e0170ad9baed80953\",\n" +
            "    \"updateTime\": 1675409401,\n" +
            "    \"resultResNo\": \"17.3\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"1621410668655042564\",\n" +
            "    \"companyId\": \"0\",\n" +
            "    \"companyName\": \"\",\n" +
            "    \"serverId\": \"0\",\n" +
            "    \"serverName\": \"\",\n" +
            "    \"partGuid\": \"\",\n" +
            "    \"datMod1\": \"\",\n" +
            "    \"datMod2\": \"\",\n" +
            "    \"datMod3\": \"\",\n" +
            "    \"datum1\": \"\",\n" +
            "    \"datum2\": \"\",\n" +
            "    \"datum3\": \"\",\n" +
            "    \"designator\": \"\",\n" +
            "    \"dimNo\": \"19.3\",\n" +
            "    \"dimSort\": \"00190003\",\n" +
            "    \"drawingGuid\": \"f39abc3e-185e-42b1-9080-b27fcdb7059c\",\n" +
            "    \"guid\": \"5c327f7a-a2f5-42b6-9063-4dca273d824a\",\n" +
            "    \"lowerTol\": -0.1,\n" +
            "    \"multiplier\": 1,\n" +
            "    \"nominal\": 5.4,\n" +
            "    \"note\": \"\",\n" +
            "    \"operationGuid\": \"6891eb0f-5778-44b9-b12b-c75d10a7da0c\",\n" +
            "    \"requirement\": \"5.4\",\n" +
            "    \"shapeActive\": 1,\n" +
            "    \"shapeCenter\": \"174,636\",\n" +
            "    \"shapeLocation\": \"E8\",\n" +
            "    \"shapeOffset\": \"0,0\",\n" +
            "    \"shapePoints\": \"39,5\",\n" +
            "    \"shapeRotateAngle\": 0,\n" +
            "    \"shapeTitle\": \"19\",\n" +
            "    \"shapeType\": 3,\n" +
            "    \"tolCalc\": 1,\n" +
            "    \"tolClass\": \"\",\n" +
            "    \"tolMod1\": \"\",\n" +
            "    \"tolMod2\": \"\",\n" +
            "    \"tolMod3\": \"\",\n" +
            "    \"tolType\": 0,\n" +
            "    \"tolTypeText\": \"\",\n" +
            "    \"type\": 15,\n" +
            "    \"typeText\": \"Linear\",\n" +
            "    \"units\": 1,\n" +
            "    \"upperTol\": 0.1,\n" +
            "    \"procedureGuid\": \"\",\n" +
            "    \"inspCenterGuid\": \"\",\n" +
            "    \"flag\": \"2e1832320f9cc2026e858a99bd574f59\",\n" +
            "    \"updateTime\": 1675409401,\n" +
            "    \"resultResNo\": \"19.3\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"1621410668789260292\",\n" +
            "    \"companyId\": \"0\",\n" +
            "    \"companyName\": \"\",\n" +
            "    \"serverId\": \"0\",\n" +
            "    \"serverName\": \"\",\n" +
            "    \"partGuid\": \"\",\n" +
            "    \"datMod1\": \"\",\n" +
            "    \"datMod2\": \"\",\n" +
            "    \"datMod3\": \"\",\n" +
            "    \"datum1\": \"\",\n" +
            "    \"datum2\": \"\",\n" +
            "    \"datum3\": \"\",\n" +
            "    \"designator\": \"\",\n" +
            "    \"dimNo\": \"23.6\",\n" +
            "    \"dimSort\": \"00230006\",\n" +
            "    \"drawingGuid\": \"f39abc3e-185e-42b1-9080-b27fcdb7059c\",\n" +
            "    \"guid\": \"182b7ac7-7a9a-4a93-afda-487eff635b96\",\n" +
            "    \"lowerTol\": 0,\n" +
            "    \"multiplier\": 1,\n" +
            "    \"nominal\": 0,\n" +
            "    \"note\": \"f\",\n" +
            "    \"operationGuid\": \"6891eb0f-5778-44b9-b12b-c75d10a7da0c\",\n" +
            "    \"requirement\": \"f\",\n" +
            "    \"shapeActive\": 1,\n" +
            "    \"shapeCenter\": \"175,816\",\n" +
            "    \"shapeLocation\": \"E8\",\n" +
            "    \"shapeOffset\": \"0,0\",\n" +
            "    \"shapePoints\": \"44,10\",\n" +
            "    \"shapeRotateAngle\": 0,\n" +
            "    \"shapeTitle\": \"23\",\n" +
            "    \"shapeType\": 3,\n" +
            "    \"tolCalc\": 0,\n" +
            "    \"tolClass\": \"\",\n" +
            "    \"tolMod1\": \"\",\n" +
            "    \"tolMod2\": \"\",\n" +
            "    \"tolMod3\": \"\",\n" +
            "    \"tolType\": 0,\n" +
            "    \"tolTypeText\": \"\",\n" +
            "    \"type\": 19,\n" +
            "    \"typeText\": \"Note\",\n" +
            "    \"units\": 1,\n" +
            "    \"upperTol\": 0,\n" +
            "    \"procedureGuid\": \"\",\n" +
            "    \"inspCenterGuid\": \"\",\n" +
            "    \"flag\": \"c1b3e3dc083044983a9795a8a28fde58\",\n" +
            "    \"updateTime\": 1675409401,\n" +
            "    \"resultResNo\": \"23.6\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"1621410667996536841\",\n" +
            "    \"companyId\": \"0\",\n" +
            "    \"companyName\": \"\",\n" +
            "    \"serverId\": \"0\",\n" +
            "    \"serverName\": \"\",\n" +
            "    \"partGuid\": \"\",\n" +
            "    \"datMod1\": \"\",\n" +
            "    \"datMod2\": \"\",\n" +
            "    \"datMod3\": \"\",\n" +
            "    \"datum1\": \"\",\n" +
            "    \"datum2\": \"\",\n" +
            "    \"datum3\": \"\",\n" +
            "    \"designator\": \"\",\n" +
            "    \"dimNo\": \"20.2\",\n" +
            "    \"dimSort\": \"00200002\",\n" +
            "    \"drawingGuid\": \"f39abc3e-185e-42b1-9080-b27fcdb7059c\",\n" +
            "    \"guid\": \"fb10b3ed-66dc-4283-b2c3-eef166c7fefa\",\n" +
            "    \"lowerTol\": 0,\n" +
            "    \"multiplier\": 1,\n" +
            "    \"nominal\": 0,\n" +
            "    \"note\": \"AREAS\",\n" +
            "    \"operationGuid\": \"6891eb0f-5778-44b9-b12b-c75d10a7da0c\",\n" +
            "    \"requirement\": \"AREAS\",\n" +
            "    \"shapeActive\": 1,\n" +
            "    \"shapeCenter\": \"34,645\",\n" +
            "    \"shapeLocation\": \"E8\",\n" +
            "    \"shapeOffset\": \"0,0\",\n" +
            "    \"shapePoints\": \"35,10\",\n" +
            "    \"shapeRotateAngle\": 0,\n" +
            "    \"shapeTitle\": \"20\",\n" +
            "    \"shapeType\": 3,\n" +
            "    \"tolCalc\": 0,\n" +
            "    \"tolClass\": \"\",\n" +
            "    \"tolMod1\": \"\",\n" +
            "    \"tolMod2\": \"\",\n" +
            "    \"tolMod3\": \"\",\n" +
            "    \"tolType\": 0,\n" +
            "    \"tolTypeText\": \"\",\n" +
            "    \"type\": 19,\n" +
            "    \"typeText\": \"Note\",\n" +
            "    \"units\": 1,\n" +
            "    \"upperTol\": 0,\n" +
            "    \"procedureGuid\": \"\",\n" +
            "    \"inspCenterGuid\": \"\",\n" +
            "    \"flag\": \"b4f17decf64ee2ff3b7e28016a1d4651\",\n" +
            "    \"updateTime\": 1675409401,\n" +
            "    \"resultResNo\": \"20.2\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"1621410669980442628\",\n" +
            "    \"companyId\": \"0\",\n" +
            "    \"companyName\": \"\",\n" +
            "    \"serverId\": \"0\",\n" +
            "    \"serverName\": \"\",\n" +
            "    \"partGuid\": \"\",\n" +
            "    \"datMod1\": \"\",\n" +
            "    \"datMod2\": \"\",\n" +
            "    \"datMod3\": \"\",\n" +
            "    \"datum1\": \"\",\n" +
            "    \"datum2\": \"\",\n" +
            "    \"datum3\": \"\",\n" +
            "    \"designator\": \"\",\n" +
            "    \"dimNo\": \"21.2\",\n" +
            "    \"dimSort\": \"00210002\",\n" +
            "    \"drawingGuid\": \"f39abc3e-185e-42b1-9080-b27fcdb7059c\",\n" +
            "    \"guid\": \"e5e77811-b139-48c8-9465-df0d539c77f9\",\n" +
            "    \"lowerTol\": -0.1,\n" +
            "    \"multiplier\": 1,\n" +
            "    \"nominal\": 0.5,\n" +
            "    \"note\": \"*\",\n" +
            "    \"operationGuid\": \"6891eb0f-5778-44b9-b12b-c75d10a7da0c\",\n" +
            "    \"requirement\": \"0.5\",\n" +
            "    \"shapeActive\": 1,\n" +
            "    \"shapeCenter\": \"499,667\",\n" +
            "    \"shapeLocation\": \"E8\",\n" +
            "    \"shapeOffset\": \"0,0\",\n" +
            "    \"shapePoints\": \"33,13\",\n" +
            "    \"shapeRotateAngle\": 0,\n" +
            "    \"shapeTitle\": \"21\",\n" +
            "    \"shapeType\": 3,\n" +
            "    \"tolCalc\": 1,\n" +
            "    \"tolClass\": \"\",\n" +
            "    \"tolMod1\": \"\",\n" +
            "    \"tolMod2\": \"\",\n" +
            "    \"tolMod3\": \"\",\n" +
            "    \"tolType\": 0,\n" +
            "    \"tolTypeText\": \"\",\n" +
            "    \"type\": 15,\n" +
            "    \"typeText\": \"Linear\",\n" +
            "    \"units\": 1,\n" +
            "    \"upperTol\": 0.1,\n" +
            "    \"procedureGuid\": \"\",\n" +
            "    \"inspCenterGuid\": \"\",\n" +
            "    \"flag\": \"cf57a0d347a09fd0e723e65dab19b93f\",\n" +
            "    \"updateTime\": 1675409401,\n" +
            "    \"resultResNo\": \"21.2\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"1621410668789260308\",\n" +
            "    \"companyId\": \"0\",\n" +
            "    \"companyName\": \"\",\n" +
            "    \"serverId\": \"0\",\n" +
            "    \"serverName\": \"\",\n" +
            "    \"partGuid\": \"\",\n" +
            "    \"datMod1\": \"\",\n" +
            "    \"datMod2\": \"\",\n" +
            "    \"datMod3\": \"\",\n" +
            "    \"datum1\": \"\",\n" +
            "    \"datum2\": \"\",\n" +
            "    \"datum3\": \"\",\n" +
            "    \"designator\": \"\",\n" +
            "    \"dimNo\": \"8\",\n" +
            "    \"dimSort\": \"00080001\",\n" +
            "    \"drawingGuid\": \"f39abc3e-185e-42b1-9080-b27fcdb7059c\",\n" +
            "    \"guid\": \"752fe636-c69e-4cf5-a79e-306538b8a04e\",\n" +
            "    \"lowerTol\": -0.1,\n" +
            "    \"multiplier\": 1,\n" +
            "    \"nominal\": 16.9,\n" +
            "    \"note\": \"\",\n" +
            "    \"operationGuid\": \"6891eb0f-5778-44b9-b12b-c75d10a7da0c\",\n" +
            "    \"requirement\": \"16.9\",\n" +
            "    \"shapeActive\": 1,\n" +
            "    \"shapeCenter\": \"132,572\",\n" +
            "    \"shapeLocation\": \"E1\",\n" +
            "    \"shapeOffset\": \"0,0\",\n" +
            "    \"shapePoints\": \"10,5\",\n" +
            "    \"shapeRotateAngle\": 0,\n" +
            "    \"shapeTitle\": \"8\",\n" +
            "    \"shapeType\": 3,\n" +
            "    \"tolCalc\": 1,\n" +
            "    \"tolClass\": \"\",\n" +
            "    \"tolMod1\": \"\",\n" +
            "    \"tolMod2\": \"\",\n" +
            "    \"tolMod3\": \"\",\n" +
            "    \"tolType\": 0,\n" +
            "    \"tolTypeText\": \"\",\n" +
            "    \"type\": 15,\n" +
            "    \"typeText\": \"Linear\",\n" +
            "    \"units\": 1,\n" +
            "    \"upperTol\": 0.1,\n" +
            "    \"procedureGuid\": \"\",\n" +
            "    \"inspCenterGuid\": \"\",\n" +
            "    \"flag\": \"1112841955e9b46f7bc09ef3c09688b6\",\n" +
            "    \"updateTime\": 1675409401,\n" +
            "    \"resultResNo\": \"8\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": \"1621410667728101380\",\n" +
            "    \"companyId\": \"0\",\n" +
            "    \"companyName\": \"\",\n" +
            "    \"serverId\": \"0\",\n" +
            "    \"serverName\": \"\",\n" +
            "    \"partGuid\": \"\",\n" +
            "    \"datMod1\": \"\",\n" +
            "    \"datMod2\": \"\",\n" +
            "    \"datMod3\": \"\",\n" +
            "    \"datum1\": \"\",\n" +
            "    \"datum2\": \"\",\n" +
            "    \"datum3\": \"\",\n" +
            "    \"designator\": \"\",\n" +
            "    \"dimNo\": \"14\",\n" +
            "    \"dimSort\": \"00140001\",\n" +
            "    \"drawingGuid\": \"f39abc3e-185e-42b1-9080-b27fcdb7059c\",\n" +
            "    \"guid\": \"dfb85544-87e7-4ef3-8c9d-bce0913c7344\",\n" +
            "    \"lowerTol\": -0.5,\n" +
            "    \"multiplier\": 1,\n" +
            "    \"nominal\": 188,\n" +
            "    \"note\": \"\",\n" +
            "    \"operationGuid\": \"6891eb0f-5778-44b9-b12b-c75d10a7da0c\",\n" +
            "    \"requirement\": \"188\",\n" +
            "    \"shapeActive\": 1,\n" +
            "    \"shapeCenter\": \"167,608\",\n" +
            "    \"shapeLocation\": \"E6\",\n" +
            "    \"shapeOffset\": \"0,0\",\n" +
            "    \"shapePoints\": \"13,8\",\n" +
            "    \"shapeRotateAngle\": 0,\n" +
            "    \"shapeTitle\": \"14\",\n" +
            "    \"shapeType\": 3,\n" +
            "    \"tolCalc\": 1,\n" +
            "    \"tolClass\": \"\",\n" +
            "    \"tolMod1\": \"\",\n" +
            "    \"tolMod2\": \"\",\n" +
            "    \"tolMod3\": \"\",\n" +
            "    \"tolType\": 0,\n" +
            "    \"tolTypeText\": \"\",\n" +
            "    \"type\": 15,\n" +
            "    \"typeText\": \"Linear\",\n" +
            "    \"units\": 1,\n" +
            "    \"upperTol\": 0.5,\n" +
            "    \"procedureGuid\": \"\",\n" +
            "    \"inspCenterGuid\": \"\",\n" +
            "    \"flag\": \"031eb11ff0a4639c703b723baacbde5e\",\n" +
            "    \"updateTime\": 1675409401,\n" +
            "    \"resultResNo\": \"14\"\n" +
            "  }" +
            "]");
}
