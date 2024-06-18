package com.agile.qdmp.standalong.service.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.DimCount;
import com.agile.qdmp.standalong.model.dto.integrator.DimensionDTO;
import com.agile.qdmp.standalong.model.entity.integrator.CharacterDesignator;
import com.agile.qdmp.standalong.model.entity.integrator.Dimension;
import com.agile.qdmp.standalong.model.entity.integrator.DimensionType;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * DIMENSION
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:41:56
 */
public interface IDimensionService extends IService<Dimension> {

    /**
     * 批量新增Dimension
     * @param list
     * @return
     */
    Boolean createDimensionMulti(List<Dimension> list);

    /**
     * 整理CharacterDesignator
     * @return
     */
    List<CharacterDesignator> listCharacterDesignator();

    /**
     * 整理DimensionType
     * @return
     */
    List<DimensionType> listDimensionTypes();

    /**
     * 根据JobGuid查询sample数量
     * @return
     */
    List<DimCount> customCountByPartGuid(String partGuid);

    /**
     * 根据SampleGuid和OperationGuid查询尺寸数量
     * @return
     */
    List<JSONObject> listBySampleAndOperation(String sampleId, String operationId, String procedureId);

    /**
     * 根据SampleGuid获取重复的Dim
     * @return
     */
    List<Dimension> listBySample(String operationGuid, String[] guids);

    /**
     * 根据DrawingGUID更新PartGUID
     * @return
     */
    int updatePartGuidbyDrawing();
}
