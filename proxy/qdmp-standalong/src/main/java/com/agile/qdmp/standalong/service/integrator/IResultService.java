package com.agile.qdmp.standalong.service.integrator;

import com.agile.qdmp.standalong.model.dto.integrator.ResultCount;
import com.agile.qdmp.standalong.model.dto.integrator.query.ResultQueryDTO;
import com.agile.qdmp.standalong.model.entity.integrator.Result;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * RESULT
 *
 * @author hyzh code generator
 * @date 2022-09-30 09:42:06
 */
public interface IResultService extends IService<Result> {
    /**
     * 批量新增Result
     * @param list
     * @return
     */
    Boolean createResultMulti(List<Result> list);

    /**
     * 批量新增Result
     * @param list
     * @return
     */
    Boolean updateResultMulti(List<Result> list);


    /**
     * 根据Sample统计Result各个类型结果
     * @param sampleGuid
     * @return
     */
    List<ResultCount> customCountBySampleGuid(String sampleGuid);

    /**
     * 统计Sample和Result结果
     * @param queryDTO
     * @return
     */
    List<JSONObject> customCount(ResultQueryDTO queryDTO);

    /**
     * 根据Job统计Sample和Result结果(带Lot)
     * @param queryDTO
     * @return
     */
    List<JSONObject> customCountByJob(ResultQueryDTO queryDTO);

//    /**
//     * 根据Job统计Result结果
//     * @param lotGuid
//     * @return
//     */
//    List<ResultCount> customCountByLotGuid(String lotGuid);
}
