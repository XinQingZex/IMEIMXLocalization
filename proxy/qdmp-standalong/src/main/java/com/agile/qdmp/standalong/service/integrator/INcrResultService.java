package com.agile.qdmp.standalong.service.integrator;

import com.agile.qdmp.standalong.model.entity.integrator.NcrResult;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * NCR_RESULT
 *
 * @author hyzh code generator
 * @date 2022-12-18 14:33:57
 */
public interface INcrResultService extends IService<NcrResult> {

    /**
     * 批量新增NcrResult
     * @param ncrResultList
     * @return
     */
    Boolean createNcrResultMulti(List<NcrResult> ncrResultList);

    /**
     * 根据ncrID查询
     * @param id
     * @return
     */
    List<JSONObject> listById(String id);
}
