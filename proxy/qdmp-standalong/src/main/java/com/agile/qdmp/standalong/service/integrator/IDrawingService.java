package com.agile.qdmp.standalong.service.integrator;

import com.agile.qdmp.standalong.model.entity.integrator.Drawing;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;
import java.util.List;

/**
 * DRAWING
 *
 * @author wenbinglei
 * @date 2022-10-22 15:03:30
 */
public interface IDrawingService extends IService<Drawing> {
    /**
     * 完善图纸信息，包含解析尺寸、保存PDF和生成图片
     * @param drawings
     * @return
     */
    Boolean completeDrawingList(List<Drawing> drawings, String serverUri, String accessToken);

    /**
     * 完善图纸信息，包含解析尺寸、保存PDF和生成图片
     * @param drawing
     * @return
     */
    Drawing completeDrawingData(Drawing drawing, String serverUri, String accessToken);

    /**
     * 根据PDF构建图片，包含DIM
     */
    void buildImage();

    /**
     * 获取指定类型的文件列表
     * @param params
     * @return
     */
    List<JSONObject> fetchFileList(HashMap<String, String> params);

    /**
     * 获取图纸完整信息
     * @param id
     * @return
     */
    JSONObject fetchDimFileList(Long id);
}
