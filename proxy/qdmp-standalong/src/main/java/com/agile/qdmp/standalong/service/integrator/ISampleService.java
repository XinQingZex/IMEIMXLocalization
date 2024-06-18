package com.agile.qdmp.standalong.service.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.SampleCount;
import com.agile.qdmp.standalong.model.dto.integrator.query.SampleQueryDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Sample;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * SAMPLE
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:42:00
 */
public interface ISampleService extends IService<Sample> {

    /**
     * 批量新增Sample
     * @param list
     * @return
     */
    Boolean createSampleMulti(List<Sample> list);

    /**
     * 查询sample数量
     * @return
     */
    List<SampleCount> customCount();

    /**
     * 根据JobGuid查询sample数量
     * @return
     */
    List<SampleCount> customCountByJobGuid(String jobGuid);

    /**
     * 查询所有
     * @param queryDTO
     * @return
     */
    List<JSONObject> listByQuery(SampleQueryDTO queryDTO);
    JSONObject getBySampleId(String sampleId);

    /**
     * 根据Part查询
     * @param queryDTO
     * @return
     */
    List<JSONObject> customCountByPart(SampleQueryDTO queryDTO);
}
